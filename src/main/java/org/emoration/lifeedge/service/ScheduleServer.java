package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ScheduleDTO;
import org.emoration.lifeedge.pojo.Event;

import java.util.List;
import java.util.Map;

/**
 * @Author czh
 * @Description 日程操作：增、删、改、单个查、范围查、全部查
 * @Date 2023/11/16
 */
public interface ScheduleServer {
    ResponseResult<NullData> insertSchedule(String userId, ScheduleDTO scheduleDTO);

    ResponseResult<NullData> deleteSchedule(String userId, Long scheduleId);

    ResponseResult<NullData> updateSchedule(String userId, Long scheduleId, ScheduleDTO scheduleDTO);

    ResponseResult<Event> selectOneSchedule(String userId, Long scheduleId);

    ResponseResult<Map<String, Object>> selectRangeSchedule(String userId, QueryDateRangeDTO queryDateRangeDTO);

    ResponseResult<Map<String, Object>> selectAllSchedule(String userId);
}
