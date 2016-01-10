package pl.edu.uksw.prir.messenger;

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
    }

    private void sendMessage(View view){
        String msgStr = msgEditText.getText().toString();
        Message msg = new Message(msgStr, from, id, to);
        try {
            msg.messageString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
