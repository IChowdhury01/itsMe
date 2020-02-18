package sp2020.attachablesmartlock.Locks;

import java.util.Random;


// TODO: Might need to add lockID/code to User

public class Lock {
    private int lockID;     // Pre-assigned to each printed device.
    private int accessCode;   // Assigned on registration.
    private Boolean lockState;  // Keeps track of

    public Lock(int lockID) {
        Random lockCodeGenerator = new Random();

        this.lockID = lockID;
        this.accessCode = lockCodeGenerator.nextInt(999999);  // TODO: When lock is created, check if generated code is already in database.
    }

    public int getLockID() {
        return lockID;
    }

    public void setLockID(int lockID) {
        this.lockID = lockID;
    }

    public int getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(int accessCode) {
        this.accessCode = accessCode;
    }

    @Override
    public String toString() {
        return "Lock{" +
                "lockID=" + lockID +
                ", accessCode=" + accessCode +
                '}';
    }
}
