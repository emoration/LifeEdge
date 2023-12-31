package org.emoration.lifeedge.controller.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author czh
 * @Description 作息建议DTO
 * @Date 2023/11/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorialDTO {
    /**
     * 该作息建议的标题
     */
    private String name;
    /**
     * 该作息建议的详情
     */
    private String description;
}
