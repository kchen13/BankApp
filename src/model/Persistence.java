package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Soren Diehl
 */
public class Persistence {

    //Soren Diehl
    StringBuilder arrayToFile = new StringBuilder();
    StringBuilder userToFile = new StringBuilder();
    private User currentUser;
    ArrayList<User> userArray = new ArrayList<>();
    ArrayList<Transaction> transactionArray = new ArrayList<>();
    ArrayList<BankAccount> baArray = new ArrayList<>();
    private File users = new File("src/model/data/users.txt");
    private File transactions = new File("src/model/data/transactions.txt");
    private File bankaccounts = new File("src/model/data/bankaccounts.txt");
    private File UIDgenFile = new File("src/model/data/UIDgen.txt");
    private File currentuser = new File("src/model/data/currentUser");

    public Persistence() {
        //Soren Diehl
        //Kelby Chen
        //Shawn Conway

        if (!users.isFile()) {
            try {
                users.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (!transactions.isFile()) {
            try {
                transactions.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (!bankaccounts.isFile()) {
            try {
                bankaccounts.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        if (!UIDgenFile.isFile()) {
            try {
                UIDgenFile.createNewFile();
                FileWriter fw = new FileWriter(UIDgenFile, false);
                fw.write("0");
                fw.flush();
                fw.close();
            } catch (IOException ex) {
            }
        }
        if (!currentuser.isFile()) {
            try {
                currentuser.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void writeUIDGen(int lastUID) {
        //Soren Diehl
        try {
            FileWriter fw = new FileWriter(UIDgenFile, false);
            String toWrite = Integer.toString(lastUID);
            fw.write(toWrite);
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeCurrentUser(User cU) {
        //Shawn Conway
        //overwrites current user's data to a file
        StringBuilder userToFile = new StringBuilder();
        userToFile
                .append("UID")
                .append("\t")
                .append("firstName")
                .append("\t")
                .append("lastName")
                .append("\t")
                .append("middleInitial")
                .append("\t")
                .append("email")
                .append("\t")
                .append("password")
                .append("\t")
                .append("\n")
                .append(cU.getUID())
                .append("\t")
                .append(cU.getFirstName())
                .append("\t")
                .append(cU.getLastName())
                .append("\t")
                .append(cU.getMiddleInitial())
                .append("\t")
                .append(cU.getEmail())
                .append("\t")
                .append(cU.getPassword());

        try {
            FileWriter fw = new FileWriter(currentuser, false);
            fw.write(userToFile.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void writeTransactionArray(ArrayList<Transaction> wTA) {
        //Soren Diehl
        //saves an ArrayList of transactions to tab delimited text file
        StringBuilder arrayToFile = new StringBuilder();
        transactionArray = wTA;
        arrayToFile
                .append("UID")
                .append("\t")
                .append("accUID")
                .append("\t")
                .append("transDate")
                .append("\t")
                .append("category")
                .append("\t")
                .append("description")
                .append("\t")
                .append("amount")
                .append("\t")
                .append("type")
                .append("\t")
                .append("\n");

        for (int i = 0; i < transactionArray.size(); i++) {
            String simpleDate = new SimpleDateFormat("MM-dd-yyyy")
                    .format(transactionArray.get(i).getTransDate());
            arrayToFile
                    .append(transactionArray.get(i).getUID())
                    .append("\t")
                    .append(transactionArray.get(i).getAccUID())
                    .append("\t")
                    .append(simpleDate)
                    .append("\t")
                    .append(transactionArray.get(i).getCategory())
                    .append("\t")
                    .append(transactionArray.get(i).getDesc())
                    .append("\t")
                    .append(transactionArray.get(i).getAmt())
                    .append("\t")
                    .append(transactionArray.get(i).getType())
                    .append("\t")
                    .append("\n");
        }
        try {
            FileWriter fw = new FileWriter(transactions, false);
            fw.write(arrayToFile.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void writeBankAccountArray(ArrayList<BankAccount> wBAA) {
        //Soren Diehl
        //saves an ArrayList of accounts to tab delimited text file
        StringBuilder arrayToFile = new StringBuilder();
        baArray = wBAA;
        arrayToFile
                .append("UID")
                .append("\t")
                .append("userUID")
                .append("\t")
                .append("Name")
                .append("\t")
                .append("balance")
                .append("\t")
                .append("\n");
        for (int i = 0; i < baArray.size(); i++) {
            arrayToFile
                    .append(baArray.get(i).getUID())
                    .append("\t")
                    .append(baArray.get(i).getUserUID())
                    .append("\t")
                    .append(baArray.get(i).getName())
                    .append("\t")
                    .append(baArray.get(i).getBalance())
                    .append("\t")
                    .append("\n");
        }
        try {
            FileWriter fw = new FileWriter(bankaccounts, false);
            fw.write(arrayToFile.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void writeUserArray(ArrayList<User> wUA) {
        //Soren Diehl
        //saves an ArrayList of user to a tab delimited text file 
        StringBuilder arrayToFile = new StringBuilder();
        userArray = wUA;
        arrayToFile
                .append("UID")
                .append("\t")
                .append("firstName")
                .append("\t")
                .append("lastName")
                .append("\t")
                .append("middleInitial")
                .append("\t")
                .append("email")
                .append("\t")
                .append("password")
                .append("\t")
                .append("\n");
        int i;
        for (i = 0; i < userArray.size(); i++) {
            arrayToFile
                    .append(userArray.get(i).getUID())
                    .append("\t")
                    .append(userArray.get(i).getFirstName())
                    .append("\t")
                    .append(userArray.get(i).getLastName())
                    .append("\t")
                    .append(userArray.get(i).getMiddleInitial())
                    .append("\t")
                    .append(userArray.get(i).getEmail())
                    .append("\t")
                    .append(userArray.get(i).getPassword())
                    .append("\t")
                    .append("\n");
        }
        try {
            FileWriter fw = new FileWriter(users, false);
            fw.write(arrayToFile.toString());
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public ArrayList<BankAccount> readAccounts() {
        //Soren Diehl
        //Returns an ArrayList of bankaccounts populated from the 
        //bankaccounts.txt file
        try {
            Scanner sc = new Scanner(bankaccounts).useDelimiter("\\t");
            while (sc.hasNext()) {
                String toAdd = sc.next();
                if (toAdd.equals("UID")) {
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    if(sc.hasNextLine()){toAdd = sc.nextLine();}
                    toAdd = sc.next();
                }
                int UID = Integer.parseInt(toAdd);
                toAdd = sc.next();
                int userUID = Integer.parseInt(toAdd);
                toAdd = sc.next();
                String name = toAdd;
                toAdd = sc.next();
                double balance = Double.parseDouble(toAdd);
                if(sc.hasNextLine()){toAdd = sc.nextLine();}
                boolean accountCheck = true;
                int i;
                for (i = 0; i < baArray.size(); i++) {
                    if (baArray.get(i).getUID() == UID) {
                        accountCheck = false;
                    }
                }
                if (accountCheck) {
                    BankAccount newAccount = new BankAccount(UID, userUID, name,
                            balance);
                    baArray.add(newAccount);
                }
            }
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return baArray;
    }

    public int readUIDGen() {
        //Soren Diehl
        //returns the last used UID integer
        int UIDGen = 0;
        try {
            Scanner sc = new Scanner(UIDgenFile);
            String toAdd = sc.next();
            UIDGen = Integer.parseInt(toAdd);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return UIDGen;
    }

    public ArrayList<Transaction> readTransactions() {
        //Soren Diehl
        //returns an ArrayList of transactions populated from the 
        //transactions.txt file
        try {
            Scanner sc = new Scanner(transactions).useDelimiter("\\t");
            while (sc.hasNext()) {
                String toAdd = sc.next();
                if (toAdd.equals("UID")) {
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    if(sc.hasNextLine()){toAdd = sc.nextLine();}
                    toAdd = sc.next();
                }
                int UID = Integer.parseInt(toAdd);
                toAdd = sc.next();
                int accUID = Integer.parseInt(toAdd);
                toAdd = sc.next();
                SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
                Date transDate = new Date();
                try {
                    transDate = formatter.parse(toAdd);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                toAdd = sc.next();
                String category = toAdd;
                toAdd = sc.next();
                String desc = toAdd;
                toAdd = sc.next();
                double amt = Double.parseDouble(toAdd);
                toAdd = sc.next();
                String type = toAdd;
                if(sc.hasNextLine()){toAdd = sc.nextLine();}
                boolean transCheck = true;
                int i;
                for (i = 0; i < transactionArray.size(); i++) {
                    if (transactionArray.get(i).getUID() == UID) {
                        transCheck = false;
                    }
                }
                if (transCheck) {
                    Transaction newTrans = new Transaction(UID, accUID, 
                            transDate, category, desc, amt, type);
                    transactionArray.add(newTrans);
                }
            }
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return transactionArray;
    }

    public ArrayList<User> readUsers() {
        //Soren Diehl
        //scans the users file into an arrayList, verifies data isn't 
        //duplicated, and returns the arraylist
        try {
            Scanner sc = new Scanner(users).useDelimiter("\\t");
            while (sc.hasNext()) {
                String toAdd = sc.next();
                if (toAdd.equals("UID")) {
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    if(sc.hasNextLine()){toAdd = sc.nextLine();}
                    toAdd = sc.next();
                }
                int UID = Integer.parseInt(toAdd);
                toAdd = sc.next();
                String firstName = toAdd;
                toAdd = sc.next();
                String lastName = toAdd;
                toAdd = sc.next();
                String mi = toAdd;
                toAdd = sc.next();
                String email = toAdd;
                toAdd = sc.next();
                String password = toAdd;
                if(sc.hasNextLine()){toAdd = sc.nextLine();}
                boolean userCheck = true;
                int i;
                for (i = 0; i < userArray.size(); i++) {
                    if (userArray.get(i).getUID() == UID) {
                        userCheck = false;
                    }
                }
                if (userCheck) {
                    User newUser = new User(UID, firstName, lastName, mi, email,
                            password);
                    userArray.add(newUser);
                }
            }
            sc.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return userArray;
    }

    public User readCurrentUser() {
        //Shawn Conway
        //instantiates the current user with data from currentuser text file
        try {
            Scanner sc = new Scanner(currentuser).useDelimiter("\\t");
            while (sc.hasNext()) {
                String toAdd = sc.next();
                if (toAdd.equals("UID")) {
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    toAdd = sc.next();
                    if(sc.hasNextLine()){toAdd = sc.nextLine();}
                    toAdd = sc.next();

                }
                int UID = Integer.parseInt(toAdd);
                toAdd = sc.next();
                String firstName = toAdd;
                toAdd = sc.next();
                String lastName = toAdd;
                toAdd = sc.next();
                String mi = toAdd;
                toAdd = sc.next();
                String email = toAdd;
                toAdd = sc.next();
                String password = toAdd;
                if(sc.hasNextLine()){toAdd = sc.nextLine();}
                currentUser = new User(UID, firstName, lastName, mi, email,
                        password);

            }
        } catch (IOException ex) {

        }
        return currentUser;
    }

    /**
     * @return the users
     */
    public File getUsers() {
        //Soren Diehl
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(File users) {
        //Soren Diehl
        this.users = users;
    }

    /**
     * @return the transactions
     */
    public File getTransactions() {
        //Soren Diehl
        return transactions;
    }

    /**
     * @param transactions the transactions to set
     */
    public void setTransactions(File transactions) {
        //Soren Diehl
        this.transactions = transactions;
    }

    /**
     * @return the bank accounts
     */
    public File getBankaccounts() {
        //Soren Diehl
        return bankaccounts;
    }

    /**
     * @param bankaccounts the bank accounts to set
     */
    public void setBankaccounts(File bankaccounts) {
        //Soren Diehl
        this.bankaccounts = bankaccounts;
    }

}
