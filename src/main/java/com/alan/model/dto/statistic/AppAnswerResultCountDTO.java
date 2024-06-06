package com.alan.model.dto.statistic;

import lombok.Data;

/**
 * @Author Alan
 * @Date 2024/6/6 21:46
 * @Description APP答案结果统计
 */
@Data
public class AppAnswerResultCountDTO {
    // 结果名称
    private String resultName;
    // 对应个数
    private String resultCount;
}
