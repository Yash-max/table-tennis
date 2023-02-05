package com.yash.tabletennis.service;

import com.yash.tabletennis.dao.UserDao;
import com.yash.tabletennis.dto.LoginOrRegisterDto;
import com.yash.tabletennis.dto.ResponseWrapper;
import com.yash.tabletennis.entity.User;
import com.yash.tabletennis.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public ResponseWrapper loginUser(String email, String password) throws Exception {
        User user = null;
        try {
            user = userDao.findByUsername(email);
        } catch (Exception e) {
            throw new Exception("User Does Not Exist");
        }
        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Incorrect Password");
        }
        String token = JwtUtils.doGenerateToken(new HashMap<>(), user.getUsername());
        ResponseWrapper resp = new ResponseWrapper();
        resp.setStatus(true);
        resp.setMessage("Successful Login");
        resp.setCode(HttpStatus.OK.value());

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("token", token);
        resp.setData(responseMap);
        return resp;
    }

    public ResponseWrapper registerUser(LoginOrRegisterDto data) throws Exception {
        ResponseWrapper resp = new ResponseWrapper();
        try {
            userDao.findByUsername(data.getEmail());
        } catch (Exception e) {
            throw new Exception("Username Already In Use");
        }

        User newUser = new User();
        newUser.setUsername(data.getEmail());
        newUser.setPassword(passwordEncoder.encode(data.getPassword()));
        newUser = userDao.save(newUser);

        String token = JwtUtils.doGenerateToken(new HashMap<>(), newUser.getUsername());

        resp.setStatus(true);
        resp.setCode(HttpStatus.OK.value());
        resp.setMessage("Successfully Registered");

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("token", token);
        resp.setData(responseMap);

        return resp;
    }
}
