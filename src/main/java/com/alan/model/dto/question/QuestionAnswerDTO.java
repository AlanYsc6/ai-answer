package com.alan.model.dto.question;

import lombok.Data;

/**
 * @Author Alan
 * @Date 2024/5/28 20:35
 * @Description
 */
@Data
public class QuestionAnswerDTO {

    /**
     * 题目
     */
    private String title;

    /**
     * 用户答案
     */
    private String userAnswer;
}