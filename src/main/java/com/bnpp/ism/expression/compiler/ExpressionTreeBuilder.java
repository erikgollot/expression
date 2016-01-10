package com.bnpp.ism.expression.compiler;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.Stack;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.bnpp.ism.expression.ast.AddExpression;
import com.bnpp.ism.expression.ast.BaseExpression;
import com.bnpp.ism.expression.ast.DivideExpression;
import com.bnpp.ism.expression.ast.Expression;
import com.bnpp.ism.expression.ast.InverseExpression;
import com.bnpp.ism.expression.ast.MinusExpression;
import com.bnpp.ism.expression.ast.MultExpression;
import com.bnpp.ism.expression.ast.NumericConstExpression;
import com.bnpp.ism.expression.ast.VariableExpression;

import expression.ExpressionsGrammarBaseListener;
import expression.ExpressionsGrammarParser.ArithmeticExpressionDivContext;
import expression.ExpressionsGrammarParser.ArithmeticExpressionMinusContext;
import expression.ExpressionsGrammarParser.ArithmeticExpressionMultContext;
import expression.ExpressionsGrammarParser.ArithmeticExpressionNegationContext;
import expression.ExpressionsGrammarParser.ArithmeticExpressionNumericEntityContext;
import expression.ExpressionsGrammarParser.ArithmeticExpressionParensContext;
import expression.ExpressionsGrammarParser.ArithmeticExpressionPlusContext;
import expression.ExpressionsGrammarParser.NumericConstContext;
import expression.ExpressionsGrammarParser.NumericVariableContext;

public class ExpressionTreeBuilder extends ExpressionsGrammarBaseListener {
	private Expression expression;
	private Stack<BaseExpression> expressions = new Stack<BaseExpression>();

	
	public BaseExpression getExpression() {
		return expressions.pop();
	}
	
	@Override
	public void enterArithmeticExpressionMult(
			ArithmeticExpressionMultContext ctx) {
		// TODO Auto-generated method stub
		super.enterArithmeticExpressionMult(ctx);
	}

	@Override
	public void exitArithmeticExpressionMult(ArithmeticExpressionMultContext ctx) {
		BaseExpression right = expressions.pop();
		BaseExpression left = expressions.pop();
		MultExpression exp = new MultExpression(left, right);
		expressions.push(exp);
	}

	@Override
	public void enterArithmeticExpressionMinus(
			ArithmeticExpressionMinusContext ctx) {
		// TODO Auto-generated method stub
		super.enterArithmeticExpressionMinus(ctx);
	}

	@Override
	public void exitArithmeticExpressionMinus(
			ArithmeticExpressionMinusContext ctx) {
		BaseExpression right = expressions.pop();
		BaseExpression left = expressions.pop();
		MinusExpression exp = new MinusExpression(left, right);
		expressions.push(exp);
	}

	@Override
	public void enterNumericConst(NumericConstContext ctx) {
		super.enterNumericConst(ctx);
	}

	@Override
	public void exitNumericConst(NumericConstContext ctx) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        String pattern = "#0.0#";

        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        BigDecimal value;
        try {
            value = (BigDecimal)decimalFormat.parse(ctx.getText());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
		expressions.add(new NumericConstExpression(value));
	}

	@Override
	public void enterArithmeticExpressionParens(
			ArithmeticExpressionParensContext ctx) {
		// TODO Auto-generated method stub
		super.enterArithmeticExpressionParens(ctx);
	}

	@Override
	public void exitArithmeticExpressionParens(
			ArithmeticExpressionParensContext ctx) {
		// TODO Auto-generated method stub
		super.exitArithmeticExpressionParens(ctx);
	}

	@Override
	public void enterArithmeticExpressionNumericEntity(
			ArithmeticExpressionNumericEntityContext ctx) {
		// TODO Auto-generated method stub
		super.enterArithmeticExpressionNumericEntity(ctx);
	}

	@Override
	public void exitArithmeticExpressionNumericEntity(
			ArithmeticExpressionNumericEntityContext ctx) {
		// TODO Auto-generated method stub
		super.exitArithmeticExpressionNumericEntity(ctx);
	}

	@Override
	public void enterArithmeticExpressionDiv(ArithmeticExpressionDivContext ctx) {
		// TODO Auto-generated method stub
		super.enterArithmeticExpressionDiv(ctx);
	}

	@Override
	public void exitArithmeticExpressionDiv(ArithmeticExpressionDivContext ctx) {
		BaseExpression right = expressions.pop();
		BaseExpression left = expressions.pop();
		DivideExpression exp = new DivideExpression(left, right);
		expressions.push(exp);
	}

	@Override
	public void enterArithmeticExpressionPlus(
			ArithmeticExpressionPlusContext ctx) {
		// TODO Auto-generated method stub
		super.enterArithmeticExpressionPlus(ctx);
	}

	@Override
	public void exitArithmeticExpressionPlus(ArithmeticExpressionPlusContext ctx) {
		BaseExpression right = expressions.pop();
		BaseExpression left = expressions.pop();
		AddExpression exp = new AddExpression(left, right);
		expressions.push(exp);
	}

	@Override
	public void enterNumericVariable(NumericVariableContext ctx) {
		super.enterNumericVariable(ctx);
	}

	@Override
	public void exitNumericVariable(NumericVariableContext ctx) {
		expressions.add(new VariableExpression(ctx.getText()));
		
	}

	@Override
	public void enterArithmeticExpressionNegation(
			ArithmeticExpressionNegationContext ctx) {
		// TODO Auto-generated method stub
		super.enterArithmeticExpressionNegation(ctx);
	}

	@Override
	public void exitArithmeticExpressionNegation(
			ArithmeticExpressionNegationContext ctx) {
		BaseExpression right = expressions.pop();
		InverseExpression exp = new InverseExpression(right);
		expressions.push(exp);
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		super.enterEveryRule(ctx);
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		super.exitEveryRule(ctx);
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		super.visitTerminal(node);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		super.visitErrorNode(node);
	}

}
