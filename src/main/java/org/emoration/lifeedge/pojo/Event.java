package org.emoration.lifeedge.pojo;

import lombok.Data;

/**
 * @Author czh
 * @Description 事件实体类
 * @Date 2023/11/16
 */
@Data
public class Event {
    private Integer id;
    private String name;
    private String description;
    private Long beginAt;
    private Long endAt;
    private Integer color;
    private Integer repeatType;
    private Integer reminderId;
    private Integer ownerId;

}
