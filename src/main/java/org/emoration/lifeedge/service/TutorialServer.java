package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.TutorialDTO;
import org.emoration.lifeedge.pojo.Tutorial;

import java.util.List;
import java.util.Map;

/**
 * @Author czh
 * @Description 教程操作：增、删、改、单个查、全部查
 * @Date 2023/11/16
 */
public interface TutorialServer {
    ResponseResult<NullData> insertTutorial(String userId, TutorialDTO tutorialDTO);

    ResponseResult<NullData> deleteTutorial(String userId, Long tutorialId);

    ResponseResult<NullData> updateTutorial(String userId, Long tutorialId, TutorialDTO tutorialDTO);

    ResponseResult<Tutorial> selectTutorial(String userId, Long tutorialId);

    ResponseResult<Map<String, List<Tutorial>>> selectAllTutorial(String userId);
}
