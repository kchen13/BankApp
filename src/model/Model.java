/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Soren Diehl
 * @author Kelby Chen
 */
public class Model {

    //Soren Diehl
    //Kelby Chen
    //Shawn Conway
    int currUID;
    String currFirstName, currLastName, currMiddleInitial, currEmail,
            currPassword;
    private int UIDGen;
    private int transUID, transAccUID;
    private double  foodExp,fuelExp,medExp,rentExp,schoolExp,miscExp;
    private Date transDate;
    private String transCategory;
    private String transDesc;
    private String transType;
    private double transAmt;
    ArrayList<User> users = new ArrayList<>();
    ArrayList<BankAccount> accounts = new ArrayList<>();
    ArrayList<Transaction> transactions = new ArrayList<>();
    ArrayList<Transaction> returnList = new ArrayList();
    User currentUser;
    Persistence pe;
    String accountName;

    public Model() {
        //Kelby Chen
        //Soren Diehl
        //assigns all ArrayLists to the saved values from persistence
        //Sets UIDGen from text file
        pe = new Persistence();
        UIDGen = pe.readUIDGen();
        accounts = pe.readAccounts();
        transactions = pe.readTransactions();
        users = pe.readUsers();
        currentUser = pe.readCurrentUser();

    }

    public void setCurrentUser() {
        //Shawn Conway
        //instantiates a user with current user's data
        //overwrites the current user data file
        User u = new User(currUID, currFirstName, currLastName,
                currMiddleInitial, currEmail, currPassword);
        currentUser = u;
        pe.writeCurrentUser(currentUser);
        pe.readCurrentUser();
    }

    public void createUser(String iFirstName, String iLastName,
            String iMiddleInitial, String iEmail, String iPassword) {
        //Soren Diehl
        //takes registration parameters, adds a UID, instantiates a new user, 
        //adds that user to the current user array, saves the userArray to 
        //users.txt
        int iUID = getUIDGen();
        User newUser = new User(iUID, iFirstName, iLastName, iMiddleInitial,
                iEmail, iPassword);
        users.add(newUser);
        pe.writeUserArray(users);
        createAccount(iUID);
    }

    public boolean validateUser(String checkUser, String checkPW) {
        //Soren Diehl
        //Shawn Conway
        //Compares user entered e-mail address to all emails in user Array and
        //checks PW if match found, returns true if PW matches
        //Sets current user based on login credentials
        boolean validUser = false;
        for (int i = 0; i < users.size(); i++) {
            if (checkUser.equals(users.get(i).getEmail())) {
                if (checkPW.equals(users.get(i).getPassword())) {
                    validUser = true;
                    currUID = users.get(i).getUID();
                    currFirstName = users.get(i).getFirstName();
                    currLastName = users.get(i).getLastName();
                    currMiddleInitial = users.get(i).getMiddleInitial();
                    currEmail = users.get(i).getEmail();
                    currPassword = users.get(i).getPassword();
                    setCurrentUser();
                }
            }
        }
        return validUser;
    }

    public String getUserName() {
        //Kelby Chen
        
        //Returns user name
        return currentUser.getFirstName() + " " + currentUser.getLastName();
    }

    public boolean uniqueUser(String email) {
        boolean uniqueCheck = true;
        for (int i = 0; i < users.size(); i++) {
            if (email.equals(users.get(i).getEmail())) {
                uniqueCheck = false;
            }
        }
        return uniqueCheck;
    }

    public void createAccount(int userUID) {
        //Soren Diehl
        //creates a new users empty accounts, adds them to the accounts array, 
        //and saves the array

        int checkingUID = getUIDGen();
        int savingsUID = getUIDGen();
        int vcUID = getUIDGen();

        BankAccount newChecking = new BankAccount(checkingUID, userUID,
                "Checking", 0.0);
        BankAccount newSavings = new BankAccount(savingsUID, userUID,
                "Savings", 0.0);
        BankAccount newVC = new BankAccount(vcUID, userUID,
                "Vacation Club", 0.0);
        accounts.add(newChecking);
        accounts.add(newSavings);
        accounts.add(newVC);
        pe.writeBankAccountArray(accounts);

    }

