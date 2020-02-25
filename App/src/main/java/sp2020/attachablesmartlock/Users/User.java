package sp2020.attachablesmartlock.Users;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

import sp2020.attachablesmartlock.Locks.Lock;

/**
 * This class models ever user account.
 */
public class User {

    private Random idCreator = new Random();

    private int userID;         // 8-bit integer
    private String name;        // Full name
    private String profilePicURL;   // Path to uploaded profile picture
    private Lock usersLock;     // Every user has their own lock.

    private FriendsList usersFriendsList;    // User's friends list


    /**
     * How images will be handled.
     * Registration page has an upload button.
     * When a profile pic is uploaded, it's saved in RPI/uploadedPhotos
     * profilePicURL = string containing path to the file.
     */

    /**
     * Constructors
     * User can be created with:
     * Name, Lock ID (if no pfp uploaded during registration)
     * Name, Lock ID, filename of uploaded profile pic
     */

    // Note: one lock can be registered to multiple users.
    public User(String inputName, int inputLockID) {
        this.userID = idCreator.nextInt(99999998) + 1;  // Can't be 0;

        this.name = inputName;

        this.profilePicURL = System.getProperty("user.dir") + "RPI/uploadedPhotos/default.jpg";

        this.usersLock = new Lock(inputLockID); // Creates lock object for user.

        this.usersFriendsList = new FriendsList();
    }

    public User(String inputName, int inputLockID, String uploadedFileName) {
        this.userID = idCreator.nextInt(99999998) + 1;

        this.name = inputName;

        this.profilePicURL = System.getProperty("user.dir") + "RPI/uploadedPhotos/" + uploadedFileName;

        this.usersLock = new Lock(inputLockID); // Creates lock object for user.

        this.usersFriendsList = new FriendsList();
    }


    // Standard getters/setters


    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public Lock getUsersLock() {
        return usersLock;
    }

    public FriendsList getUsersFriendsList() {
        return usersFriendsList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public void setUsersLock(Lock usersLock) {
        this.usersLock = usersLock;
    }




    // Print user contents

    @Override
    public String toString() {
        return "\nName: " + name
                + "\nUser ID: " + userID
                + "\nPath to Profile Photo: " + profilePicURL
                + "\nLock ID: " + usersLock.getLockID()
                + "\nLock State: " + usersLock.isLocked();
    }


}
