package com.ll.brush_questions.controller;

import com.ll.brush_questions.common.Result;
import com.ll.brush_questions.dto.PaperDto;
import com.ll.brush_questions.entity.ChoiceQuestion;
import com.ll.brush_questions.entity.Paper;
import com.ll.brush_questions.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/paper")
public class PaperController {

    @Resource
    PaperService paperService;

    /**
     * 获取所有试卷名称
     */
    @GetMapping("/listName")
    public Result<List<PaperDto>> listName(){
        return paperService.listName();
    }

    /**
     * 根据id获取试卷
     */
    @GetMapping("/getPageById/{id}")
    public Result<Paper> getPageById(@PathVariable Long id){
        return paperService.getPageById(id);
    }


    /**
     * 增加试卷
     * @param paper 试卷
     */
    @PostMapping("/addChoiceQuestion")
    public Result<Long> addChoiceQuestion(Paper paper){
        return paperService.addPaper(paper);
    }

}
