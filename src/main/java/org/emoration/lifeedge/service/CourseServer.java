package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.CourseDTO;

/**
 * @Author czh
 * @Description 课程操作：自动导入、手动输入
 * @Date 2023/11/16
 */
public interface CourseServer {
    ResponseResult<NullData> importCourse(Integer userId, String courseInfo);

    ResponseResult<NullData> inputCourse(Integer userId, CourseDTO courseDTO);
}
