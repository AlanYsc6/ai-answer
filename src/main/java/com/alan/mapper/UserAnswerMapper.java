package com.alan.mapper;

import com.alan.model.dto.statistic.AppAnswerCountDTO;
import com.alan.model.dto.statistic.AppAnswerResultCountDTO;
import com.alan.model.entity.UserAnswer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author Alan
* @description 针对表【user_answer(用户答题记录)】的数据库操作Mapper
* @createDate 2024-05-26 14:27:51
* @Entity com.alan.model.entity.UserAnswer
*/
public interface UserAnswerMapper extends BaseMapper<UserAnswer> {

    List<AppAnswerCountDTO> doAppAnswerCount();

    List<AppAnswerResultCountDTO> doAppAnswerResultCount(Long appId);
}




