package com.semantalytics.jena.function.string;

import jdk.internal.org.objectweb.asm.tree.analysis.NodeValue;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

public final class EqualsIgnoreCase extends FunctionBase2 {

    protected EqualsIgnoreCase() {
        super(2, StringVocabulary.equalsIgnoreCase.stringNodeValue());
    }

    @Override
    public NodeValue exec(final NodeValue... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringNodeValue();
        final String secondString = assertStringLiteral(values[1]).stringNodeValue();

        return literal(StringUtils.equalsIgnoreCase(firstString, secondString));
    }
}
