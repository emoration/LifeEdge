package org.emoration.lifeedge.pojo;

import lombok.Data;

/**
 * @Author czh
 * @Description 教程实体类
 * @Date 2023/11/16
 */
@Data
public class User {
    private Integer id;
    private String pictureUrl;
    private String username;
    private String password;

}