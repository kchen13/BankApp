package model;

/**
 * @author Kelby Chen
 */
public class BankAccount { //Created by Kelby Chen

    private int UID;
    private int userUID;
    private String name;
    private double balance;

    /**
     * Constructor
     *
     * @param iUID integer that holds the bank account UID
     * @param iUserUID integer that holds user UID
     * @param iName string that holds name of bank account
     * @param iBalance double that holds account balance
     */
    public BankAccount(int iUID, int iUserUID, String iName, double iBalance) {
        // Kelby Chen
        UID = iUID;
        userUID = iUserUID;
        name = iName;
        balance = iBalance;
    }

    /**
     * Deposits amount to bank account
     *
     * @param iAmt amount to deposit
     */
    public void deposit(double iAmt) {
        //Shawn Conway
        //Kelby Chen
        setBalance(getBalance() + iAmt);
    }

    /**
     * Withdraws amount from bank account
     *
     * @param iAmt amount to withdraw
     */
    public void withdraw(double iAmt) {
        //Shawn Conway
        //Kelby Chen
        setBalance(getBalance() - iAmt);
    }

    /**
     * @return the UID
     */
    public int getUID() {
        //Kelby Chen
        return UID;
    }

    /**
     * @return the user UID
     */
    public int getUserUID() {
        //Kelby Chen
        return userUID;
    }

    /**
     * @param userUID sets the userUID
     */
    public void setUserUID(int userUID) {
        //Kelby Chen
        this.userUID = userUID;
    }

    /**
     * @return the name of the bank account
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets bank account name
     */
    public void setName(String name) {
        //Kelby Chen
        this.name = name;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        //Kelby Chen
        return balance;
    }

    /**
     * @param balance sets bank account balance
     */
    public void setBalance(double balance) {
        //Kelby Chen
        this.balance = balance;
    }

    /**
     * @param UID incoming user UID to test
     * @return boolean considering user UID
     */
    public boolean userUIDcheck(int UID) {
        //Kelby Chen
        return this.UID == UID;
    }
}
