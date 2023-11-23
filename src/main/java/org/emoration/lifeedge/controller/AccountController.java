package org.emoration.lifeedge.controller;

import lombok.extern.slf4j.Slf4j;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.service.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/16
 */
@Slf4j
@RestController
@RequestMapping("/user/account")
public class AccountController {
    @Autowired
    UserServer userServer;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @PostMapping("/register")
    public ResponseResult<NullData> register(@RequestBody Map<String, Object> requestBody) {
        String username = (String) requestBody.get("username");
        String password = (String) requestBody.get("password");
        return userServer.register(username, password);
    }


    public ResponseResult<NullData> login(String username, String password) {
        return null;
    }


    public ResponseResult<NullData> deleteUser(String userId) {
        return null;
    }


    public ResponseResult<NullData> updateUserName(String userId, String username) {
        return null;
    }


    public ResponseResult<NullData> updateUserPassword(String userId, String password) {
        return null;
    }

    public ResponseResult<NullData> updateUserPicture(String userId, String pictureUrl) {
        return null;
    }
}
