package org.emoration.lifeedge.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author czh
 * @Description 事件实体类
 * @Date 2023/11/16
 */
@Data
public class Event {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Long beginAt;
    private Long endAt;
    private Integer color;
    private Long reminderId;
    private String ownerId;

}
