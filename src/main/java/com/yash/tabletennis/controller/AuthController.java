package com.yash.tabletennis.controller;

import com.yash.tabletennis.dto.LoginOrRegisterDto;
import com.yash.tabletennis.dto.RequestWrapper;
import com.yash.tabletennis.dto.ResponseWrapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseWrapper login(@RequestBody RequestWrapper<LoginOrRegisterDto> request) {
        ResponseWrapper resp = new ResponseWrapper();
        try {
            if (request.getData().getEmail().equals("khandelwalyash2710@gmail.com") && request.getData().getPassword().equals("Yash@123")) {
                resp.setStatus(true);
                resp.setMessage("Successful Login");
                resp.setCode(HttpStatus.OK.value());
            } else {
                resp.setStatus(false);
                resp.setMessage("Invalid Credentials");
                resp.setCode(HttpStatus.UNAUTHORIZED.value());
            }
        } catch (Exception e) {
            resp.setStatus(false);
            resp.setMessage("Internal Server Issue");
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        resp.setData(new HashMap<>());
        return resp;
    }
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseWrapper signup(@RequestBody RequestWrapper<LoginOrRegisterDto> request) {
        ResponseWrapper resp = new ResponseWrapper();
        resp.setStatus(true);
        resp.setCode(HttpStatus.OK.value());
        resp.setMessage("Successful Login");
        resp.setData(new HashMap<>());
        return resp;
    }
}
