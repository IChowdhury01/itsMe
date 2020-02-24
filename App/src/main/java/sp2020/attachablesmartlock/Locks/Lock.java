package sp2020.attachablesmartlock.Locks;

import java.util.Random;


public class Lock {
    private int lockID;         // Pre-assigned to each printed device.
    private Boolean lockState;  // Keeps track of whether lock is currently locked/unlocked.

    public Lock(int lockID) {
        this.lockID = lockID;
        this.lockState = false; // When creating a new lock, default state is unlocked.
    }

    public int getLockID() {
        return lockID;
    }

    public void setLockID(int lockID) {
        this.lockID = lockID;
    }

    public Boolean getLockState() {
        return lockState;
    }

    public void setLockState(Boolean lockState) {
        this.lockState = lockState;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "lockID=" + lockID +
                ", lockState=" + lockState +
                '}';
    }
}
