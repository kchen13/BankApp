package model;

/**
 * @author Soren Diehl
 * @author Kelby Chen (formatting, commenting)
 */
public class User {
    //Soren Diehl

    private int UID;
    private String firstName;
    private String lastName;
    private String middleInitial;
    private String email;
    private String password;

    /**
     * Constructor
     *
     * @param iUID integer that holds user UID
     * @param iFirstName string that holds the first name
     * @param iLastName string that holds the last name
     * @param iMiddleInitial string that holds the middle initial
     * @param iEmail string that holds user email address
     * @param iPassword string that holds user password
     */
    public User(int iUID, String iFirstName, String iLastName,
            String iMiddleInitial, String iEmail, String iPassword) {
        //Soren Diehl
        UID = iUID;
        firstName = iFirstName;
        lastName = iLastName;
        middleInitial = iMiddleInitial;
        email = iEmail;
        password = iPassword;
    }

    /**
     * @return the UID
     */
    public int getUID() {
        //Soren Diehl
        return UID;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        //Soren Diehl
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        //Soren Diehl
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        //Soren Diehl
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        //Soren Diehl
        this.lastName = lastName;
    }

    /**
     * @return the middleInitial
     */
    public String getMiddleInitial() {
        //Soren Diehl
        return middleInitial;
    }

    /**
     * @param middleInitial the middleInitial to set
     */
    public void setMiddleInitial(String middleInitial) {
        //Soren Diehl
        this.middleInitial = middleInitial;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        //Soren Diehl
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        //Soren Diehl
        this.email = email;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        //Soren Diehl
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        //Soren Diehl
        this.password = password;
    }

}
