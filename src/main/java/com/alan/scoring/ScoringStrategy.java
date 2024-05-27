package com.alan.scoring;

import com.alan.model.entity.App;
import com.alan.model.entity.UserAnswer;

import java.util.List;

/**
 * @Author Alan
 * @Date 2024/5/27 09:54
 * @Description 评分策略
 */
public interface ScoringStrategy {
    /**
     * 执行评分
     * @param choices 用户选择的答案
     * @param app 应用信息
     * @return 用户答题记录
     * @throws Exception
     */
    UserAnswer doscore(List<String> choices, App app) throws Exception;
}
