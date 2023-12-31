package org.emoration.lifeedge.pojo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.emoration.lifeedge.pojo.Reminder;

import java.util.List;

/**
 * @Author czh
 * @Description 提醒Mapper
 * @Date 2023/11/16
 */
@Mapper
public interface ReminderMapper extends BaseMapper<Reminder> {
    List<Reminder> selectRangeReminderByUserId(String userId, Integer earliestOn, Integer latestOn);

    List<Reminder> selectAllReminderByUserId(String userId);
}
