package resttest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import resttest.sql.TestConnector;

@Controller
@RequestMapping("/count")
public class counter {
    long count = 0L;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String getCount(){
        return "{ sweg: " + count++ +","+
                "all: " + TestConnector.getAll() +
                "}";
    }

}