package com.bnpp.ism.expression.compiler;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;

import com.bnpp.ism.expression.ast.BaseExpression;
import com.bnpp.ism.expression.exception.ExceptionThrowingErrorHandler;

import expression.ExpressionsGrammarLexer;
import expression.ExpressionsGrammarParser;

public class ExpressionCompiler {
	public BaseExpression compile(String inputString) {
		ANTLRInputStream input = new ANTLRInputStream(inputString);
		ExpressionsGrammarLexer lexer = new ExpressionsGrammarLexer(input);
		TokenStream tokens = new CommonTokenStream(lexer);
		ExpressionsGrammarParser parser = new ExpressionsGrammarParser(tokens);

		ExpressionTreeBuilder treeBuilder = new ExpressionTreeBuilder();
		parser.addParseListener(treeBuilder);
		parser.setErrorHandler(new ExceptionThrowingErrorHandler());

		parser.arithmetic_expr();

		return treeBuilder.getExpression();
	}
}
