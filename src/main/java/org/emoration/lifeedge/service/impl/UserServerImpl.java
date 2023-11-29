package org.emoration.lifeedge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.emoration.lifeedge.common.CodeConstants;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.pojo.User;
import org.emoration.lifeedge.pojo.mapper.UserMapper;
import org.emoration.lifeedge.service.UserServer;
import org.emoration.lifeedge.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author czh
 * @Description 用户
 * @Date 2023/11/16
 */
@Service
public class UserServerImpl implements UserServer {

    @Autowired
    UserMapper userMapper;
    //    @Autowired
//    AuthenticationManager authenticationManager;
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public ResponseResult<NullData> register(String username, String password) {
        return ResponseResult.fail("注册功能已经被弃用，请使用微信登录");
//        if (!PasswordUtil.valid(password)) {
//            return ResponseResult.fail("密码不符合规范");
//        }
//        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
//        userQueryWrapper.eq("username", username);
//        if (userMapper.selectOne(userQueryWrapper) != null) {
//            return ResponseResult.fail("用户名被占用");
//        }
//        User user = new User();
//        user.setUsername(username);
//        user.setPassword(PasswordUtil.encode(password));
//        if (userMapper.insert(user) == 1) {
//            return ResponseResult.success("成功注册");
//        } else {
//            return ResponseResult.fail("服务器未知错误");
//        }
    }

    @Override
    public ResponseResult<Map<String, String>> login(String username, String password) {
        return ResponseResult.fail("登陆功能已经被弃用，请使用微信登录");
////        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
////        try {
//            QueryWrapper<User> wrapper = new QueryWrapper<>();
//            wrapper.eq("username", username);
//            User user = userMapper.selectOne(wrapper);
////            if(redisUtil.hasKey("freeze:" + username))
////                return new ResponseResult<>(CodeConstants.CODE_USER_EXCEPTION, "该用户已经被冻结");
////            authenticationManager.authenticate(authenticationToken);
////            if(redisUtil.hasKey("fail_login : " + username))
////                redisUtil.delete("fail_login : " + username);
//            String jwt = tokenUtil.getTokenByUserId(user.getId());
//            HashMap<String, String> map = new HashMap<>();
//            map.put("token", jwt);
////            if (Objects.equals(userMapper.selectRoleByUserId(user.getUserId()), "ROLE_MANAGER"))
////                map.put("role", "2");
////            else
////                map.put("role", "1");
//            return new ResponseResult<>(CodeConstants.CODE_SUCCESS, "登陆成功", map);
////        } catch (AuthenticationException e) {
//////            if (redisUtil.hasKey("fail_login : " + username)) {
//////                redisUtil.set("fail_login : " + username, (Integer) redisUtil.get("fail_login : " + username) + 1);
//////                if ((Integer) redisUtil.get("fail_login : " + username) > ParameterConstants.MAX_NUMBER_OF_FAIL) {
//////                    redisUtil.set("freeze:" + username, "true", 3600);
//////                    return new ResponseResult<>(CodeConstants.CODE_USER_EXCEPTION, "该用户已达最大密码错误次数,已经被暂时冻结账户");
//////                }
//////            } else {
//////                redisUtil.set("fail_login : " + username, 1);
//////            }
////        } finally {
////
////        }
//
////        return new ResponseResult<>(CodeConstants.CODE_UNAUTHORIZED, "登陆失败");
////        return null;
    }

    @Override
    public ResponseResult<Map<String, String>> systemLogin() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", "system");
        User user = userMapper.selectOne(wrapper);
        if (user == null)
            if (0 == userMapper.insert(new User("system", "example.png", "system")))
                return ResponseResult.fail("系统登录失败");
        return ResponseResult.data(Map.of("token", tokenUtil.getTokenByUserId("system")));
    }

    @Override
    public ResponseResult<NullData> deleteUser(String userId) {
        return ResponseResult.fail("已使用微信登录，不支持删除用户");
    }

    @Override
    public ResponseResult<User> selectUser(String userId) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userId);
        User user = userMapper.selectOne(wrapper);
        if (user == null)
            return ResponseResult.fail("用户不存在");
        return ResponseResult.data(user);
    }

    @Override
    public ResponseResult<NullData> updateUserName(String userId, String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userId);
        User user = userMapper.selectOne(wrapper);
        if (user == null)
            return ResponseResult.fail("用户不存在");
        user.setUsername(username);
        if (userMapper.updateById(user) == 1)
            return ResponseResult.success("修改成功");
        return ResponseResult.fail("修改失败");
    }

    @Override
    public ResponseResult<NullData> updateUserPassword(String userId, String oldPassword, String newPassword) {
        return ResponseResult.fail("已使用微信登录，不支持修改密码");
    }

    @Override
    public ResponseResult<NullData> updateUserPicture(String userId, String pictureUrl) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", userId);
        User user = userMapper.selectOne(wrapper);
        if (user == null)
            return ResponseResult.fail("用户不存在");
        user.setPictureUrl(pictureUrl);
        if (userMapper.updateById(user) == 1)
            return ResponseResult.success("修改成功");
        return ResponseResult.fail("修改失败");
    }
}
