package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;

import java.util.Map;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/27
 */
public interface WeChatLoginService {
    ResponseResult<Map<String, String>> doSpringbootLoginWithWeChat(String code);
}
