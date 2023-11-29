package org.emoration.lifeedge.controller;

import lombok.extern.slf4j.Slf4j;
import org.emoration.lifeedge.common.NullData;
import org.emoration.lifeedge.common.ResponseResult;
import org.emoration.lifeedge.controller.DTO.TutorialDTO;
import org.emoration.lifeedge.pojo.Tutorial;
import org.emoration.lifeedge.service.TutorialServer;
import org.emoration.lifeedge.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author czh
 * @Description Tutorial
 * @Date 2023/11/16
 */
@Slf4j
@RestController
@RequestMapping("/")
public class TutorialController {
    @Autowired
    TutorialServer tutorialServer;
    @Autowired
    TokenUtil tokenUtil;

    @PostMapping("/manager/tutorial/create")
    public ResponseResult<NullData> insertTutorial(@RequestHeader("Authorization") String token, @RequestBody TutorialDTO tutorialDTO) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (!userId.equals("system")) return ResponseResult.error("权限不足");
        if (tutorialDTO == null)
            return ResponseResult.fail("教程信息不能为空");

        return tutorialServer.insertTutorial(userId, tutorialDTO);
    }

    @DeleteMapping("/manager/tutorial/delete/{tutorialId}")
    public ResponseResult<NullData> deleteTutorial(@RequestHeader("Authorization") String token, @PathVariable Long tutorialId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (!userId.equals("system")) return ResponseResult.error("权限不足");

        return tutorialServer.deleteTutorial(userId, tutorialId);
    }

    @PutMapping("/manager/tutorial/change/{tutorialId}")
    public ResponseResult<NullData> updateTutorial(@RequestHeader("Authorization") String token, @PathVariable Long tutorialId, @RequestBody TutorialDTO tutorialDTO) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }
        if (!userId.equals("system")) return ResponseResult.error("权限不足");
        if (tutorialDTO == null)
            return ResponseResult.fail("教程信息不能为空");

        return tutorialServer.updateTutorial(userId, tutorialId, tutorialDTO);
    }

    @GetMapping({"/user/tutorial/selectOne/{tutorialId}", "/manager/tutorial/selectOne/{tutorialId}"})
    public ResponseResult<Tutorial> selectTutorial(@RequestHeader("Authorization") String token, @PathVariable Long tutorialId) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }

        return tutorialServer.selectTutorial(userId, tutorialId);
    }

    @GetMapping({"/manager/tutorial/selectAll", "/user/tutorial/selectAll"})
    public ResponseResult<Map<String, List<Tutorial>>> selectAllTutorial(@RequestHeader("Authorization") String token) {
        String userId;
        try {
            userId = tokenUtil.parseTokenToUserId(token);
            if (userId == null) throw new Exception();
        } catch (Exception e) {
            return ResponseResult.error("token错误");
        }

        return tutorialServer.selectAllTutorial(userId);
    }
}
