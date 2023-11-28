package org.emoration.lifeedge.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.emoration.lifeedge.common.RedisKeyConstants;
import org.emoration.lifeedge.pojo.mapper.UserMapper;
import org.emoration.lifeedge.pojo.User;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author czh
 * @Description token工具类
 * @Date 2023/11/23
 */
@Component
public class TokenUtil {

    @Value("${token.signature}")
    private String signature = "czh123";

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisUtil redisUtil;

    /**
     * @Description 通过UserId获得token，自动设置过期时间
     * @Date 2023/11/23
     */
    public String getTokenByUserId(String userId) {
        JwtBuilder jwtBuilder = Jwts.builder();
        String token = jwtBuilder.setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .claim("userId", userId)
                .claim("createAt", DateUtil.getDateTime())
                .signWith(SignatureAlgorithm.HS256, signature)
                .compact();
        redisUtil.set(RedisKeyConstants.TOKEN_PREFIX + token, DateUtil.getDateTime(), RedisKeyConstants.TOKEN_MAX_UNREFRESHED_SECONDS);
        return token;
    }

    /**
     * @Description 刷新token过期时间
     * @Date 2023/11/23
     */
    public void refreshToken(String token) {
        redisUtil.set(RedisKeyConstants.TOKEN_PREFIX + token, DateUtil.getDateTime(), RedisKeyConstants.TOKEN_MAX_UNREFRESHED_SECONDS);
    }

    /**
     * @Description 删除token
     * @Date 2023/11/23
     */
    public void deferredDisableToken(String token) {
        redisUtil.set(RedisKeyConstants.TOKEN_PREFIX + token, DateUtil.getDateTime(), RedisKeyConstants.TOKEN_DEFERRED_DELETE_SECONDS);
    }

    /**
     * @Description 通过token获得createAt
     * @Date 2023/11/23
     */
    public String getRefreshAtByToken(String token) {
        return (String) redisUtil.get(RedisKeyConstants.TOKEN_PREFIX + token);
    }

    /**
     * @Description 解析token获得Claims对象
     * @Date 2023/11/23
     */
    private Claims parseTokenToClaims(String token) {
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    /**
     * @Description 解析token获取createAt
     * @Date 2023/11/23
     */
    public String parseTokenToCreateAt(String token) {
        Claims claims = parseTokenToClaims(token);
        return claims.get("createAt", String.class);
    }

    /**
     * @Description 解析token获取userId
     * @Date 2023/11/23
     */
    public String parseTokenToUserId(String token) {
        Claims claims = parseTokenToClaims(token);
        return claims.get("userId", String.class);
    }

    /**
     * @Description 解析token获取user对象
     * @Date 2023/11/23
     */
    public User parseTokenToUser(String token) {
        String userId = parseTokenToUserId(token);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        return userMapper.selectOne(wrapper);
    }

}
