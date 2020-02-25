package sp2020.attachablesmartlock.Users;

import sp2020.attachablesmartlock.Timers.Timer;

/**
 * This class is for creating an object that represent's a user's friend.
 * The object tracks the user's ID, name and PFP (taken from the friend's User account object),
 * whether they have access to the user's lock, and how long they have access for.
 */
public class Friend {

    // These fields will be displayed in the UI
    private String name;
    private Boolean hasAccess;
    private String profilePicURL;
    private Timer accessTimer;    // Tracks remaining access time of friend.

    // Not shown in UI.
    private int UID;       // Matches user ID of added friend.
    private int tempAuthCode;     // Used for authentication before unlocking device.


    // Turns a user into a friend
    public Friend(User userToFriend) {
        this.UID = userToFriend.getUserID();
        this.name = userToFriend.getName();
        this.profilePicURL = userToFriend.getProfilePicURL();

        this.hasAccess = false; // By default, added friends don't have lock access.
        this.tempAuthCode = 0;
        this.accessTimer = new Timer(0,0,0);
    }

    /**
     * How our authentication system works
     * When a friend is added, tempAuthcode = 0;
     * When the user give his friend's access: tempAuthcode = user's lockID and timer starts.
     * When timer hits 0: tempAuthcode = 0;
     * When friend tries to unlock a device: check if friend's tempAuthcode == user's lockID
     */

    // Getters/Setters
    public Boolean getHasAccess() {
        return hasAccess;
    }

    public int getTempAuthCode() {
        return tempAuthCode;
    }

    public void setTempAuthCode(int tempAuthCode) {
        this.tempAuthCode = tempAuthCode;
    }

    public Timer getAccessTimer() {
        return accessTimer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicURL() {
        return profilePicURL;
    }

    public void setProfilePicURL(String profilePicURL) {
        this.profilePicURL = profilePicURL;
    }

    public int getUID() {
        return UID;
    }

    @Override
    public String toString() {
        if (hasAccess) {
            return name + ", " + hasAccessToString() + ", Remaining time: " +  accessTimer + "\n";
        }
        else {
            return name + ", " + hasAccessToString() + "\n";
        }
    }


    public String hasAccessToString() {
        if (hasAccess) {
            return "Currently has lock access";
        }
        else {
            return "Currently does not have lock access";
        }
    }

    public void grantAccess(int hours, int minutes, int seconds) {
        this.hasAccess = true;

        this.accessTimer.setTime(hours,minutes,seconds);
    }

    public void revokeAccess() {
        this.hasAccess = false;
        this.accessTimer.clear();
    }

}

