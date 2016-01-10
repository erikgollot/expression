package com.bnpp.ism.expression.ast;

import java.math.BigDecimal;
import java.util.Map;

public class NumericConstExpression implements BaseExpression {
	public NumericConstExpression(BigDecimal value) {
		super();
		this.value = value;
	}

	private BigDecimal value;	

	@Override
	public BigDecimal evaluate(Map<String, BigDecimal> variables) {
		return value;
	}
}
