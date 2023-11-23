package org.emoration.lifeedge.common;

/**
 * @Author czh
 * @Description status code
 * @Date 2023/11/16
 */
public interface CodeConstants {

    int CODE_SUCCESS = 200;                 // 成功
    int CODE_UNAUTHORIZED = 401;            // 授权错误，token错误
    int CODE_ACCESS_LIMIT = 403;            // 访问限制
    int CODE_NOT_FOUND = 404;               // 查询未找到/其他用户错误
    int CODE_SERVER_ERROR = 500;            // 服务器错误

}
