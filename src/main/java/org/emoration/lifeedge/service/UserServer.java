package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;

/**
 * @Author czh
 * @Description 用户操作：注册、登录、注销、改名、改密码
 * @Date 2023/11/16
 */
public interface UserServer {
    ResponseResult<NullData> register(String username, String password);

    ResponseResult<NullData> login(String username, String password);

    ResponseResult<NullData> deleteUser(String userId);

    ResponseResult<NullData> updateUserName(String userId, String username);

    ResponseResult<NullData> updateUserPassword(String userId, String password);

    ResponseResult<NullData> updateUserPicture(String userId, String pictureUrl);


}