    public int getAccUID(String accountType) {
        //Soren Diehl
        //Takes an accName param (eg. "Checking") and searches accounts for 
        //currentUser's UID + accName match
        //returns the accUID of matching account
        int accUID = 0;
        for (int i = 0; i < accounts.size(); i++) {
            if (currentUser.getUID() == accounts.get(i).getUserUID()
                    && accountType.equalsIgnoreCase(accounts.get(i).getName())) {
                accUID = accounts.get(i).getUID();
            }
        }
        return accUID;
    }

    public double checkBalance(String accountType) {
        //Soren Diehl
        int accToCheck = getAccUID(accountType);
        double bal = 0.0;
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUID() == accToCheck) {
                bal = accounts.get(i).getBalance();
            }
        }
        return bal;
    }

    public void createTransaction(String accountType) {
        ///use getAccUID to find accUID based on currentUser and accountType
        transAccUID = getAccUID(accountType);
        //get a unique UID for the transaction
        transUID = getUIDGen();
        //set transDate to today's date
        transDate = new Date();
    }

    public void createDeposit(String accountType, double amount,
            String userDescription) {
        //type of transaction
        transType = "deposit";
        //set the expense category to not applicable(NA)
        transCategory = "NA";
        createTransaction(accountType);
        Transaction trans = new Transaction(transUID, transAccUID,
                transDate, transCategory, userDescription, amount,  transType);
        transactions.add(trans);
        pe.writeTransactionArray(transactions);
        //adjust account balances in accounts array
       
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUID() == transAccUID) {
                accounts.get(i).setBalance(accounts.get(i).getBalance() + amount);
            }
        }
        pe.writeBankAccountArray(accounts);
    }

    public void createWithdrawal(String accountType, double amount,
            String userCat, String userDescription) {
        //Soren Diehl
        //parameters are account Name, amount of withdrawal, expense category
        //and user-entered description
        //type of transaction
        transType = "withdrawal";
        //if user chose an expense category, assign it to transCategory
        if (!userCat.equalsIgnoreCase("NA")) {
            transCategory = userCat;
        } else {
            transCategory = "NA";
        }
        //call createTransaction to set accID, date, and UID
        createTransaction(accountType);
        //instantiate the Transaction
        Transaction newTrans = new Transaction(transUID, transAccUID, transDate,
                transCategory, userDescription, amount, transType);
        //add the transaction to the transactions array and save the array
        transactions.add(newTrans);
        pe.writeTransactionArray(transactions);
        //alter the account balance in accounts array
        for (int i = 0; i < accounts.size(); i++) {
            if (transAccUID == accounts.get(i).getUID()) {
                accounts.get(i).setBalance(accounts.get(i).getBalance() - amount);
            }
        }
        //save the accounts array
        pe.writeBankAccountArray(accounts);
    }

    public void createTransfer(String accountType, double amount,
            String userDescription, String accountRecipType) {
        //Soren Diehl
        //this method creates 2 transactions, 1 for the transferring acc
        //and one for the recipient account
        //parameters are account name, transfer amount, user-entered 
        //description + other account info, and recipient account name
        transType = "transfer";
        transCategory = "NA";
        createTransaction(accountType);
        //Concatenate transfer info to description
        transDesc = userDescription + " Transfer to " + accountRecipType;
        double amt = 0 - amount;
        //instantiate the outgoing transfer and add to array
        Transaction newTrans = new Transaction(transUID, transAccUID, transDate,
                transCategory, transDesc, amt, transType);
        transactions.add(newTrans);
        //alter the account balance in the array
        for (int i = 0; i < accounts.size(); i++) {
            if (transAccUID == accounts.get(i).getUID()) {
                accounts.get(i).setBalance(accounts.get(i).getBalance() - amount);
            }
        }
        //instantiate the incoming transfer and add to the array
        //Note: the transactions will have different UIDs
        createTransaction(accountRecipType);
        transDesc = userDescription + " Transfer from " + accountType;
        amt = amount;
        Transaction newTrans2 = new Transaction(transUID, transAccUID,
                transDate, transCategory, transDesc, amount, transType);
        transactions.add(newTrans2);
        //alter the account balance in the array
        for (int i = 0; i < accounts.size(); i++) {
            if (transAccUID == accounts.get(i).getUID()) {
                accounts.get(i).setBalance(accounts.get(i).getBalance() + amount);
            }
        }
        //save the arrays
        pe.writeTransactionArray(transactions);
        pe.writeBankAccountArray(accounts);
    }

    
    public ArrayList<Transaction> findTransactions(String field, String param) {
        //Soren Diehl
        //Kelby Chen
        //First string is for search type
        //second string is the search value
        //returns an Array of Transactions matching the search details
        if(returnList.size() > 0){returnList.clear();}
        String accName = "";
        if(field.equals("Amount")){
            
            double amtSrch = Double.parseDouble(param);
            for(int i = 0; i < transactions.size(); i++){
                if(amtSrch == transactions.get(i).getAmt()){
                    for(int j = 0; j < accounts.size(); j++){
                        if(accounts.get(j).getUID() == 
                                transactions.get(i).getAccUID()){
                            accName = accounts.get(j).getName();
                        }
                    transactions.get(i).setAccName(accName);
                    }
                    returnList.add(transactions.get(i));
                }
            }
        }
        if (field.equals("Expense Category")){
            String search = param;
            for(int i = 0; i < transactions.size(); i++){
                if(search.equalsIgnoreCase(transactions.get(i).getCategory())){
                    for(int j = 0; j < accounts.size(); j++){
                        if(accounts.get(j).getUID() == 
                                transactions.get(i).getAccUID()){
                            accName = accounts.get(j).getName();
                        }
                    transactions.get(i).setAccName(accName);
                    }
                    returnList.add(transactions.get(i));
                }
            }
        }
        if(field.equals("Description Contents")){
            for(int i = 0; i < transactions.size(); i++){
                if(transactions.get(i).getDesc().contains(param)){
                    for(int j = 0; j < accounts.size(); j++){
                        if(accounts.get(j).getUID() == 
                                transactions.get(i).getAccUID()){
                            accName = accounts.get(j).getName();
                        }
                    transactions.get(i).setAccName(accName);
                    }
                    returnList.add(transactions.get(i));
                }
            }
        }
        return returnList;
    }
    
    public void countExpenses(String iAccType)
    //Shawn Conway
    //takes account type string as arg
    {
        //initialize counter fields as 0
        foodExp = 0;
        fuelExp = 0;
        medExp = 0;
        rentExp = 0;
        schoolExp = 0;
        miscExp = 0;
        //sort through transactions arraylist
        for(int i = 0;i< transactions.size();i++)
        {
            //if the current user's account id of the specified type equals any account id in transactions
            //matches that transaction's category to preset values, gets the amount of that transaction and adds it to a respective counter field
            if(getAccUID(iAccType) == transactions.get(i).getAccUID())
            {
                if(transactions.get(i).getCategory().equalsIgnoreCase("food")){
                        
                        foodExp += transactions.get(i).getAmt();
                    }
                else if(transactions.get(i).getCategory().equalsIgnoreCase("fuel")){
                        
                        fuelExp += transactions.get(i).getAmt();
                    }
               else if(transactions.get(i).getCategory().equalsIgnoreCase("medical")){
                        
                   medExp += transactions.get(i).getAmt();
               }
              else if(transactions.get(i).getCategory().equalsIgnoreCase("rent")){
                  
                  rentExp += transactions.get(i).getAmt();
              }
              else if(transactions.get(i).getCategory().equalsIgnoreCase("school")){
                  
                  schoolExp += transactions.get(i).getAmt();
              }
              else if(transactions.get(i).getCategory().equalsIgnoreCase("misc")){
                  
                  miscExp += transactions.get(i).getAmt();
              }     
               
                    
  
            }
        }
    }

    /**
     * @return the UIDGen
     */
    public int getUIDGen() { //increments the UIDGen object before every use and
        //saves the number in the UIDgen.txt file
        //Soren Diehl
        UIDGen++;
        pe.writeUIDGen(UIDGen);
        return UIDGen;
    }
    
    //Shawn Conway
    //getters for the expense counter fields
    public double getFoodExp() {
        return foodExp;
    }

    public double getFuelExp() {
        return fuelExp;
    }

    public double getMedExp() {
        return medExp;
    }

    public double getRentExp() {
        return rentExp;
    }

    public double getSchoolExp() {
        return schoolExp;
    }
    
    

    public double getMiscExp() {
        return miscExp;
    }
    
}
