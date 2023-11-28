package org.emoration.lifeedge.service.impl;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ReminderDTO;
import org.emoration.lifeedge.pojo.Reminder;
import org.emoration.lifeedge.pojo.mapper.ReminderMapper;
import org.emoration.lifeedge.service.ReminderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/16
 */
@Service
public class ReminderServerImpl implements ReminderServer {
    @Autowired
    ReminderMapper reminderMapper;

    @Override
    public ResponseResult<NullData> insertReminder(String userId, ReminderDTO reminderDTO) {
        return null;
    }

    @Override
    public ResponseResult<NullData> deleteReminder(String userId, Integer reminderId) {
        return null;
    }

    @Override
    public ResponseResult<NullData> updateReminder(String userId, ReminderDTO reminderDTO) {
        return null;
    }

    @Override
    public ResponseResult<Reminder> selectOneReminder(String userId, Integer reminderId) {
        return null;
    }

    @Override
    public ResponseResult<List<Reminder>> selectRangeReminder(String userId, QueryDateRangeDTO queryDateRangeDTO) {
        return null;
    }

    @Override
    public ResponseResult<List<Reminder>> selectAllReminder(String userId) {
        return null;
    }
}
