package org.emoration.lifeedge.controller.DTO;

import lombok.Data;

/**
 * @Author czh
 * @Description 提醒DTO
 * @Date 2023/11/16
 */
@Data
public class ReminderDTO {
    /**
     * 提醒类型,枚举:普通/计算题/摇一摇/小游戏
     */
    private Integer type;
    /**
     * 时间戳(实际最小单位到分钟)
     */
    private Integer remindAt;
    /**
     * 提醒失败后 重复提醒的次数
     */
    private Integer repeatTimes;
}