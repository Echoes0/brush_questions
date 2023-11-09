package com.ll.brush_questions.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Paper {

  @TableId
  private Long id;
  private String title;
  private String subject;
  private long creator;
  private java.sql.Timestamp creationTime;
  private long totalMarks;
  private int count;
  private int duration;
}
