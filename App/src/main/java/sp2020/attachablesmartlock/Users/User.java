package sp2020.attachablesmartlock.Users;

import java.util.ArrayList;
import java.util.Random;

import sp2020.attachablesmartlock.Locks.Lock;

public class User {

    private Random idCreator = new Random();

    private int userID;         // 8-bit integer
    private String name;        // Full name
    private String profilePicURL;   // Path to uploaded profile picture
    private Lock usersLock;     // Every user has their own lock.
    private ArrayList<Friend> friendsList;   // List of friends


    /**
     * How images will be handled.
     * Registration page has an upload button.
     * When a profile pic is uploaded, it's saved in RPI/uploadedPhotos
     * profilePicURL = string containing path to the file.
     */

    /**
     * Constructors
     * User can be created with:
     * Name, Lock ID
     * Name, Lock ID, filename of uploaded profile pic
     */

    // Note: one lock can be registered to multiple users.
    public User(String inputName, int inputLockID) {
        this.userID = idCreator.nextInt(99999999);

        this.name = inputName;

        this.profilePicURL = System.getProperty("user.dir") + "\\RPI\\uploadedPhotos\\default.jpg";

        this.usersLock = new Lock(inputLockID); // Creates lock object for user.

        this.friendsList = new ArrayList<Friend>();
    }

    public User(String inputName, int inputLockID, String uploadedFileName) {
        this.userID = idCreator.nextInt(99999999);

        this.name = inputName;

        this.profilePicURL = System.getProperty("user.dir") + "\\RPI\\uploadedPhotos\\" + uploadedFileName;

        this.usersLock = new Lock(inputLockID); // Creates lock object for user.

        this.friendsList = new ArrayList<Friend>();
    }

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

    public ArrayList<Friend> getFriendsList() {
        return friendsList;
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

    public void addFriend (User userToAdd) {
        Friend newFriend = new Friend(userToAdd.name, profilePicURL);

        this.friendsList.add(newFriend);
    }

    public void removeFriend (Friend newFriend) {
        this.friendsList.remove(newFriend);
    }

    public void clearFriends() {
        this.friendsList.clear();
    }


    @Override
    public String toString() {
        return "User Info\nName: " + name
                + "\nUser ID: " + userID
                + "\nPath to Profile Photo: " + profilePicURL
                + "\nUser's Lock Information\nLock ID: " + usersLock.getLockID()
                + "\nLock State: " + usersLock.getLockState()
                + "\nFriends List" + friendsList;
    }
}
