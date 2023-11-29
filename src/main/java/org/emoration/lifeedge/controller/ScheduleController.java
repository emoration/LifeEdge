package org.emoration.lifeedge.controller;

import lombok.extern.slf4j.Slf4j;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.CourseDTO;
import org.emoration.lifeedge.controller.DTO.QueryDateRangeDTO;
import org.emoration.lifeedge.controller.DTO.ScheduleDTO;
import org.emoration.lifeedge.pojo.Event;
import org.emoration.lifeedge.service.CourseServer;
import org.emoration.lifeedge.service.ScheduleServer;
import org.emoration.lifeedge.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author czh
 * @Description 日程
 * @Date 2023/11/16
 */
@Slf4j
@RestController
@RequestMapping("/user/schedule") // @RequestMapping("/user/schedule/course")
public class ScheduleController {
    @Autowired
    ScheduleServer scheduleServer;
    @Autowired
    CourseServer courseServer;
    @Autowired
    TokenUtil tokenUtil;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PostMapping("/create")
    public ResponseResult<NullData> insertSchedule(@RequestHeader("Authorization") String token, @RequestBody ScheduleDTO scheduleDTO) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (scheduleDTO == null)
            return ResponseResult.fail("日程信息不能为空");
        if (scheduleDTO.getBeginAt() > scheduleDTO.getEndAt())
            return ResponseResult.fail("开始时间不能大于结束时间");

        return scheduleServer.insertSchedule(userId, scheduleDTO);
    }

    @DeleteMapping("/delete/{scheduleId}")
    public ResponseResult<NullData> deleteSchedule(@RequestHeader("Authorization") String token, @PathVariable Long scheduleId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (scheduleId == null)
            return ResponseResult.fail("日程id不能为空");

        return scheduleServer.deleteSchedule(userId, scheduleId);
    }

    @PutMapping("/change/{scheduleId}")
    public ResponseResult<NullData> updateSchedule(@RequestHeader("Authorization") String token, @RequestBody ScheduleDTO scheduleDTO, @PathVariable Long scheduleId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (scheduleId == null)
            return ResponseResult.fail("日程id不能为空");
        if (scheduleDTO == null)
            return ResponseResult.fail("日程信息不能为空");
        if (scheduleDTO.getBeginAt() > scheduleDTO.getEndAt())
            return ResponseResult.fail("开始时间不能大于结束时间");

        return scheduleServer.updateSchedule(userId, scheduleId, scheduleDTO);
    }

    @GetMapping("/selectOne/{scheduleId}")
    public ResponseResult<Event> selectOneSchedule(@RequestHeader("Authorization") String token, @PathVariable Long scheduleId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (scheduleId == null)
            return ResponseResult.fail("日程id不能为空");

        return scheduleServer.selectOneSchedule(userId, scheduleId);
    }

    @RequestMapping("/selectRange")
    public ResponseResult<Map<String, Object>> selectRangeSchedule(
            @RequestHeader("Authorization") String token,
            @RequestBody QueryDateRangeDTO queryDateRangeDTO) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (queryDateRangeDTO == null)
            return ResponseResult.fail("查询范围不能为空");

        return scheduleServer.selectRangeSchedule(userId, queryDateRangeDTO);
    }

    @RequestMapping("/selectTerm")
    public ResponseResult<Map<String, Object>> selectTermSchedule(
            @RequestHeader("Authorization") String token) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        QueryDateRangeDTO queryDateRangeDTO = new QueryDateRangeDTO();
        queryDateRangeDTO.setEarliestOn(1693152000);
        queryDateRangeDTO.setLatestOn(1706457600);
        return scheduleServer.selectRangeSchedule(userId, queryDateRangeDTO);
    }

    @GetMapping("/selectAll")
    public ResponseResult<Map<String, Object>> selectAllSchedule(@RequestHeader("Authorization") String token) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }

        return scheduleServer.selectAllSchedule(userId);
    }

    @PostMapping("/course/importSome")
    public ResponseResult<NullData> importCourse(@RequestHeader("Authorization") String token, @RequestBody Map<String, Object> map) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        String courseInfo;
        try {
            courseInfo = (String) map.get("courseInfo");
            if (courseInfo == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("课程信息错误");
        }

        return courseServer.importCourse(userId, courseInfo);
    }

    @PostMapping("/course/importOne")
    public ResponseResult<NullData> inputCourse(@RequestHeader("Authorization") String token, @RequestBody CourseDTO courseDTO) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (courseDTO == null)
            return ResponseResult.error("课程信息错误");

        return courseServer.inputCourse(userId, courseDTO);
    }

}
