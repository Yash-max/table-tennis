package com.yash.tabletennis.controller;

import com.yash.tabletennis.dto.LoginOrRegisterDto;
import com.yash.tabletennis.dto.RequestWrapper;
import com.yash.tabletennis.dto.ResponseWrapper;
import com.yash.tabletennis.service.AuthService;
import com.yash.tabletennis.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseWrapper login(@RequestBody RequestWrapper<LoginOrRegisterDto> request) throws Exception {
        ResponseWrapper resp = new ResponseWrapper();
        try {
            resp = authService.loginUser(request.getData().getEmail(), request.getData().getPassword());
        } catch (Exception e) {
            resp.setStatus(false);
            resp.setMessage(e.getMessage());
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setData(new HashMap<>());
        }
        return resp;
    }
    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public ResponseWrapper signup(@RequestBody RequestWrapper<LoginOrRegisterDto> request) {
        ResponseWrapper resp = new ResponseWrapper();
        try {
            resp = authService.registerUser(request.getData());
        } catch (Exception e) {
            resp.setStatus(false);
            resp.setMessage(e.getMessage());
            resp.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setData(new HashMap<>());
        }
        return resp;
    }
}
