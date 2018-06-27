package demo.controller;

import demo.service.ServiceTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class ControllerTemplate {

    @Autowired
    ServiceTemplate serviceTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public String send() {
        serviceTemplate.send();
        return "success";
    }
}
