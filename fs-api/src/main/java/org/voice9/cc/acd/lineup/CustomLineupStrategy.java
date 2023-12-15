package org.voice9.cc.acd.lineup;

import org.apache.commons.lang3.StringUtils;
import com.voice9.core.constant.Constant;
import com.voice9.core.po.CallInfo;
import com.voice9.core.strategy.LineupStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by caoliang on 2021/8/2
 * <p>
 * 自定义排队策略
 */
public class CustomLineupStrategy implements LineupStrategy {
    private Logger logger = LoggerFactory.getLogger(CustomLineupStrategy.class);

    private String expression;

    public CustomLineupStrategy(String expression) {
        this.expression = expression;
    }

    @Override
    public Long calculateLevel(CallInfo callInfo) {
        EvaluationContext context = new StandardEvaluationContext();
        context.setVariable(Constant.START_TIME, callInfo.getCallTime());
        context.setVariable(Constant.QUEUE_TIME, callInfo.getQueueStartTime());
        if (callInfo.getProcessData().isEmpty()) {
            return this.calculate(expression, context);
        }
        callInfo.getProcessData().keySet().forEach(key -> {
            Object value = callInfo.getProcessData().get(key);
            if (value != null && StringUtils.isNumeric(value.toString())) {
                context.setVariable(key, Double.valueOf(value.toString()));
            }
        });
        return this.calculate(expression, context);
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
