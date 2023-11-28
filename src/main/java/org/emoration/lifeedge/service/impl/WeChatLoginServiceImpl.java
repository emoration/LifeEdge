package org.emoration.lifeedge.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.WeChatLoginResponse;
import org.emoration.lifeedge.pojo.User;
import org.emoration.lifeedge.pojo.mapper.UserMapper;
import org.emoration.lifeedge.service.WeChatLoginService;
import org.emoration.lifeedge.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/27
 */

@Service
public class WeChatLoginServiceImpl implements WeChatLoginService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper; // Jackson 的 ObjectMapper
    @Value("wx.appId")
    private String appId;
    @Value("wx.appSecret")
    private String appSecret;

    private WeChatLoginResponse doWeChatLogin(String code, String appId, String appSecret) {
//        String appId = "your_app_id";
//        String appSecret = "your_app_secret";
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId +
                "&secret=" + appSecret +
                "&js_code=" + code +
                "&grant_type=authorization_code";
//        String response = restTemplate.getForObject(url, String.class);
//        try {
//            // 手动解析 JSON 字符串
//            WeChatLoginResponse loginResponse = objectMapper.readValue(response, WeChatLoginResponse.class);
//            return loginResponse;
//        } catch (Exception e) {
//            // 处理异常
//            e.printStackTrace();
//            return null;
//        }
        ResponseEntity<WeChatLoginResponse> responseEntity = restTemplate.getForEntity(url, WeChatLoginResponse.class);
        return responseEntity.getBody();
    }

    @Override
    public ResponseResult<Map<String, String>> doSpringbootLoginWithWeChat(String code) {
        WeChatLoginResponse weChatLoginResponse = doWeChatLogin(code, appId, appSecret);
        /*
         * 错误码
         * 40029	code 无效	js_code无效
         * 45011	api minute-quota reach limit  mustslower  retry next minute	API 调用太频繁，请稍候再试
         * 40226	code blocked	高风险等级用户，小程序登录拦截 。风险等级详见用户安全解方案
         * -1	system error	系统繁忙，此时请开发者稍候再试
         */
        if (weChatLoginResponse.getErrcode() == null) {
            userMapper.insert(new User(weChatLoginResponse.getOpenid(), null, weChatLoginResponse.getOpenid()));
            return ResponseResult.data(Map.of("token", tokenUtil.getTokenByUserId(weChatLoginResponse.getOpenid())));
        } else {
            return ResponseResult.fail(weChatLoginResponse.getErrmsg());
        }
    }
}