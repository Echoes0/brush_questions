package com.ll.brush_questions.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class ChoiceQuestion {

  @TableId
  private Long id;
  private int num;
  private String title;
  private String choiceA;
  private String choiceB;
  private String choiceC;
  private String choiceD;
  private String correctAnswer;
  private java.sql.Timestamp createdAt;
  private java.sql.Timestamp updatedAt;
  private long paperId;
}
