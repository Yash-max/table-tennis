package com.yash.tabletennis.controller;

import com.yash.tabletennis.dto.ResponseWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseWrapper getDashboard() {
        ResponseWrapper wrapper = new ResponseWrapper();
        wrapper.setMessage("Welcome to my world MJ 25 Jan");
        wrapper.setStatus(true);
        return wrapper;
    }

}
