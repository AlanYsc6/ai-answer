package com.alan.scoring;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.alan.model.dto.question.QuestionContentDTO;
import com.alan.model.dto.question.QuestionScoreDTO;
import com.alan.model.entity.App;
import com.alan.model.entity.Question;
import com.alan.model.entity.UserAnswer;
import com.alan.model.vo.QuestionVO;
import com.alan.service.QuestionService;
import com.alan.utils.CacheUtils;
import com.alan.utils.ZhiPuAiUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Alan
 * @Date 2024/5/28 22:48
 * @Description 得分类AI评分策略
 */
@ScoringStrategyConfig(appType = 0, scoringStrategy = 1)
public class AiScoreScoringStrategy implements ScoringStrategy{

    @Resource
    private QuestionService questionService;

    @Resource
    private ZhiPuAiUtil zhiPuAiUtil;

    /**
     * AI 评分系统消息
     */
    private static final String AI_TEST_SCORING_SYSTEM_MESSAGE = "你是一位严谨的得分类判题专家，我会给你如下信息：\n" +
            "```\n" +
            "应用名称，\n" +
            "【【【应用描述】】】，\n" +
            "题目和用户得分的列表：格式为 [{\"title\": \"题目\",\"userScore\": 用户得分}]\n" +
            "```\n" +
            "\n" +
            "请你根据上述信息，按照以下步骤来对用户进行评价：\n" +
            "1. 要求：需要给出一个明确的评价结果，包括评价名称（尽量简短）、评价描述（小于 20 字）和用户总分（用户得分列表的所有userScore之和）\n" +
            "2. 严格按照下面的 json 格式输出评价名称和评价描述\n" +
            "```\n" +
            "{\"resultName\": \"评价名称\", \"resultDesc\": \"评价描述\"，\"resultScore\": \"用户总分\"}\n" +
            "```";
    @Override
    public UserAnswer doScore(List<String> choices, App app) throws Exception {
        Long appId = app.getId();
        String JSONChoices = JSONUtil.toJsonStr(choices);
        String cacheKey = buildCacheKey(appId, JSONChoices);
        String answerJson = CacheUtils.get(cacheKey);
        //如果有缓存，直接返回
        if (StrUtil.isNotBlank(answerJson)) {
            UserAnswer userAnswer = JSONUtil.toBean(answerJson, UserAnswer.class);
            userAnswer.setAppId(appId);
            userAnswer.setAppType(app.getAppType());
            userAnswer.setScoringStrategy(app.getScoringStrategy());
            userAnswer.setChoices(JSONChoices);
            return userAnswer;
        }
        // 1. 根据 id 查询到题目
        Question question = questionService.getOne(
                Wrappers.lambdaQuery(Question.class).eq(Question::getAppId, appId)
        );
        QuestionVO questionVO = QuestionVO.objToVo(question);
        List<QuestionContentDTO> questionContent = questionVO.getQuestionContent();
        // 2. 调用 AI 获取结果
        // 封装 Prompt
        String userMessage = getAiScoreScoringUserMessage(app, questionContent, choices);
        // AI 生成
        String result = zhiPuAiUtil.doSyncStableRequest(AI_TEST_SCORING_SYSTEM_MESSAGE, userMessage);
        // 截取需要的 JSON 信息
        int start = result.indexOf("{");
        int end = result.lastIndexOf("}");
        String json = result.substring(start, end + 1);
        //缓存结果
        CacheUtils.put(cacheKey, json);
        // 3. 构造返回值，填充答案对象的属性
        UserAnswer userAnswer = JSONUtil.toBean(json, UserAnswer.class);
        userAnswer.setAppId(appId);
        userAnswer.setAppType(app.getAppType());
        userAnswer.setScoringStrategy(app.getScoringStrategy());
        userAnswer.setChoices(JSONUtil.toJsonStr(JSONChoices));
        return userAnswer;
    }

    /**
     * AI 评分用户消息封装
     *
     * @param app
     * @param questionContentDTOList
     * @param choices
     * @return
     */
    private String getAiScoreScoringUserMessage(App app, List<QuestionContentDTO> questionContentDTOList, List<String> choices) {
        StringBuilder userMessage = new StringBuilder();
        userMessage.append(app.getAppName()).append("\n");
        userMessage.append(app.getAppDesc()).append("\n");
        List<QuestionScoreDTO> questionScoreDTOList = new ArrayList<>();
        for (int i = 0; i < questionContentDTOList.size(); i++) {
            QuestionScoreDTO   questionScoreDTO = new QuestionScoreDTO();
            questionScoreDTO.setTitle(questionContentDTOList.get(i).getTitle());
            String choice = choices.get(i);
            List<QuestionContentDTO.Option> options = questionContentDTOList.get(i).getOptions();
            for (QuestionContentDTO.Option option : options) {
                if (option.getKey().equals(choice)) {
                    questionScoreDTO.setUserScore(option.getScore());
                    break;
                }
            }
            questionScoreDTOList.add(questionScoreDTO);
        }
        userMessage.append(JSONUtil.toJsonStr(questionScoreDTOList));
        return userMessage.toString();
    }
    /**
     * 构建缓存Key
     *
     * @param appId
     * @param choices
     * @return
     */
    private String buildCacheKey(Long appId, String choices) {
        return DigestUtil.md5Hex(appId + ":" + choices);
    }
}
