package org.emoration.lifeedge.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author czh
 * @Description 通用响应实体
 * @Date 2023/11/16
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    //状态码
    private Integer code;

    //返回信息
    private String msg;

    //返回数据
    private T data;

    public ResponseResult(Integer code) {
        this.code = code;
    }

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功响应
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return 响应实体
     */
    static public <T> ResponseResult<T> data(T data) {
        return new ResponseResult<>(CodeConstants.CODE_SUCCESS, data);
    }

    /**
     * 成功响应
     *
     * @param msg 返回信息
     * @param <T> 数据类型
     * @return 响应实体
     */
    static public <T> ResponseResult<T> success(String msg) {
        return new ResponseResult<>(CodeConstants.CODE_SUCCESS, msg);
    }

    /**
     * 失败响应
     *
     * @param msg 返回信息
     * @param <T> 数据类型
     * @return 响应实体
     */
    static public <T> ResponseResult<T> fail(String msg) {
        return new ResponseResult<>(CodeConstants.CODE_NOT_FOUND, msg);
    }

    /**
     * 无权限响应
     *
     * @param msg 返回信息
     * @param <T> 数据类型
     * @return 响应实体
     */
    static public <T> ResponseResult<T> unauthorized(String msg) {
        return new ResponseResult<>(CodeConstants.CODE_ACCESS_LIMIT, msg);
    }

    /**
     * 服务器错误
     *
     * @param msg 返回信息
     * @param <T> 数据类型
     * @return 响应实体
     */
    static public <T> ResponseResult<T> error(String msg) {
        return new ResponseResult<>(CodeConstants.CODE_SERVER_ERROR, msg);
    }
}

