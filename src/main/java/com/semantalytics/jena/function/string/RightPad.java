

package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class RightPad extends AbstractFunction implements StringFunction {

    protected RightPad() {
        super(2, StringVocabulary.rightPad.stringValue());
    }

    private RightPad(final RightPad rightPad) {
        super(rightPad);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final int size = assertNumericLiteral(values[1]).intValue();

        return literal(StringUtils.rightPad(string, size));
    }

    @Override
    public RightPad copy() {
        return new RightPad(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.rightPad.name();
    }
}
