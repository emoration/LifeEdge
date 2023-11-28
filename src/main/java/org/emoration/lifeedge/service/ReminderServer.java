package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ReminderDTO;
import org.emoration.lifeedge.pojo.Reminder;

import java.util.List;

/**
 * @Author czh
 * @Description 提醒操作：增、删、改、单个查、范围查、全部查
 * @Date 2023/11/16
 */
public interface ReminderServer {
    ResponseResult<NullData> insertReminder(String userId, ReminderDTO reminderDTO);

    ResponseResult<NullData> deleteReminder(String userId, Integer reminderId);

    ResponseResult<NullData> updateReminder(String userId, ReminderDTO reminderDTO);

    ResponseResult<Reminder> selectOneReminder(String userId, Integer reminderId);

    ResponseResult<List<Reminder>> selectRangeReminder(String userId, QueryDateRangeDTO queryDateRangeDTO);

    ResponseResult<List<Reminder>> selectAllReminder(String userId);
}
