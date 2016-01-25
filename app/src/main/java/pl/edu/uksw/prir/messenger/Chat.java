package pl.edu.uksw.prir.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.net.InetAddress;

public class Chat extends AppCompatActivity {

    private TextView msgView;
    private EditText msgEditText;
    private Button sendButton;
    private String from, to, id;
    public Context mojcontext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mojcontext  = getApplicationContext();

        msgView = (TextView)findViewById(R.id.msgTextView);
        msgEditText = (EditText) findViewById(R.id.msgEditText);

        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        id = intent.getStringExtra("id");
        to = intent.getStringExtra("to");
        registerReceiver(broadcast, filter);
    }
    public void sendMessage(View view){
        String msgStr = msgEditText.getText().toString();
        if (!msgStr.equals(null)) {
            new LongOperation().execute(msgStr);

            //  Message msg = new Message(msgStr, from, id, to);
            //  try {
            //     msg.messageString();
            // } catch (FileNotFoundException e) {
            //     e.printStackTrace();
            //  }
        }
    }

    private IntentFilter filter = new IntentFilter("pl.edu.uksw.prir.messenger.Chat");

    private BroadcastReceiver broadcast = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            //  updateResults(intent.getStringExtra("result"));
            Toast toast = Toast.makeText(mojcontext, "dostalem receiva", Toast.LENGTH_LONG);
            Log.i("cos","odbieram broadcast");
            msgView.setText(intent.getStringExtra("result"));
            // msgView.setText("cos");

        }
    };


    public class LongOperation extends AsyncTask<String, Void, String> {

        Context context = getApplicationContext();
        @Override
        protected String doInBackground(String... params) {
            try {
                NioClient client = new NioClient(InetAddress.getByName("192.168.2.103"), 9090);
                //NioClient client = new NioClient(InetAddress.getByName("www.google.com"), 80);

                Thread t = new Thread(client);
                t.setDaemon(true);
                t.start();
                RspHandler handler = new RspHandler();
                client.send(params[0].getBytes(), handler);
                handler.waitForResponse();
                Log.i("cos", "start klient udany");
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("cos", "start klient nieudany");

            }
            Log.i("cos", "executed");
            return "Executed";
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }

}
