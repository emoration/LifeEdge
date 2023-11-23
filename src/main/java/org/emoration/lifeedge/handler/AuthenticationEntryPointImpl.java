package org.emoration.lifeedge.handler;

import com.alibaba.fastjson.JSON;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.utils.RedisUtil;
import org.emoration.lifeedge.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author czh
 * @Description 自定义异常处理器
 * @Date 2023/11/23
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Autowired
    WebUtil webUtil;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        ResponseResult<NullData> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), authException.getMessage());
        String json = JSON.toJSONString(result);
        webUtil.renderString(response, json);

    }
}
