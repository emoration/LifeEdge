package org.emoration.lifeedge.service;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.TutorialDTO;

import java.util.List;

/**
 * @Author czh
 * @Description 教程操作：增、删、改、单个查、全部查
 * @Date 2023/11/16
 */
public interface TutorialServer {
    ResponseResult<NullData> insertTutorial(String userId, TutorialDTO tutorialDTO);

    ResponseResult<NullData> deleteTutorial(String userId, Integer tutorialId);

    ResponseResult<NullData> updateTutorial(String userId, TutorialDTO tutorialDTO);

    ResponseResult<TutorialDTO> selectTutorial(String userId, Integer tutorialId);

    ResponseResult<List<TutorialDTO>> selectAllTutorial(String userId);
}
