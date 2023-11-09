package com.ll.brush_questions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ll.brush_questions.common.Result;
import com.ll.brush_questions.dto.QuestionDto;
import com.ll.brush_questions.entity.ChoiceQuestion;

import java.util.List;

public interface ChoiceQuestionService extends IService<ChoiceQuestion> {
    Result<QuestionDto> getQuestion(int num, long pageId);

    Result<String> addChoiceQuestion(ChoiceQuestion question);

    Result<String> addChoiceQuestion(List<ChoiceQuestion> questions);
}
