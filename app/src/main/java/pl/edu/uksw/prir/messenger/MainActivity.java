package pl.edu.uksw.prir.messenger;

/**
 *
 * @author Wojciech Pokora
 * @author Jakub Pawlak
 * @author Patryk Szewczyk
 * @author Katarzyna Wiater
 * @author Agnieszka Musiał
 * @author Michał Darkowski
 *
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button chatButton;
    private String from, id, to;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent serviceIntent = new Intent(this, MessangerService.class);
        chatButton = (Button)findViewById(R.id.chatButton);
    }

    private void openChat(View view){
        to = chatButton.getText().toString();

        Intent intent = new Intent(this, Chat.class);
        intent.putExtra("from", from);
        intent.putExtra("id", id);
        intent.putExtra("to", to);
        startActivity(intent);
    }
}
