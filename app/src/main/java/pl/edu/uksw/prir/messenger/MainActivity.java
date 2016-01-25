package pl.edu.uksw.prir.messenger;

/**
 *
 * @author Wojciech Pokora
 * @author Jakub Pawlak
 * @author Patryk Szewczyk
 * @author Katarzyna Wiater
 * @author Agnieszka Musiał
 * @author Michał Darkowski
 */


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button chatButton;
    private String from, id, to = "";

    public static final String ACTION_NEW_MSG = "pl.edu.uksw.prir.messenger.NEW_MSG";
    public static final String MSG_FIELD = "message";



    private IntentFilter filter = new IntentFilter("pl.edu.uksw.prir.messenger.Chat");




    public final static String TAG = "ActivityMain";
    public static final String PKG = "info.lamatricexiste.network";
    public static SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatButton = (Button) findViewById(R.id.chatButton);
        //showToast("cos");
        Context context = getApplicationContext();

        Log.i("cos", "start serw");
        try {
            EchoWorker worker = new EchoWorker(context);
            new Thread(worker).start();
            new Thread(new NioServer(InetAddress.getByName("192.168.2.103"), 9090, worker)).start();
            Log.i("cos", "start serw udany");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("cos", "start serw nieudany");
            Toast.makeText(context, "start serw nieuadny", Toast.LENGTH_LONG).show();;

        }

        // startService(new Intent(this, MessangerService.class));

    }




    public void openChat(View view){
        to = chatButton.getText().toString();

        Intent intent = new Intent(this, Chat.class);
        intent.putExtra("from", from);
        intent.putExtra("id", id);
        intent.putExtra("to", to);
        startActivity(intent);
    }


}

