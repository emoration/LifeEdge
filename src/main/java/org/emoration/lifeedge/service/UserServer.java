package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.pojo.User;

import java.util.Map;

/**
 * @Author czh
 * @Description 用户操作：注册、登录、查询、注销、改名、改密码
 * @Date 2023/11/16
 */
public interface UserServer {
    ResponseResult<NullData> register(String username, String password);

    ResponseResult<Map<String, String>> login(String username, String password);

    ResponseResult<Map<String, String>> systemLogin();


    ResponseResult<NullData> deleteUser(String userId);

    ResponseResult<User> selectUser(String userId);

    ResponseResult<NullData> updateUserName(String userId, String username);

    public ResponseResult<NullData> updateUserPassword(String userId, String oldPassword, String newPassword);

    ResponseResult<NullData> updateUserPicture(String userId, String pictureUrl);


}
