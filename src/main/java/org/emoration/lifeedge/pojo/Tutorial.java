package org.emoration.lifeedge.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author czh
 * @Description 教程实体类
 * @Date 2023/11/16
 */
@Data
public class Tutorial {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;
}
