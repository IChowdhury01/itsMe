package sp2020.attachablesmartlock.Users;

import java.util.Random;

import sp2020.attachablesmartlock.Locks.Lock;

class User {

    private int userID;     // 8-bit integer
    private String name;    // Full name

    private Lock usersLock; // Every user has their own lock.

    // TODO: Make the constructors check the database if the userID
    //  is available, before creating the account.

    // Create user, given a specific ID and name.
    public User(int userID, String name, int inputLockID) {
        this.userID = userID;
        this.name = name;

        this.usersLock = new Lock(inputLockID); // Creates lock object for user. Assigns lockID, accessCode, and "unlocked" state.
    }

    // Create user with random ID, given name.
    public User(String name) {
        Random idCreator = new Random();

        this.name = name;
        this.userID = idCreator.nextInt(99999999);
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User Info\nName: " + name + "\nUser ID: " + userID;
    }
}
