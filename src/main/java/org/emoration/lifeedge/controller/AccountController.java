package org.emoration.lifeedge.controller;

import lombok.extern.slf4j.Slf4j;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.pojo.User;
import org.emoration.lifeedge.service.UserServer;
import org.emoration.lifeedge.service.WeChatLoginService;
import org.emoration.lifeedge.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
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
    @Autowired
    WeChatLoginService weChatLoginService;
    @Autowired
    TokenUtil tokenUtil;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    // FIXME 已弃用
    @PostMapping("/register")
    public ResponseResult<NullData> register(@RequestBody Map<String, Object> requestBody) {
        String username;
        String password;
        try {
            username = (String) requestBody.get("username");
            password = (String) requestBody.get("password");
            if (username == null || password == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("参数username或password错误");
        }

        return userServer.register(username, password);
    }


    // FIXME 已弃用
    @PostMapping("/login")
    public ResponseResult<Map<String, String>> login(@RequestBody Map<String, Object> requestBody) {
        String username;
        String password;
        try {
            username = (String) requestBody.get("username");
            password = (String) requestBody.get("password");
            if (username == null || password == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("参数username或password错误");
        }

        return userServer.login(username, password);
    }

    @GetMapping("/systemLogin")
    public ResponseResult<Map<String, String>> systemLogin() {
        return userServer.systemLogin();
    }

    @PostMapping("/wechatLogin")
    public ResponseResult<Map<String, String>> wechatLogin(@RequestBody Map<String, Object> requestBody) {
        String code;
        try {
            code = (String) requestBody.get("code");
            if (code == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("参数code错误");
        }

        return weChatLoginService.doSpringbootLoginWithWeChat(code);
    }

    @DeleteMapping("/delete")
    public ResponseResult<NullData> deleteUser(@RequestHeader("Authorization") String token) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if(userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("token错误");
        }

        return userServer.deleteUser(userId);
    }

    @GetMapping("/query")
    public ResponseResult<User> selectUser(@RequestHeader("Authorization") String token) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if(userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("token错误");
        }

        return userServer.selectUser(userId);
    }

    @PutMapping("/changeUsername")
    public ResponseResult<NullData> updateUserName(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> requestBody) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("token错误");
        }
        String newUsername;
        try {
            newUsername = (String) requestBody.get("newUsername");
            if (newUsername == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("参数newUsername错误");
        }

        return userServer.updateUserName(userId, newUsername);
    }

    @PutMapping("/changePassword")
    public ResponseResult<NullData> updateUserPassword(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> requestBody) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("token错误");
        }
        String oldPassword;
        String newPassword;
        try {
            oldPassword = (String) requestBody.get("oldPassword");
            newPassword = (String) requestBody.get("newPassword");
            if (oldPassword == null || newPassword == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("参数oldPassword或newPassword错误");
        }

        return userServer.updateUserPassword(userId, oldPassword, newPassword);
    }

    @PutMapping("/changePicture")
    public ResponseResult<NullData> updateUserPicture(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> requestBody) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("token错误");
        }
        String pictureUrl;
        try {
            pictureUrl = (String) requestBody.get("newPictureUrl");
            if (pictureUrl == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.fail("参数newPictureUrl错误");
        }

        return userServer.updateUserPicture(userId, pictureUrl);
    }
}
