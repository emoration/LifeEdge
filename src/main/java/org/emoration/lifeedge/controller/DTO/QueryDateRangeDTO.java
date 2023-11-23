package org.emoration.lifeedge.controller.DTO;

import lombok.Data;

/**
 * @Author czh
 * @Description 查询日期范围DTO
 * @Date 2023/11/16
 */
@Data
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
