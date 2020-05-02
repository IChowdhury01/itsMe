package com.example.rushi.leds;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


public class Motor_Control extends AppCompatActivity {

    private Button btnUnlock, btnLock, btnDis;
    private TextView textView,sentView,receivedView;
    private ProgressDialog progress;
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    //SPP UUID. Look for it
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String MACaddress = null;
    private String deviceName = null;

    private InputStream inputStream;
    private byte buffer[];
    private boolean stopThread;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        MACaddress = intent.getStringExtra(MainActivity.EXTRA_ADDRESS);//receive the MAC address of the bluetooth device
        deviceName = intent.getStringExtra(MainActivity.EXTRA_NAME);//receive the name of the bluetooth device

        setContentView(R.layout.activity_control_device);

        btnUnlock = (Button)findViewById(R.id.buttonunlock);
        btnLock = (Button)findViewById(R.id.buttonlock);
        btnDis = (Button)findViewById(R.id.buttondisconnect);
        textView = (TextView)findViewById(R.id.textView2);
        sentView = (TextView)findViewById(R.id.sentView);

        new ConnectBT().execute();//Call the class to connect


        //commands to be sent to bluetooth
        btnUnlock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendUnlock();
            }
        });

        btnLock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sendLock();
            }
        });

        btnDis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Disconnect();//close connection
            }
        });
    }

    private void Disconnect()
    {
        if (btSocket!=null)//If the btSocket is busy
        {
            try
            {
                btSocket.close();//close connection
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
        finish();//return to the first layout

    }

    private void receiveData()
    {
        if (btSocket!=null)
        {
            try
            {
                do {
                    inputStream = btSocket.getInputStream();
                    SystemClock.sleep(1000);
                }while (inputStream==null);
                beginListenForData();
            }
            catch (IOException e)
            {
                msg("error in showing");
            }
        }
    }

    private void beginListenForData()
    {
        stopThread = false;
        buffer = new byte[1024];
        Thread thread  = new Thread(new Runnable()
        {
            public void run()
            {
                byte[] rawBytes = new byte[1024];
                int byteCount;
                while (!stopThread)
                {
                    try
                    {
                        byteCount = inputStream.read(rawBytes);
                        final String string = new String(rawBytes,0,byteCount);

                        runOnUiThread(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                receivedView.append(string+"\n");
                            }
                        });

                        stopThread = true;
                        inputStream = null;
                    }
                    catch (IOException ex)
                    {
                        stopThread = true;
                    }
                }
            }
        });
        thread.start();
    }

    private void sendUnlock()
    {
        String send = "UNLOCK";
        String prefix = "-> ";
        if (btSocket!=null)
        {
            try {
                if(send=="")
                    msg("No data found");
                else {
                        btSocket.getOutputStream().write(send.charAt(0));
                    btSocket.getOutputStream().write('\n');
                    }
                sentView.append(prefix+send.trim());
                }
            catch (IOException e)
            {
                msg("Error in sending data");
            }
            sentView.append("\n");
            receiveData();
        }
    }

    private void sendLock()
    {
        String send = "LOCK";
        String prefix = "-> ";
        if (btSocket!=null)
        {
            try {
                if(send=="")
                    msg("No data found");
                else {
                        btSocket.getOutputStream().write(send.charAt(0));
                    btSocket.getOutputStream().write('\n');
                    }
                sentView.append(prefix+send.trim());
                }
            catch (IOException e)
            {
                msg("Error in sending data");
            }
            sentView.append("\n");
            receiveData();
        }
    }

    // efficient way to call Toast
    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>// UI thread
    {
        private boolean ConnectSuccess = true;//if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(Motor_Control.this, "Connecting with "+ deviceName, "Please wait!!");//show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices)//while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice bluetoothDevice = myBluetooth.getRemoteDevice(MACaddress);//connects to the device's address and checks if it's available
                    btSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)//after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is "+deviceName +" a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                textView.setText("Connected with "+deviceName);
                msg("Successfully Connected with "+deviceName);
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
}
