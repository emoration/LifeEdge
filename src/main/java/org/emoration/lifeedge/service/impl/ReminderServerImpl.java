package org.emoration.lifeedge.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ReminderDTO;
import org.emoration.lifeedge.pojo.Event;
import org.emoration.lifeedge.pojo.Reminder;
import org.emoration.lifeedge.pojo.mapper.EventMapper;
import org.emoration.lifeedge.pojo.mapper.ReminderMapper;
import org.emoration.lifeedge.service.ReminderServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author czh
 * @Description 提醒
 * @Date 2023/11/16
 */
@Service
public class ReminderServerImpl implements ReminderServer {
    @Autowired
    ReminderMapper reminderMapper;
    @Autowired
    EventMapper eventMapper;


    @Override
    public ResponseResult<NullData> insertReminder(String userId, ReminderDTO reminderDTO, Long scheduleId) {
        Reminder reminder = new Reminder();
        reminder.setRepeatTimes(reminderDTO.getRepeatTimes());
        reminder.setRemindAt(reminderDTO.getRemindAt());
        reminder.setType(reminderDTO.getType());
        if (reminderMapper.insert(reminder) == 1) {
            QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", scheduleId);
            Event event = eventMapper.selectOne(queryWrapper);
            if (event.getReminderId() != null) {
                reminderMapper.deleteById(reminder.getId());
                return ResponseResult.fail("该日程已有提醒");
            }
            event.setReminderId(reminder.getId());
            if (eventMapper.updateById(event) == 1) {
                return ResponseResult.success("提醒创建成功");
            } else {
                reminderMapper.deleteById(reminder.getId());
                return ResponseResult.fail("提醒创建失败");
            }
        } else {
            return ResponseResult.fail("提醒创建失败");
        }
    }

    @Override
    public ResponseResult<NullData> deleteReminder(String userId, Long reminderId) {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("reminder_id", reminderId);
        Event event = eventMapper.selectOne(queryWrapper);
        if (event == null) {
            return ResponseResult.fail("提醒不存在");
        } else {
            UpdateWrapper<Event> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("reminder_id", reminderId)
                    .set("reminder_id", null); // 设置需要更新的字段和值
            if (eventMapper.update(null, updateWrapper) == 1) {
                reminderMapper.deleteById(reminderId);
                return ResponseResult.success("提醒删除成功");
            } else {
                return ResponseResult.fail("提醒删除失败");
            }
        }
    }

    @Override
    public ResponseResult<NullData> updateReminder(String userId, Long reminderId, ReminderDTO reminderDTO) {
        Reminder reminder = reminderMapper.selectById(reminderId);
        if (reminder == null) {
            return ResponseResult.fail("提醒不存在");
        } else {
            reminder.setRepeatTimes(reminderDTO.getRepeatTimes());
            reminder.setRemindAt(reminderDTO.getRemindAt());
            reminder.setType(reminderDTO.getType());
            if (reminderMapper.updateById(reminder) == 1) {
                return ResponseResult.success("提醒修改成功");
            } else {
                return ResponseResult.fail("提醒修改失败");
            }
        }
    }

    @Override
    public ResponseResult<Map<String, Object>> selectOneReminder(String userId, Long reminderId) {
        Reminder reminder = reminderMapper.selectById(reminderId);
        if (reminder == null) {
            return ResponseResult.fail("提醒不存在");
        } else {
            return ResponseResult.data(Map.of("reminder", reminder));
        }
    }

    @Override
    public ResponseResult<Map<String, Object>> selectRangeReminder(String userId, QueryDateRangeDTO queryDateRangeDTO) {

        List<Reminder> reminders = reminderMapper.selectRangeReminderByUserId(userId, queryDateRangeDTO.getEarliestOn(), queryDateRangeDTO.getLatestOn());
        if (reminders == null) {
            return ResponseResult.fail("提醒不存在");
        } else {
            return ResponseResult.data(Map.of("reminderList", reminders, "dateRangeFrom", queryDateRangeDTO.getEarliestOn(), "dateRangeTo", queryDateRangeDTO.getLatestOn()));
        }
    }

    @Override
    public ResponseResult<Map<String, Object>> selectAllReminder(String userId) {
        List<Reminder> reminders = reminderMapper.selectAllReminderByUserId(userId);
        if (reminders == null) {
            return ResponseResult.fail("提醒不存在");
        } else {
            return ResponseResult.data(Map.of("reminderList", reminders));
        }
    }
}
