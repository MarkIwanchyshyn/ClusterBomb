package resttest.webSocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class WsChatServlet {

    static volatile List<Session> previous = new LinkedList<>();

    @OnOpen
    public void addPerson(Session session){
        if(!previous.contains(session)){
            previous.add(session);
        }
    }

    @OnClose
    public void removePerson(Session session){
        previous.remove(session);
    }

    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        try {
            for(Session i : previous){
                if (i.isOpen()) {
                    try {
                        i.getBasicRemote().sendText(msg, last);
                    }catch (IOException e){
                        i.close();
                    }

                }
            }

        } catch (IOException e) {
            // Ignore
        }
    }


}