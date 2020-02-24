package sp2020.attachablesmartlock.Users;

import sp2020.attachablesmartlock.Timers.Timer;

public class Friend {

    // Fields that will be displayed in UI
    private String name;
    private Boolean hasAccess;
    private String profilePicURL;

    private int tempAuthCode;     // Used for authentication unlocking device.
    private Timer accessTimer;    // Tracks remaining access time of friend.

    public Friend(String name, String profilePicURL) {
        this.name = name;       // These will be retrieved from the database.
        this.profilePicURL = profilePicURL;

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


    public Boolean getHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(Boolean hasAccess) {

        this.hasAccess = hasAccess;
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

    public void setAccessTimer(Timer accessTimer) {
        this.accessTimer = accessTimer;
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

    @Override
    public String toString() {
        return "Friend Info\n" +
                "\nName: " + name+
                "\nAccess Priveleges: " + hasAccess +
                "\nTime Remaining: " + accessTimer +
                "\nAuthorization Code: " + tempAuthCode;
    }
}
