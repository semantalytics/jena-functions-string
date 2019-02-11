
package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class SubstringAfterLast extends AbstractFunction implements StringFunction {

    protected SubstringAfterLast() {
        super(2, StringVocabulary.substringAfterLast.stringValue());
    }

    private SubstringAfterLast(final SubstringAfterLast substringAfterLast) {
        super(substringAfterLast);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String separator = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.substringAfterLast(string, separator));
    }

    @Override
    public SubstringAfterLast copy() {
        return new SubstringAfterLast(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.substringAfterLast.name();
    }
}
