package pl.edu.uksw.prir.messenger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileNotFoundException;

public class Chat extends AppCompatActivity {

    private TextView msgView;
    private EditText msgEditText;
    private Button sendButton;
    private String from, to, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        msgView = (TextView)findViewById(R.id.msgTextView);
        msgEditText = (EditText) findViewById(R.id.msgEditText);

        Intent intent = getIntent();
        from = intent.getStringExtra("from");
        id = intent.getStringExtra("id");
        to = intent.getStringExtra("to");
    }

    private void sendMessage(View view){
        String msgStr = msgEditText.getText().toString();
        if (!msgStr.equals(null)) {
            Message msg = new Message(msgStr, from, id, to);
            try {
                msg.messageString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
