package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shawn Conway
 * @author Kelby Chen
 */
public class Transaction {

    private int UID, accUID;
    private Date transDate;
    private String category, desc, type;
    private double amt;
    private int accRecTransfer;
    private String accName;

    /**
     * Constructor
     *
     * @param iUid integer that holds transaction UID
     * @param iAccUid integer that hold the Account UID
     * @param iTransDate date that holds the transaction date
     * @param iCategory string that holds the category
     * @param iDesc string that holds the description
     * @param iAmt double that holds the amount
     * @param iType string that holds the transaction type
     */
    public Transaction(int iUid, int iAccUid, Date iTransDate, String iCategory,
            String iDesc, double iAmt, String iType) {
        //Shawn Conway
        //Kelby Chen
        UID = iUid;
        accUID = iAccUid;
        transDate =  iTransDate;
        category = iCategory;
        desc = iDesc;
        amt = iAmt;
        type = iType;
    }

    /**
     * @return the UID
     */
    public int getUID() {
        //Shawn Conway
        return UID;
    }

    /**
     * @return the accUID
     */
    public int getAccUID() {
        //Shawn Conway
        return accUID;
    }

    /**
     * @param accUID value is set to accUID
     */
    public void setAccUID(int accUID) {
        //Shawn Conway
        this.accUID = accUID;
    }

    /**
     * @return the transDate
     */
    public Date getTransDate() {
        //Shawn Conway
        return transDate;
    }

    /**
     * @param transDate value is set to transDate
     */
    public void setTransDate(Date transDate) {
        //Shawn Conway
        this.transDate = transDate;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        //Shawn Conway
        return category;
    }

    /**
     * @param category value is set to category
     */
    public void setCategory(String category) {
        //Shawn Conway
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDesc() {
        //Shawn Conway
        return desc;
    }

    /**
     * @param desc is set to description
     */
    public void setDesc(String desc) {
        //Shawn Conway
        this.desc = desc;
    }

    /**
     * @return the amount of transaction
     */
    public double getAmt() {
        //Shawn Conway
        return amt;
    }

    /**
     * @param amt is set to amount of transaction
     */
    public void setAmt(double amt) {
        //Shawn Conway
        this.amt = amt;
    }

    public String getType() {
        return type;
    }
    
    public void setAccName(String iAccName){
        accName = iAccName;
    }
    public String getAccName(){
        return accName;
    }
}
