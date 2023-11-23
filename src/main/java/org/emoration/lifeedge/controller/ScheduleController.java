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
@RequestMapping("/user/schedule") // @RequestMapping("/user/schedule/course")
public abstract class ScheduleController{
    @Autowired
    ScheduleServer scheduleServer;
    @Autowired
    CourseServer courseServer;

    
    public ResponseResult<NullData> insertSchedule(String token, ScheduleDTO scheduleDTO) {
        return null;
    }

    
    public ResponseResult<NullData> deleteSchedule(String token, Integer scheduleId) {
        return null;
    }

    
    public ResponseResult<NullData> updateSchedule(String token, ScheduleDTO scheduleDTO) {
        return null;
    }

    
    public ResponseResult<Event> selectOneSchedule(String token, Integer scheduleId) {
        return null;
    }

    
    public ResponseResult<List<Event>> selectRangeSchedule(String token, QueryDateRangeDTO queryDateRangeDTO) {
        return null;
    }

    
    public ResponseResult<List<Event>> selectAllSchedule(Integer userId) {
        return null;
    }
    
    public ResponseResult<NullData> importCourse(String token, String courseInfo) {
        return null;
    }

    
    public ResponseResult<NullData> inputCourse(String token, CourseDTO courseDTO) {
        return null;
    }

}
