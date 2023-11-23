package org.emoration.lifeedge.service.impl;

import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.TutorialDTO;
import org.emoration.lifeedge.pojo.mapper.TutorialMapper;
import org.emoration.lifeedge.service.TutorialServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ResponseResult<NullData> insertTutorial(Integer userId, TutorialDTO tutorialDTO) {
        return null;
    }

    @Override
    public ResponseResult<NullData> deleteTutorial(Integer userId, Integer tutorialId) {
        return null;
    }

    @Override
    public ResponseResult<NullData> updateTutorial(Integer userId, TutorialDTO tutorialDTO) {
        return null;
    }

    @Override
    public ResponseResult<TutorialDTO> selectTutorial(Integer userId, Integer tutorialId) {
        return null;
    }

    @Override
    public ResponseResult<List<TutorialDTO>> selectAllTutorial(Integer userId) {
        return null;
    }
}
