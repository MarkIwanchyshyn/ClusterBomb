package resttest.messages;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by Mark on 2/7/2015.
 */
public class message {
    private static Logger logger = Logger.getLogger(message.class.getName());
    private static ObjectMapper mapper = new ObjectMapper();

    public String type;
    public Object data;



    public static message parseMessage(String input){
        try {
            return mapper.readValue(input,message.class);
        } catch (IOException e) {
            logger.severe(e.toString());
            throw new RuntimeException(e);
        }
    }

    public String toJSON(){
        try {
            return mapper.writeValueAsString(this);
        } catch (IOException e) {
            logger.severe(e.toString());
            throw new RuntimeException(e);
        }
    }

}
