package org.emoration.lifeedge.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author czh
 * @Description 课程DTO
 * @Date 2023/11/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
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
    private Integer beginAtWeek;
    /**
     * 时间戳(实际最小单位到分钟)
     */
    private Integer endAtWeek;
    /**
     * RGB压缩表示(实际最多占用int32位，如：0xFFFFFF)
     */
    private Integer color;
    /**
     * 枚举:1单周2双周3每周
     */
    private Integer oddEven;
}