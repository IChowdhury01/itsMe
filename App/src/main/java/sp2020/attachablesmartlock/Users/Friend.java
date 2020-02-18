package sp2020.attachablesmartlock.Users;

import sp2020.attachablesmartlock.Timers.Timer;

class Friend extends User {

    private Boolean hasAccess;
    private int tempAuthCode;
    private Timer accessTimer;    // Timer object tracks remaining access time of friend.

    // When lock access is granted to a friend, Friend's tempAuthCode value will be equal to
    // User lock's accessCode for a limited time.
    // When a user tries to open someone else's lock, check if tempAuthCode == accessCode

    public Friend(String name) {
        super(name);
        this.hasAccess = false; // By default, newly added friends don't have access, have no tempCode, and have timer is set to 0;
        this.tempAuthCode = 0;
        this.accessTimer = new Timer(0,0,0);
    }

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

    @Override
    public String toString() {
        return "Friend Info\n" +
                "User ID: " + this.getUserID() +
                "\nName: " + this.getName()+
                "\nAccess Priveleges: " + this.hasAccess +
                "\nTime Remaining: " + this.accessTimer;
    }
}
