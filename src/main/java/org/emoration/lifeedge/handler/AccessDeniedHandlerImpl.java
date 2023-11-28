//package org.emoration.lifeedge.handler;
//
//import com.alibaba.fastjson.JSON;
//import org.emoration.lifeedge.common.NullData;
//import org.emoration.lifeedge.common.ResponseResult;
//import org.emoration.lifeedge.utils.WebUtil;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.web.access.AccessDeniedHandler;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * @Author czh
// * @Description 自定义权限异常处理器
// * @Date 2023/11/23
// */
//@Component
//public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
//
//    @Autowired
//    WebUtil webUtil;
//
//    @Override
//    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//
//        ResponseResult<NullData> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "用户权限不足");
//        String json = JSON.toJSONString(result);
//        webUtil.renderString(response, json);
//
//    }
//}
