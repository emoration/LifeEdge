package org.emoration.lifeedge.controller;

import lombok.extern.slf4j.Slf4j;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ReminderDTO;
import org.emoration.lifeedge.pojo.Reminder;
import org.emoration.lifeedge.service.ReminderServer;
import org.emoration.lifeedge.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    @Autowired
    TokenUtil tokenUtil;

    @PostMapping("/create/{scheduleId}")
    public ResponseResult<NullData> insertReminder(@RequestHeader("Authorization") String token, @RequestBody ReminderDTO reminderDTO, @PathVariable Long scheduleId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (reminderDTO == null)
            return ResponseResult.fail("提醒信息不能为空");

        return reminderServer.insertReminder(userId, reminderDTO, scheduleId);
    }

    @DeleteMapping("/delete/{reminderId}")
    public ResponseResult<NullData> deleteReminder(@RequestHeader("Authorization") String token, @PathVariable Long reminderId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }

        return reminderServer.deleteReminder(userId, reminderId);
    }

    @PutMapping("/change/{reminderId}")
    public ResponseResult<NullData> updateReminder(@RequestHeader("Authorization") String token, @RequestBody ReminderDTO reminderDTO, @PathVariable Long reminderId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (reminderDTO == null)
            return ResponseResult.fail("提醒信息不能为空");

        return reminderServer.updateReminder(userId, reminderId, reminderDTO);
    }

    @GetMapping("/selectOne/{reminderId}")
    public ResponseResult<Map<String, Object>> selectOneReminder(@RequestHeader("Authorization") String token, @PathVariable Long reminderId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }

        return reminderServer.selectOneReminder(userId, reminderId);
    }

    @RequestMapping("/selectRange")
    public ResponseResult<Map<String, Object>> selectRangeReminder(@RequestHeader("Authorization") String token, @RequestBody QueryDateRangeDTO queryDateRangeDTO) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }

        return reminderServer.selectRangeReminder(userId, queryDateRangeDTO);
    }

    @GetMapping("/selectAll")
    public ResponseResult<Map<String, Object>> selectAllReminder(@RequestHeader("Authorization") String token) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }

        return reminderServer.selectAllReminder(userId);
    }
}
