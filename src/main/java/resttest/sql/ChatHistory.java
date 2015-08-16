package resttest.sql;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by Mark on 1/6/2015.
 */
public class ChatHistory {
    private Logger logger = Logger.getLogger(ChatHistory.class.getName());
    private Connection conn;
    private Statement stmt;
    private DateFormat sqlDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    private boolean ready = false;

    public ChatHistory(){
    	try {
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/TestDB" );

            conn =  ds.getConnection();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "show tables;" );
            
            if( stmt != null ){
            	ready = true;
            }
            
        }catch (Exception e){
        	logger.severe( e.toString() );
        	ready = false;
        }
    	
    }
    
    public boolean ready(){
    	return ready;
    }

    public String getAll(){
        if( ready){
	    	try {
	            String total = "";
	            ResultSet rs = stmt.executeQuery( "SELECT test_swagcol FROM test.chat_history;" );
	            while(rs.next()){
	                total += rs.getString("text")+ "\n";
	            }
	            return total;
	        } catch (SQLException e) {
	            logger.severe(e.toString());
	        }
	        return "ERROR";
        }else{
        	return "db not avalible\n";
        }
        
    }

    public String getLastHour(){
    	if( ready){
	        try {
	            String total = "";
	            Calendar cal = Calendar.getInstance();
	            cal.setTime(new Date());
	            cal.add(Calendar.HOUR_OF_DAY,-1);
	            String toEx = "SELECT text FROM test.chat_history WHERE sent > \"" + sqlDateFormat.format(cal.getTime()) + "\";";
	            logger.info("About to querry:  "+toEx);
	            ResultSet rs = stmt.executeQuery(toEx);
	            while(rs.next()){
	                total += rs.getString("text")+ "\n";
	            }
	            return total;
	        } catch (SQLException e) {
	            logger.severe(e.toString());
	            return "ERROR\n";
	        }
	    }else{
	    	return "db not avalible\n";
	    }
    }



    public void sent(int user, String text){
    	if( ready){
	    	try {
	            boolean sucess;
	            String sqlToEx = "INSERT INTO `test`.`chat_history` (`user`, `sent`, `text`) VALUES (\'"+user+"\', \'"+sqlDateFormat.format(new Date())+"\', \'"+text+"\');";
	           logger.info("about to run:  " + sqlToEx);
	           sucess = stmt.execute(sqlToEx);
	
	        } catch (SQLException e) {
	            logger.severe(e.toString());
	        }
    	}else{
    		logger.info( "Not saving sent message, db not ready" );
	    }
    }



}
