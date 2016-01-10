package com.bnpp.ism.expression.ast;

import java.math.BigDecimal;
import java.util.Map;

public class VariableExpression implements BaseExpression {
	public VariableExpression(String name) {
		super();
		this.name = name;
	}

	private String name;	

	@Override
	public BigDecimal evaluate(Map<String, BigDecimal> variables) {
		return variables.get(name);
	}
}
