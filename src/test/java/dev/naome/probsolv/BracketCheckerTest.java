package dev.naome.probsolv;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BracketCheckerTest {
    @Test
    public void testPairedSquareBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("[]")).isTrue();
    }

    @Test
    public void testEmptyString() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("")).isTrue();
    }

    @Test
    public void testUnpairedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("[[")).isFalse();
    }

    @Test
    public void testWrongOrderedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("}{")).isFalse();
    }

    @Test
    public void testWrongClosingBracket() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("{]")).isFalse();
    }

    @Test
    public void testPairedWithWhitespace() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("{ }")).isTrue();
    }

    @Test
    public void testPartiallyPairedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("{[])")).isFalse();
    }

    @Test
    public void testSimpleNestedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("{[]}")).isTrue();
    }

    @Test
    public void testSeveralPairedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("{}[]")).isTrue();
    }

    @Test
    public void testPairedAndNestedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("([{}({}[])])")).isTrue();
    }

    @Test
    public void testUnopenedClosingBracket() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("{[)][]}")).isFalse();
    }

    @Test
    public void testUnpairedAndNestedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("([{])")).isFalse();
    }

    @Test
    public void testPairedAndWrongNestedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("[({]})")).isFalse();
    }

    @Test
    public void testPairedAndWrongNestedBracketsButInnermostAreCorrect() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("[({}])")).isFalse();
    }

    @Test
    public void testPairedAndIncompleteBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("{}[")).isFalse();
    }

    @Test
    public void testTooManyClosingBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("[]]")).isFalse();
    }

    @Test
    public void testEarlyUnexpectedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid(")()")).isFalse();
    }

    @Test
    public void testEarlyMismatchedBrackets() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("{)()")).isFalse();
    }

    @Test
    public void testMathExpression() {
        BracketCheckerService bracketChecker = new BracketCheckerService();
        assertThat(bracketChecker.areBracketsValid("(((185 + 223.85) * 15) - 543)/2")).isTrue();
    }

    @Test
    public void testComplexLatexExpression() {
        BracketCheckerService bracketChecker = new BracketCheckerService();

        assertThat(bracketChecker.areBracketsValid(
                "\\left(\\begin{array}{cc} \\frac{1}{3} & x\\\\ \\mathrm{e}^{x} &... x^2 \\end{array}\\right)"))
                .isTrue();
    }

}
