package org.emoration.lifeedge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ScheduleDTO;
import org.emoration.lifeedge.pojo.Event;
import org.emoration.lifeedge.pojo.mapper.EventMapper;
import org.emoration.lifeedge.service.ScheduleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/16
 */
@Service
public class ScheduleServerImpl implements ScheduleServer {
    @Autowired
    EventMapper eventMapper;

    @Override
    public ResponseResult<NullData> insertSchedule(String userId, ScheduleDTO scheduleDTO) {

        Event event = new Event();
        event.setId(null);
        event.setName(scheduleDTO.getName());
        event.setDescription(scheduleDTO.getDescription());
        event.setBeginAt(scheduleDTO.getBeginAt().longValue());
        event.setEndAt(scheduleDTO.getEndAt().longValue());
        event.setColor(scheduleDTO.getColor());
        event.setOwnerId(userId);
        event.setReminderId(null);
        if (eventMapper.insert(event) == 1) {
            return ResponseResult.success("新建日程成功");
        } else {
            return ResponseResult.fail("新建日程失败");
        }
    }

    @Override
    public ResponseResult<NullData> deleteSchedule(String userId, Integer scheduleId) {

        QueryWrapper<Event> eventQueryWrapper = new QueryWrapper<>();
        eventQueryWrapper.eq("id", scheduleId);
        eventQueryWrapper.eq("owner_id", userId);
        if (eventMapper.delete(eventQueryWrapper) == 1) {
            return ResponseResult.success("删除日程成功");
        } else {
            return ResponseResult.fail("删除日程失败");
        }
    }

    @Override
    public ResponseResult<NullData> updateSchedule(String userId, Integer scheduleId, ScheduleDTO scheduleDTO) {
        QueryWrapper<Event> eventQueryWrapper = new QueryWrapper<>();
        eventQueryWrapper.eq("id", scheduleId);
        eventQueryWrapper.eq("owner_id", userId);
        Event event = eventMapper.selectOne(eventQueryWrapper);
        if (event == null) {
            return ResponseResult.fail("日程不存在");
        }
        if (scheduleDTO.getName() != null)
            event.setName(scheduleDTO.getName());
        if (scheduleDTO.getDescription() != null)
            event.setDescription(scheduleDTO.getDescription());
        if (scheduleDTO.getBeginAt() != null)
            event.setBeginAt(scheduleDTO.getBeginAt().longValue());
        if (scheduleDTO.getEndAt() != null)
            event.setEndAt(scheduleDTO.getEndAt().longValue());
        if (scheduleDTO.getColor() != null)
            event.setColor(scheduleDTO.getColor());
        event.setOwnerId(userId);
        if (eventMapper.updateById(event) == 1) {
            return ResponseResult.success("更新日程成功");
        } else {
            return ResponseResult.fail("更新日程失败");
        }
    }

    @Override
    public ResponseResult<Event> selectOneSchedule(String userId, Integer scheduleId) {
        QueryWrapper<Event> eventQueryWrapper = new QueryWrapper<>();
        eventQueryWrapper.eq("id", scheduleId);
        eventQueryWrapper.eq("owner_id", userId);
        Event event = eventMapper.selectOne(eventQueryWrapper);
        if (event == null) {
            return ResponseResult.fail("日程不存在");
        }
        return ResponseResult.data(event);
    }

    @Override
    public ResponseResult<Map<String, Object>> selectRangeSchedule(String userId, QueryDateRangeDTO queryDateRangeDTO) {
        if (userId == null)
            return ResponseResult.fail("用户id不能为空");
        if (queryDateRangeDTO.getEarliestOn() == null || queryDateRangeDTO.getLatestOn() == null
                || queryDateRangeDTO.getEarliestOn() > queryDateRangeDTO.getLatestOn())
            return ResponseResult.fail("查询时间范围错误");

        List<Event> eventList = eventMapper.selectRangeSchedule(userId, queryDateRangeDTO.getEarliestOn(), queryDateRangeDTO.getLatestOn());
//        if(eventList==null){
//            eventList = new ArrayList<>();
//        }
        Map<String, Object> data = Map.of("eventList", eventList, "dateRangeFrom", queryDateRangeDTO.getEarliestOn(), "dateRangeTo", queryDateRangeDTO.getLatestOn());
        return ResponseResult.data(data);
    }

    @Override
    public ResponseResult<Map<String, Object>> selectAllSchedule(String userId) {
        if (userId == null)
            return ResponseResult.fail("用户id不能为空");
        QueryWrapper<Event> eventQueryWrapper = new QueryWrapper<>();
        eventQueryWrapper.eq("owner_id", userId);
        List<Event> eventList = eventMapper.selectList(eventQueryWrapper);
        if (eventList == null) {
            eventList = new ArrayList<>();
        }
        Map<String, Object> data = Map.of("eventList", eventList);
        return ResponseResult.data(data);
    }
}
