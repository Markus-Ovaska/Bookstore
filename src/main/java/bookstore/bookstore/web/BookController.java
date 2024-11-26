package bookstore.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class BookController {

    @RequestMapping("*")
    @ResponseBody
    public String index()
    {
        return "Bookstore";
    }
    
    }
