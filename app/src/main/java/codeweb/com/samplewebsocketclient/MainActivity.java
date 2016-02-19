package codeweb.com.samplewebsocketclient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    final String ADDRESS = "ws://192.168.1.10:8080/ws";

    private EditText editMessage;
    private Button btn_send;
    private TextView txtMessages;

    private WebSocket webSocketConnection;
    private MessageListenerInterface messageListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineUIWidgets();
        connectWebSocket(ADDRESS);
        webSocketConnection.connect();
    }

    @Override
    protected void onDestroy() {
        webSocketConnection.disconnect();
        super.onDestroy();
    }

    private void connectWebSocket(String address){
        messageListener = new MessageListenerTextView(txtMessages);
        webSocketConnection = new WebSocket(address, messageListener);
    }

    private void defineUIWidgets(){
        editMessage = (EditText) findViewById(R.id.ed_msg);
        btn_send = (Button) findViewById(R.id.btn_send);
        txtMessages = (TextView) findViewById(R.id.txt_msg);

        setUpButtons();
    }

    private void setUpButtons(){
        btn_send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {
        String message = editMessage.getText().toString().trim();
        if (TextUtils.isEmpty(message)) {
            return;
        }

        webSocketConnection.sendMessage(message);
        editMessage.setText("");
    }
}
