package org.emoration.lifeedge.service.impl;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.TutorialDTO;
import org.emoration.lifeedge.pojo.Tutorial;
import org.emoration.lifeedge.pojo.mapper.TutorialMapper;
import org.emoration.lifeedge.service.TutorialServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author czh
 * @Description TODO
 * @Date 2023/11/16
 */
@Service
public class TutorialServerImpl implements TutorialServer {
    @Autowired
    TutorialMapper tutorialMapper;

    @Override
    public ResponseResult<NullData> insertTutorial(String userId, TutorialDTO tutorialDTO) {
        Tutorial tutorial = new Tutorial();
        tutorial.setName(tutorialDTO.getName());
        tutorial.setDescription(tutorialDTO.getDescription());
        if (tutorialMapper.insert(tutorial) == 1) {
            return ResponseResult.success("添加成功");
        }
        return ResponseResult.fail("添加失败");
    }

    @Override
    public ResponseResult<NullData> deleteTutorial(String userId, Long tutorialId) {
        if (tutorialMapper.deleteById(tutorialId) == 1) {
            return ResponseResult.success("删除成功");
        }
        return ResponseResult.fail("教程不存在");
    }

    @Override
    public ResponseResult<NullData> updateTutorial(String userId, Long tutorialId, TutorialDTO tutorialDTO) {
        Tutorial tutorial = new Tutorial();
        tutorial.setId(tutorialId);
        tutorial.setName(tutorialDTO.getName());
        tutorial.setDescription(tutorialDTO.getDescription());
        if (tutorialMapper.updateById(tutorial) == 1) {
            return ResponseResult.success("修改成功");
        }
        return ResponseResult.fail("教程不存在");
    }

    @Override
    public ResponseResult<Tutorial> selectTutorial(String userId, Long tutorialId) {
        Tutorial tutorial = tutorialMapper.selectById(tutorialId);
        if (tutorial == null) {
            return ResponseResult.fail("教程不存在");
        }
        return ResponseResult.data(tutorial);
    }

    @Override
    public ResponseResult<Map<String, List<Tutorial>>> selectAllTutorial(String userId) {
        List<Tutorial> tutorialList = tutorialMapper.selectList(null);
        if (tutorialList == null) {
            return ResponseResult.fail("查询失败");
        }
        return ResponseResult.data(Map.of("tutorialList", tutorialList));
    }
}
