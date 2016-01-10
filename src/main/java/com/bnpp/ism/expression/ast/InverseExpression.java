package com.bnpp.ism.expression.ast;

import java.math.BigDecimal;
import java.util.Map;

public class InverseExpression implements BaseExpression {
	public InverseExpression(BaseExpression expr) {
		super();
		this.expr = expr;
	}

	private BaseExpression expr;

	@Override
	public BigDecimal evaluate(Map<String, BigDecimal> variables) {
		return expr.evaluate(variables).multiply(new BigDecimal(-1));
	}
}
