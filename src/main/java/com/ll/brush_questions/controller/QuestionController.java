package com.ll.brush_questions.controller;

import com.ll.brush_questions.common.Result;
import com.ll.brush_questions.dto.QuestionDto;
import com.ll.brush_questions.entity.ChoiceQuestion;
import com.ll.brush_questions.service.ChoiceQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    ChoiceQuestionService choiceQuestionService;

    /**
     * 获取某一题目
     * @param num 题目序号
     * @param pageId 试卷编号
     * @return
     */
    @GetMapping("/getQuestion")
    public Result<QuestionDto> getQuestion(Integer num, Long pageId){
        log.info("题目序号："+num);
        log.info("试卷编号："+pageId);
        return choiceQuestionService.getQuestion(num,pageId);
    }

    /**
     * 增加选择题
     * @param question 选择题
     */
    @PostMapping("/addChoiceQuestion")
    public Result<String> addChoiceQuestion(ChoiceQuestion question){
        return choiceQuestionService.addChoiceQuestion(question);
    }
}
