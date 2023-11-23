package org.emoration.lifeedge.service.impl;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ScheduleDTO;
import org.emoration.lifeedge.pojo.Event;
import org.emoration.lifeedge.pojo.mapper.EventMapper;
import org.emoration.lifeedge.service.ScheduleServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseResult<NullData> insertSchedule(Integer userId, ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public ResponseResult<NullData> deleteSchedule(Integer userId, Integer scheduleId) {
        return null;
    }

    @Override
    public ResponseResult<NullData> updateSchedule(Integer userId, ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public ResponseResult<Event> selectOneSchedule(Integer userId, Integer scheduleId) {
        return null;
    }

    @Override
    public ResponseResult<List<Event>> selectRangeSchedule(Integer userId, QueryDateRangeDTO queryDateRangeDTO) {
        return null;
    }

    @Override
    public ResponseResult<List<Event>> selectAllSchedule(Integer userId) {
        return null;
    }
}
