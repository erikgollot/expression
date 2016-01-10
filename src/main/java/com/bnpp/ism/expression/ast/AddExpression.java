package com.bnpp.ism.expression.ast;

import java.math.BigDecimal;
import java.util.Map;

public class AddExpression implements BaseExpression {
	public AddExpression(BaseExpression left, BaseExpression right) {
		super();
		this.left = left;
		this.right = right;
	}

	private BaseExpression left;
	private BaseExpression right;

	@Override
	public BigDecimal evaluate(Map<String, BigDecimal> variables) {
		return left.evaluate(variables).add(right.evaluate(variables));
	}
}
