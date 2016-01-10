package com.bnpp.ism.expression.ast;

import java.math.BigDecimal;
import java.util.Map;

public interface BaseExpression {
	BigDecimal evaluate(Map<String, BigDecimal> variables);
}
