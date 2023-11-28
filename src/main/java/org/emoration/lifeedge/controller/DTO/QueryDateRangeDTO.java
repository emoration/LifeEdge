package org.emoration.lifeedge.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author czh
 * @Description 查询日期范围DTO
 * @Date 2023/11/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryDateRangeDTO {
    /**
     * 最早日期
     */
    private Integer earliestOn;
    /**
     * 最晚日期
     */
    private Integer latestOn;
}
