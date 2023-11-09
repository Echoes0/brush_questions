package com.ll.brush_questions.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ll.brush_questions.common.Result;
import com.ll.brush_questions.dto.PaperDto;
import com.ll.brush_questions.entity.Paper;
import com.ll.brush_questions.mapper.PaperMapper;
import com.ll.brush_questions.service.PaperService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {
    @Override
    public Result<List<PaperDto>> listName() {
        List<Paper> papers = list();

        List<PaperDto> paperDtoS=new ArrayList<>();

        for(Paper paper:papers){
            PaperDto paperDto=new PaperDto();
            paperDto.setValue(String.valueOf(paper.getId()));
            paperDto.setTitle(paper.getTitle());
            paperDtoS.add(paperDto);
        }
        return Result.success(paperDtoS);
    }

    @Override
    public Result<Paper> getPageById(Long id) {
        return Result.success(getById(id));
    }

    @Override
    public Result<Long> addPaper(Paper paper) {
        save(paper);
        return Result.success(paper.getId());
    }
}