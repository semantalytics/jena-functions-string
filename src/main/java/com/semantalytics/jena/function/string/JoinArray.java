
package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class JoinArray extends AbstractFunction implements StringFunction {

    protected JoinArray() {
        super(1, StringVocabulary.joinArray.stringValue());
    }

    private JoinArray(final JoinArray join) {
        super(join);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String[] pieces = assertStringLiteral(values[0]).stringValue().split("\u001f");

        return literal(StringUtils.join(pieces));
    }

    @Override
    public JoinArray copy() {
        return new JoinArray(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.joinArray.name();
    }
}
