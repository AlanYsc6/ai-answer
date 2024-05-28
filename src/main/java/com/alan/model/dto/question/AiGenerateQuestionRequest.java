package com.alan.model.dto.question;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2024/5/28 18:48
 * @Description AI 自动生成题目请求
 */
@Data
public class AiGenerateQuestionRequest implements Serializable {

    /**
     * 应用 id
     */
    private Long appId;

    /**
     * 题目数
     */
    int questionNumber = 10;

    /**
     * 选项数
     */
    int optionNumber = 2;

    private static final long serialVersionUID = 1L;
}
