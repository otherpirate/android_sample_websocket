package codeweb.com.samplewebsocketclient;

import android.widget.TextView;

public class MessageListenerTextView implements MessageListenerInterface{

    private TextView txtView;
    MessageListenerTextView(TextView txtView){
        this.txtView = txtView;
    }

    @Override
    public void receiveMessage(String message) {
        txtView.append("\n" + message);
    }
}
