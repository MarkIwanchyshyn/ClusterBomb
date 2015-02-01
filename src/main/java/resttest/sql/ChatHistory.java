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
public abstract class ChatHistory {
    private static Logger logger = Logger.getLogger(ChatHistory.class.getName());
    private static Connection conn;
    private static Statement stmt;
    private static DateFormat sqlDateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");


    static {
        try {
            InitialContext cxt = new InitialContext();
            DataSource ds = (DataSource) cxt.lookup( "java:/comp/env/jdbc/TestDB" );

            conn =  ds.getConnection();
            stmt = conn.createStatement();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static String getAll(){
        try {
            String total = "";
            ResultSet rs = stmt.executeQuery("SELECT test_swagcol FROM test.chat_history;");
            while(rs.next()){
                total += rs.getString("text")+ "\n";
            }
            return total;
        } catch (SQLException e) {
            logger.severe(e.toString());
        }
        return "ERROR";
    }

    public static String getLastHour(){
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
    }



    public static void sent(int user, String text){
        try {
            boolean sucess;
            String sqlToEx = "INSERT INTO `test`.`chat_history` (`user`, `sent`, `text`) VALUES (\'"+user+"\', \'"+sqlDateFormat.format(new Date())+"\', \'"+text+"\');";
           logger.info("about to run:  " + sqlToEx);
           sucess = stmt.execute(sqlToEx);

        } catch (SQLException e) {
            logger.severe(e.toString());
        }

    }



}
