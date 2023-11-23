package org.emoration.lifeedge.pojo;

import lombok.Data;

/**
 * @Author czh
 * @Description 提醒实体类
 * @Date 2023/11/16
 */
@Data
public class Reminder {
    private Integer id;
    private Integer type;
    private Long remindAt;
    private Integer repeatTimes;

}