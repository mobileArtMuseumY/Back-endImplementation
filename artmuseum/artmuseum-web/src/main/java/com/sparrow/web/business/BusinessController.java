package com.sparrow.web.business;

import com.sparrow.common.ServerResponse;
import com.sparrow.model.Business;
import com.sparrow.service.Business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessController {
    @Autowired
    private BusinessService businessService;

    @RequestMapping(value = "/register", method=RequestMethod.POST)
    public ServerResponse register(Business business){
        return businessService.save(business);
    }









}
