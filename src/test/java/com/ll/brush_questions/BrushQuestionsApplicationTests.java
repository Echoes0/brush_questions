package com.ll.brush_questions;

import com.ll.brush_questions.common.Result;
import com.ll.brush_questions.service.ChoiceQuestionService;
import com.ll.brush_questions.service.PaperService;
import com.ll.brush_questions.utils.PaperChange;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

import static com.ll.brush_questions.utils.CreateMybatisFile.*;
import static com.ll.brush_questions.utils.PaperChange.*;

@SpringBootTest
class BrushQuestionsApplicationTests {

    @Resource
    ChoiceQuestionService choiceQuestionService;

    @Resource
    PaperService paperService;

    @Test
    void contextLoads() {
    }


    @Test
    void test() throws IOException {

        GenerateMapper();
        GenerateService();
        GenerateImpl();
    }

    @Test
    void paper() throws IOException {
        PaperChange.analysisPaper("D:/700-Document/data/2023408.doc");
        PaperChange.addPaper(PaperChange.name);
        Result<Long> result = paperService.addPaper(paper);

        addQuestions(result.getData());
        choiceQuestionService.addChoiceQuestion(choiceQuestions);

    }

}
