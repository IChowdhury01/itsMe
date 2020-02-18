package sp2020.attachablesmartlock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
