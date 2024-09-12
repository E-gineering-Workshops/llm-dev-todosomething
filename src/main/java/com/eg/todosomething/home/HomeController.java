package com.eg.todosomething.home;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    String podName = "";
    String person = "";
    public HomeController(Environment environment) {
        person = "Hello " + environment.getProperty("PERSON","you");
        podName = "Served from " + environment.getProperty("POD_NAME","default");
    }



    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", person);
        model.addAttribute("podname", podName);
        model.addAttribute("foo", "bar");
        return "index";
    }
}
