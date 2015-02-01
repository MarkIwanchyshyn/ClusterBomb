package resttest.webSocket;

import resttest.sql.ChatHistory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class WsChatServlet {
    private static Logger logger = Logger.getLogger(WsChatServlet.class.getName());

    static volatile List<Session> previous = new LinkedList<>();

    static final String connectedMsg = "!USER CONNECTED! ";
    static final String leaveMsg = "!USER DISCONNECTED! ";

    @OnOpen
    public void addPerson(Session session){
        if(!previous.contains(session)){
            logger.info("adding new session "+session.getId());

            previous.add(session);

            logger.info("sending last 1 hour");
            try {
                session.getBasicRemote().sendText(ChatHistory.getLastHour(),true);
            } catch (IOException e) {
                logger.severe(e.toString());
            }
            sendToAll(session, connectedMsg + session.getId());

        }
    }

    @OnClose
    public void removePerson(Session session){
        previous.remove(session);
        logger.info("removing session " + session.getId());
        sendToAll(session, leaveMsg + session.getId());
    }

    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        sendToAll(session,msg);
    }

    public static int getId(Session session){
        return Integer.parseInt(session.getId(),16);
    }

    private void sendToAll(Session session,String msg){
        try {
            synchronized (previous) {
                for (Session i : previous) {
                    if (i.isOpen()) {
                        try {
                            i.getBasicRemote().sendText(msg);
                        } catch (IOException e) {
                            i.close();
                        }
                    }
                }
            }
        } catch (IOException e) {
            logger.severe(e.toString());
        }
        ChatHistory.sent(getId(session),msg);
    }




}