package org.emoration.lifeedge.controller;

import lombok.extern.slf4j.Slf4j;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.TutorialDTO;
import org.emoration.lifeedge.service.TutorialServer;
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
@RequestMapping("/") // Tutorial
public class TutorialController {
    @Autowired
    TutorialServer tutorialServer;

    
    public ResponseResult<NullData> insertTutorial(String token, TutorialDTO tutorialDTO) {
        return null;
    }

    
    public ResponseResult<NullData> deleteTutorial(String token, Integer tutorialId) {
        return null;
    }

    
    public ResponseResult<NullData> updateTutorial(String token, TutorialDTO tutorialDTO) {
        return null;
    }

    
    public ResponseResult<TutorialDTO> selectTutorial(String token, Integer tutorialId) {
        return null;
    }

    
    public ResponseResult<List<TutorialDTO>> selectAllTutorial(Integer userId) {
        return null;
    }
}
