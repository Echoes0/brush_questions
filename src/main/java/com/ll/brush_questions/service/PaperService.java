package com.ll.brush_questions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ll.brush_questions.common.Result;
import com.ll.brush_questions.dto.PaperDto;
import com.ll.brush_questions.entity.Paper;

import java.util.List;

public interface PaperService extends IService<Paper> {
    Result<List<PaperDto>> listName();

    Result<Paper> getPageById(Long id);

    Result<Long> addPaper(Paper paper);
}
