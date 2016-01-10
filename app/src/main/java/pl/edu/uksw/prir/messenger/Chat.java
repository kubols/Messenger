package pl.edu.uksw.prir.messenger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Chat extends AppCompatActivity {

    private TextView msgView;
    private EditText msgEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        msgView = (TextView)findViewById(R.id.msgTextView);
        msgEditText = (EditText) findViewById(R.id.msgEditText);
    }

    private void sendMessage(View view){
        String msg = msgEditText.getText().toString();
    }
}
