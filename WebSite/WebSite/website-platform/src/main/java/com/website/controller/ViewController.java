package com.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/view", method = {RequestMethod.GET})
public class ViewController {

    @RequestMapping(value = "/businessregist")
    public String businessRegistView(){
        return "business/businessregist";
    }

}
