package org.emoration.lifeedge.service.impl;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.CourseDTO;
import org.emoration.lifeedge.pojo.Event;
import org.emoration.lifeedge.pojo.mapper.EventMapper;
import org.emoration.lifeedge.service.CourseServer;
import org.emoration.lifeedge.utils.CourseParseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/16
 */
@Service
public class CourseServerImpl implements CourseServer {
    @Autowired
    EventMapper eventMapper;

    @Override
    public ResponseResult<NullData> importCourse(String userId, String courseInfo) {
        List<Event> eventList = CourseParseUtil.doParse(courseInfo);
        if (eventList == null)
            return ResponseResult.fail("课程信息解析失败");
        for (Event event : eventList) {
            event.setOwnerId(userId);
            event.setReminderId(null);
            eventMapper.insert(event);
        }
        return ResponseResult.success("课程导入成功");
    }

    @Override
    public ResponseResult<NullData> inputCourse(String userId, CourseDTO courseDTO) {
        try {
            List<Event> eventList = CourseParseUtil.courseDTO_to_eventList(courseDTO);
            for (Event event : eventList) {
                event.setOwnerId(userId);
                event.setReminderId(null);
                eventMapper.insert(event);
            }
            return ResponseResult.success("课程导入成功");
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }
}
