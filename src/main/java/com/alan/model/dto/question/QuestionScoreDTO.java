package com.alan.model.dto.question;

import lombok.Data;

/**
 * @Author Alan
 * @Date 2024/5/28 20:35
 * @Description
 */
@Data
public class QuestionScoreDTO {

    /**
     * 题目
     */
    private String title;

    /**
     * 用户得分
     */
    private Integer userScore;
}