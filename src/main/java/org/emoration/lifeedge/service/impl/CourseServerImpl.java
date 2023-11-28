package org.emoration.lifeedge.service.impl;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.CourseDTO;
import org.emoration.lifeedge.pojo.mapper.EventMapper;
import org.emoration.lifeedge.service.CourseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return null;
    }

    @Override
    public ResponseResult<NullData> inputCourse(String userId, CourseDTO courseDTO) {
        return null;
    }
}
