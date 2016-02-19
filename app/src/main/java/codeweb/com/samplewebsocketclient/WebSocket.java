package codeweb.com.samplewebsocketclient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class WebSocket {

    private WebSocketClient webSocketClient;

    WebSocket(String address, final MessageListenerInterface messageListener){
        webSocketClient = new WebSocketClient(buildURI(address)) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
            }

            @Override
            public void onMessage(final String s) {
                messageListener.receiveMessage(s);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
            }

            @Override
            public void onError(Exception ex) {
            }
        };
    }

    public void connect(){
        webSocketClient.connect();
    }

    public void disconnect(){
        webSocketClient.close();
    }

    public void sendMessage(String message){
        webSocketClient.send(message);
    }

    private URI buildURI(String address){
        URI uri;
        try {
            uri = new URI(address);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }

        return uri;
    }
}
