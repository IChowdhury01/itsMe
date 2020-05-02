package sp2020.attachablesmartlock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.provider.ContactsContract;
import android.widget.Button;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

import sp2020.attachablesmartlock.Locks.Lock;
import sp2020.attachablesmartlock.Timers.BoundedCounter;
import sp2020.attachablesmartlock.Timers.Timer;
import sp2020.attachablesmartlock.Users.Friend;
import sp2020.attachablesmartlock.Users.User;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runTests();
    }

    /**
     * Function that is called whenever the home screen LOCK button is clicked.
     * Creates a Toast object (on-screen notification) and shows it on the screen.
     */
    public void onLockButtonTap(View v) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Your device is now locked!", Toast.LENGTH_LONG);
        myToast.show();
    }



    /**
     * Function that is called whenever the home screen "Friends" button is clicked.
     * It will redirect the user to his friends list page.
     */
    public void onFriendButtonTap(View v) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Friend's list not implemented yet.", Toast.LENGTH_LONG);
        myToast.show();
    }

    public void onBluetoothTap(View v) {
        Intent intent = new Intent(this, Bluetooth.class);
        startActivity(intent);
    }

    public void onLogoTap(View v) {
        Intent intent = new Intent(this, showinfo.class);
        startActivity(intent);
        //Toast myToast = Toast.makeText(getApplicationContext(), "Friend's list not implemented yet.", Toast.LENGTH_LONG);
        //myToast.show();
    }

    /**
     * Function that is called whenever the user clicks his profile picture on the home screen.
     * It will redirect the user to his User profile page.
     */
    public void onProfilePicTap(View v) {

        Intent intent = new Intent(this, Userprofile.class);
        startActivity(intent);

    }


    /**
     * This function runs all code-testing methods.
     */
    public static void runTests() {
        Log.d("myTest", "This will print if the app is successfully run.");

        Random randomizer = new Random();   // If RNG is used for some tests

        // Comment out tests after they're working.
//        testLock(randomizer);
//        testCounter();
//        testTimer();
//        testFriend();
//        testUsers();
    }


    // Testing methods

    public static void testLock(Random randomizer) {
        // Test lock class.
        Lock myLock = new Lock(randomizer.nextInt(9999999));
        Log.d("myTestClass L", myLock.toString());

        Log.d("myTestClass L", "Lock ID is " + myLock.getLockID() + " and lock state is " + myLock.isLocked());

        Log.d("myTestClass L", myLock.lockStateToString());

        myLock.switchLockState();

        Log.d("myTestClass L", myLock.toString());
    }

    public static void testCounter() {
        BoundedCounter myCounter = new BoundedCounter(59);

        Log.d("myTestClass C", myCounter.toString());
        Log.d("myTestClass C", "Counter's current value is " + myCounter.getValue());

        myCounter.setValue(24);
        Log.d("myTestClass C", myCounter.toString());
        myCounter.setValue(60);
        Log.d("myTestClass C", myCounter.toString());
        myCounter.setValue(-2);
        Log.d("myTestClass C", myCounter.toString());

        myCounter.setValue(1);
        Log.d("myTestClass C", myCounter.toString());
        myCounter.decrease();
        Log.d("myTestClass C", myCounter.toString());
        myCounter.decrease();
        Log.d("myTestClass C", myCounter.toString());

        myCounter.setValue(58);
        Log.d("myTestClass C", myCounter.toString());
        myCounter.increase();
        Log.d("myTestClass C", myCounter.toString());
        myCounter.increase();
        Log.d("myTestClass C", myCounter.toString());
    }

    public static void testTimer() {
        Timer myTimer = new Timer(0, 0, 0);
        Log.d("myTestClass Timer", myTimer.toString());

        myTimer.setTime(23, 59, 59);
        Log.d("myTestClass Timer", myTimer.toString());
        Log.d("myTestClass Timer", "Seconds: " + myTimer.getTimeInSeconds());

        myTimer.tickDown();
        Log.d("myTestClass Timer", myTimer.toString());

        myTimer.setTime(23, 59, 0);
        myTimer.tickDown();
        Log.d("myTestClass Timer", myTimer.toString());

        myTimer.setTime(23, 0, 0);
        myTimer.tickDown();
        Log.d("myTestClass Timer", myTimer.toString());

        myTimer.setTime(0, 0, 0);
        myTimer.tickDown();
        Log.d("myTestClass Timer", myTimer.toString());
    }

    public static void testFriend() {
        User user1 = new User("User1", 00001);
        User user2 = new User("User2", 00002);

        Friend addedUser = new Friend(user1);   // Creates a friend object representing user2.
        Friend addedUserv2 = new Friend(user2);  // method 2

        addedUserv2.grantAccess(1, 23, 5);
        Log.d("myTestClass Friend", addedUser.toString());
        Log.d("myTestClass Friend", addedUserv2.toString());

        addedUserv2.revokeAccess();
        Log.d("myTestClass Friend", addedUserv2.toString());
    }

    public static void testUsers() {
        // Create users
        User user1 = new User("User1", 00001);
        User user2 = new User("User2", 00002, "myPic.jpg");
        User user3 = new User("User3", 00003);

        Log.d("myTestClass User", user1.toString());
        Log.d("myTestClass User", user2.toString());

        // Add friends
        Log.d("myTestClass User", user1.getUsersFriendsList().toString());
        user1.getUsersFriendsList().addFriend(user2);
        user1.getUsersFriendsList().addFriend(user3);
        Log.d("myTestClass User", user1.getUsersFriendsList().toString());

        // Remove friends
        user1.getUsersFriendsList().removeFriend(user3);
        Log.d("myTestClass User", user1.getUsersFriendsList().toString());

        // Clear list
        user1.getUsersFriendsList().clearFriends();
        Log.d("myTestClass User", user1.getUsersFriendsList().toString());
    }
}
