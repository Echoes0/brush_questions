package com.ll.brush_questions.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDto {
    int num;
    String qContent;
    List<Choice> choice;
}
