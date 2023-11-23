package org.emoration.lifeedge.controller.DTO;

import lombok.Data;

/**
 * @Author czh
 * @Description 日程DTO
 * @Date 2023/11/16
 */
@Data
public class ScheduleDTO {
    /**
     * 事件的名称
     */
    private String name;
    /**
     * 事件的描述(详情)
     */
    private String description;
    /**
     * 时间戳(实际最小单位到分钟)
     */
    private Integer beginAt;
    /**
     * 时间戳(实际最小单位到分钟)
     */
    private Integer endAt;
    /**
     * RGB压缩表示(实际最多占用int32位，如：0xFFFFFF)
     */
    private Integer color;
    /**
     * 枚举:1日/2周/3月/4年/5工作日
     */
    private Integer repeat;
}
