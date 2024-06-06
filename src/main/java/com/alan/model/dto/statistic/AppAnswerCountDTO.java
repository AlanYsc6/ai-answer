package com.alan.model.dto.statistic;

import lombok.Data;

/**
 * @Author Alan
 * @Date 2024/6/6 21:46
 * @Description 用户提交答案数统计
 */
@Data
public class AppAnswerCountDTO {

    private Long appId;
    private Long answerCount;
}
