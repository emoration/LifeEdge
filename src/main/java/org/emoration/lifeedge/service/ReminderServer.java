package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ReminderDTO;
import org.emoration.lifeedge.pojo.Reminder;

import java.util.List;
import java.util.Map;

/**
 * @Author czh
 * @Description 提醒操作：增、删、改、单个查、范围查、全部查
 * @Date 2023/11/16
 */
public interface ReminderServer {

    ResponseResult<NullData> insertReminder(String userId, ReminderDTO reminderDTO, Long scheduleId);

    ResponseResult<NullData> deleteReminder(String userId, Long reminderId);

    ResponseResult<NullData> updateReminder(String userId, Long reminderId, ReminderDTO reminderDTO);

    ResponseResult<Map<String, Object>> selectOneReminder(String userId, Long reminderId);

    ResponseResult<Map<String, Object>> selectRangeReminder(String userId, QueryDateRangeDTO queryDateRangeDTO);

    ResponseResult<Map<String, Object>>  selectAllReminder(String userId);
}
