package sp2020.attachablesmartlock.Locks;


/**
 * This is a class for creating Lock objects.
 * Every lock has a lock ID that will be input on user registration.
 * The lock has 2 states: locked (isLocked = true) or unlocked (false).
 */

public class Lock {
    private int lockID;         // Pre-assigned to each device. Related to device's connection to network.
    private Boolean isLocked;  // Keeps track of whether lock is currently locked/unlocked.

    public Lock(int lockID) {
        this.lockID = lockID;
        this.isLocked = false; // When creating a new lock, default state is unlocked.
    }

    public int getLockID() {
        return lockID;
    }

    public Boolean isLocked() {
        return this.isLocked;   // False = unlocked. True = locked.
    }

    // Switches state of lock (locked <-> unlocked). Use when home screen lock button is clicked.
    public void switchLockState() {
        this.isLocked = !this.isLocked;
    }

    @Override
    public String toString() {
        String lockedOrUnlocked;

        if (isLocked) {
            lockedOrUnlocked = "Locked";
        }
        else {
            lockedOrUnlocked = "Unlocked";
        }

        return "Lock #" + lockID
                + ", " + lockedOrUnlocked;
    }

    public String lockStateToString() {
        if (this.isLocked) {
            return "locked";
        }
        else {
            return "unlocked";
        }
    }
}
