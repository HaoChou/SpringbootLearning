package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/4/25.
 */
@Controller
@EnableAutoConfiguration
public class hello {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }


    @RequestMapping("/test")
    @ResponseBody
    String test(String arg1,String arg2) {
        return "Hello World!:"+arg1+":"+arg2;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(hello.class, args);
    }
}