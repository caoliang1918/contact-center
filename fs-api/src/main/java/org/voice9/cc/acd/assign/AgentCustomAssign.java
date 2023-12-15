package org.voice9.cc.acd.assign;

import org.apache.commons.lang3.StringUtils;
import com.voice9.core.constant.Constant;
import com.voice9.core.po.AgentInfo;
import com.voice9.core.strategy.AgentStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by caoliang on 2021/8/3
 */
public class AgentCustomAssign implements AgentStrategy {

    private Logger logger = LoggerFactory.getLogger(AgentCustomAssign.class);

    /**
     * 自定义分配策略
     */
    private String customExpression;

    public AgentCustomAssign(String customExpression) {
        this.customExpression = customExpression;
    }

    @Override
    public Long calculateLevel(AgentInfo agentInfo) {
        EvaluationContext context = new StandardEvaluationContext();
        //技能相关
        agentInfo.getSkillAgents().forEach(skill -> {
            context.setVariable("skill_" + skill.getSkillId(), skill.getRankValue());
        });
        //时间变量
        context.setVariable(Constant.LONG_READY, -agentInfo.getStateTime());
        context.setVariable(Constant.READY_TIMES, agentInfo.getReadyTimes());
        context.setVariable(Constant.SEREVICE_TIMES, agentInfo.getServiceTime());
        return calculate(customExpression, context);
    }

    /**
     * @param expression
     * @param context
     * @return
     */
    private Long calculate(String expression, EvaluationContext context) {
        if (StringUtils.isBlank(expression)) {
            return 0L;
        }
        try {
            ExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(expression);
            return exp.getValue(context, Long.class);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return 0L;
    }

}
