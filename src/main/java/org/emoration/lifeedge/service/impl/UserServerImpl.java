package org.emoration.lifeedge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.pojo.User;
import org.emoration.lifeedge.pojo.mapper.UserMapper;
import org.emoration.lifeedge.service.UserServer;
import org.emoration.lifeedge.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/16
 */
@Service
public class UserServerImpl implements UserServer {

    @Autowired
    UserMapper userMapper;
//    @Autowired
//    AuthenticationManager authenticationManager;

    @Override
    public ResponseResult<NullData> register(String username, String password) {
        if (!PasswordUtils.valid(password)) {
            return ResponseResult.fail("密码不符合规范");
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        if (userMapper.selectOne(userQueryWrapper) != null) {
            return ResponseResult.fail("用户名被占用");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(PasswordUtils.encode(password));
        if (userMapper.insert(user) == 1) {
            return ResponseResult.success("成功注册");
        } else {
            return ResponseResult.fail("服务器未知错误");
        }
    }

    @Override
    public ResponseResult<NullData> login(String username, String password) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//        try {
//            QueryWrapper<User> wrapper = new QueryWrapper<>();
//            wrapper.eq("username", username);
//            User user = userMapper.selectOne(wrapper);
////            if(redisUtil.hasKey("freeze:" + username))
////                return new ResponseResult<>(CodeConstants.CODE_USER_EXCEPTION, "该用户已经被冻结");
//            authenticationManager.authenticate(authenticationToken);
////            if(redisUtil.hasKey("fail_login : " + username))
////                redisUtil.delete("fail_login : " + username);
//            String jwt = tokenUtil.getTokenByUserId(user.getUserId());
//            HashMap<String, String> map = new HashMap<>();
//            map.put("token", jwt);
////            if (Objects.equals(userMapper.selectRoleByUserId(user.getUserId()), "ROLE_MANAGER"))
////                map.put("role", "2");
////            else
////                map.put("role", "1");
//            return new ResponseResult<>(CodeConstants.CODE_SUCCESS, "登陆成功", map);
//        } catch (AuthenticationException e) {
////            if (redisUtil.hasKey("fail_login : " + username)) {
////                redisUtil.set("fail_login : " + username, (Integer) redisUtil.get("fail_login : " + username) + 1);
////                if ((Integer) redisUtil.get("fail_login : " + username) > ParameterConstants.MAX_NUMBER_OF_FAIL) {
////                    redisUtil.set("freeze:" + username, "true", 3600);
////                    return new ResponseResult<>(CodeConstants.CODE_USER_EXCEPTION, "该用户已达最大密码错误次数,已经被暂时冻结账户");
////                }
////            } else {
////                redisUtil.set("fail_login : " + username, 1);
////            }
//        } finally {
//
//        }
//
//        return new ResponseResult<>(CodeConstants.CODE_UNAUTHORIZED, "登陆失败");
        return null;
    }

    @Override
    public ResponseResult<NullData> deleteUser(String userId) {
        return null;
    }

    @Override
    public ResponseResult<NullData> updateUserName(String userId, String username) {
        return null;
    }

    @Override
    public ResponseResult<NullData> updateUserPassword(String userId, String password) {
        return null;
    }

    @Override
    public ResponseResult<NullData> updateUserPicture(String userId, String pictureUrl) {
        return null;
    }
}
