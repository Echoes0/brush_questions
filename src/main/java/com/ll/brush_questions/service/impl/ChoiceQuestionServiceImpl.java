package com.ll.brush_questions.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ll.brush_questions.common.Result;
import com.ll.brush_questions.dto.Choice;
import com.ll.brush_questions.dto.QuestionDto;
import com.ll.brush_questions.entity.ChoiceQuestion;
import com.ll.brush_questions.mapper.ChoiceQuestionMapper;
import com.ll.brush_questions.service.ChoiceQuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoiceQuestionServiceImpl extends ServiceImpl<ChoiceQuestionMapper, ChoiceQuestion> implements ChoiceQuestionService {

    @Override
    public Result<QuestionDto> getQuestion(int num, long pageId) {
        ChoiceQuestion question = query().eq("num", num).eq("paper_id", pageId).one();
        if (question==null){
            return Result.error("该试卷还没有题");
        }
        QuestionDto questionDto = new QuestionDto();
        questionDto.setNum(num);
        questionDto.setQContent(question.getTitle());
        List<Choice> choices=new ArrayList<>();

        choices.add(
                displayOption(
                        question.getChoiceA(),
                        "A"
                )
        );
        choices.add(
                displayOption(
                        question.getChoiceB(),
                        "B"
                )
        );
        choices.add(
                displayOption(
                        question.getChoiceC(),
                        "C"
                )
        );
        choices.add(
                displayOption(
                        question.getChoiceD(),
                        "D"
                )
        );

        questionDto.setChoice(choices);
        return Result.success(questionDto);
    }

    @Override
    public Result<String> addChoiceQuestion(ChoiceQuestion question) {
        save(question);
        return Result.success("导入试题成功");
    }

    @Override
    public Result<String> addChoiceQuestion(List<ChoiceQuestion> questions) {

        saveBatch(questions);
        return Result.success("批量导入试题成功");
    }


    // 解析选项
    private static Choice displayOption(String option,String c) {
        if (option != null) {
            Choice choice = new Choice();
            choice.setOption(c);
            choice.setContent(option);

            return  choice;
        }
        return null;
    }
}