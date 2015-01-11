package resttest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import resttest.sql.TestConnector;

@Controller
public class counter {
    long count = 0L;

    public counter(){
        System.out.println("created lol");
    }

    @RequestMapping( value="/test", method = RequestMethod.GET)
    @ResponseBody
    public String getCount(){
        System.out.println("got get request");
        return "{ sweg: " + count++ +","+
                "all: " + TestConnector.getAll() +
                "}";
    }

}