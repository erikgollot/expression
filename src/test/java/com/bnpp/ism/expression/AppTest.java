package com.bnpp.ism.expression;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.bnpp.ism.expression.ast.BaseExpression;
import com.bnpp.ism.expression.compiler.ExpressionCompiler;
import com.bnpp.ism.expression.exception.ExceptionThrowingErrorHandler;

import expression.ExpressionsGrammarLexer;
import expression.ExpressionsGrammarParser;

/**
 * Unit test for simple App.
 */

@RunWith(Parameterized.class)
public class AppTest 
    extends TestCase
{
	private static final Map<String, BigDecimal> myMap;
    static
    {
        myMap = new HashMap<String, BigDecimal>();
        myMap.put("v1", new BigDecimal(2.0f));
        myMap.put("a", new BigDecimal(2.0f));
        myMap.put("b", new BigDecimal(12.0f));
    }
	
	@Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{              
                { true, "2+5/v1;" },
                { true, "a+b;" },              
                { false, "a*(2+;" },
                               
        });
    }	
   
    
    private final boolean testValid;
    private final String testString;

    
    public AppTest(boolean testValid, String testString) {
        this.testValid = testValid;
        this.testString = testString;
    }

    @Test
    public void testApp()
    {
    	ANTLRInputStream input = new ANTLRInputStream(this.testString);
        ExpressionsGrammarLexer lexer = new ExpressionsGrammarLexer(input);
        TokenStream tokens = new CommonTokenStream(lexer);

        ExpressionsGrammarParser parser = new ExpressionsGrammarParser(tokens);

        parser.removeErrorListeners();
        parser.setErrorHandler(new ExceptionThrowingErrorHandler());

        if (this.testValid) {
            ParserRuleContext ruleContext = parser.arithmetic_expr();
            assertNull(ruleContext.exception);
            if (ruleContext.exception==null) {
            	// Make AST
            	// Parse again....
            	ExpressionCompiler compiler = new ExpressionCompiler();
            	BaseExpression astExpr = compiler.compile(this.testString);
            	System.out.println(astExpr.evaluate(myMap));
            }
        } else {
            try {
                ParserRuleContext ruleContext = parser.arithmetic_expr();
                fail("Failed on \"" + this.testString + "\"");
            } catch (RuntimeException e) {
                // deliberately do nothing
            }
        }
    }
}
