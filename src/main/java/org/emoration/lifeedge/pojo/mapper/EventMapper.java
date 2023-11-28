package org.emoration.lifeedge.pojo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.pojo.Event;

import java.util.List;

/**
 * @Author czh
 * @Description 事件Mapper
 * @Date 2023/11/16
 */
@Mapper
public interface EventMapper extends BaseMapper<Event> {
    List<Event> selectRangeSchedule(String userId, Integer earliestOn, Integer latestOn);
}