package sp2020.attachablesmartlock;

import androidx.appcompat.app.AppCompatActivity;

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



        // TESTING
        Log.d("TAG1", "This will print when the app is run.");

        // To make random numbers.
        Random randomizer = new Random();

        // Create objects from each class.
        User myFirstUser = new User ("Ivan Chowdhury", 12345678);
        User mySecondUser = new User ("Sam Keene", 1111111);
        Friend sampleFriend = new Friend("Friend 1", "");
        Lock sampleLock = new Lock(0000011);
        BoundedCounter sampleCounter = new BoundedCounter(30);
        Timer sampleTimer = new Timer(3,25,30);

        // Test objects



    }

    /**
     *  Function that is called whenever the LOCK/UNLOCK button is clicked.
     *  Creates a Toast object (on-screen notification) and shows it on the screen.
      */

    public void onLockButtonTap(View v) {
        Toast myToast = Toast.makeText(getApplicationContext(), "Your device is now locked!", Toast.LENGTH_LONG);
        myToast.show();
    }


}
