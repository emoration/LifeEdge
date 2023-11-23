package org.emoration.lifeedge.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/20
 */
public class PasswordUtils {
    // 密码匹配的正则表达式模式
    private static final Pattern passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?])[A-Za-z\\d`~!@#$%^&*()\\-_=+\\[{\\]}\\\\|;:'\",<.>/?]{8,16}$"
);

    static public boolean valid(String password) {

        // 创建 Matcher 对象
        Matcher matcher = passwordPattern.matcher(password);

        // 判断是否匹配
        return matcher.matches();
    }

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    public static String encode(String password) {

        return bCryptPasswordEncoder.encode(password);

    }
}

