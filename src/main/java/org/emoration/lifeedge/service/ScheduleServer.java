package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ScheduleDTO;
import org.emoration.lifeedge.pojo.Event;

import java.util.List;

/**
 * @Author czh
 * @Description 日程操作：增、删、改、单个查、范围查、全部查
 * @Date 2023/11/16
 */
public interface ScheduleServer {
    ResponseResult<NullData> insertSchedule(Integer userId, ScheduleDTO scheduleDTO);

    ResponseResult<NullData> deleteSchedule(Integer userId, Integer scheduleId);

    ResponseResult<NullData> updateSchedule(Integer userId, ScheduleDTO scheduleDTO);

    ResponseResult<Event> selectOneSchedule(Integer userId, Integer scheduleId);

    ResponseResult<List<Event>> selectRangeSchedule(Integer userId, QueryDateRangeDTO queryDateRangeDTO);

    ResponseResult<List<Event>> selectAllSchedule(Integer userId);
}
