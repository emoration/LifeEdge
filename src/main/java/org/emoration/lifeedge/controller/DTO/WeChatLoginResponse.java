package org.emoration.lifeedge.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeChatLoginResponse {
    private String session_key;
    private String unionid;
    private Integer errcode;
    private String openid;
    private String errmsg;
}
