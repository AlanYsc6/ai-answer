package com.alan.controller;

import com.alan.common.BaseResponse;
import com.alan.common.ErrorCode;
import com.alan.common.ResultUtils;
import com.alan.exception.ThrowUtils;
import com.alan.mapper.UserAnswerMapper;
import com.alan.model.dto.statistic.AppAnswerCountDTO;
import com.alan.model.dto.statistic.AppAnswerResultCountDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Alan
 * @Date 2024/6/6 22:13
 * @Description APP 统计中心
 */
@RestController
@RequestMapping("/app/statistic")
@Slf4j
public class AppStatisticController {
    @Resource
    private UserAnswerMapper userAnswerMapper;

    /**
     * 热门应用统计(Top 10)
     * @return
     */
    @GetMapping("/answer_count")
    public BaseResponse<List<AppAnswerCountDTO>> getAppAnswerCount() {
        return ResultUtils.success(userAnswerMapper.doAppAnswerCount());
    }

    /**
     * 答题结果分布统计
     * @param appId
     * @return
     */
    @GetMapping("/answer_result_count")
    public BaseResponse<List<AppAnswerResultCountDTO>> getAppAnswerResultCount(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userAnswerMapper.doAppAnswerResultCount(appId));
    }
}
