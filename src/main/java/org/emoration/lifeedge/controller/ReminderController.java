package org.emoration.lifeedge.controller;

import lombok.extern.slf4j.Slf4j;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ReminderDTO;
import org.emoration.lifeedge.pojo.Reminder;
import org.emoration.lifeedge.service.ReminderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/16
 */

@Slf4j
@RestController
@RequestMapping("/user/schedule/reminder")
public class ReminderController {
    @Autowired
    ReminderServer reminderServer;


    public ResponseResult<NullData> insertReminder(String token, ReminderDTO reminderDTO) {
        return null;
    }


    public ResponseResult<NullData> deleteReminder(String token, Integer reminderId) {
        return null;
    }


    public ResponseResult<NullData> updateReminder(String token, ReminderDTO reminderDTO) {
        return null;
    }


    public ResponseResult<Reminder> selectOneReminder(String token, Integer reminderId) {
        return null;
    }


    public ResponseResult<List<Reminder>> selectRangeReminder(String token, QueryDateRangeDTO queryDateRangeDTO) {
        return null;
    }


    public ResponseResult<List<Reminder>> selectAllReminder(Integer userId) {
        return null;
    }
}
