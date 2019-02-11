package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class JoinArrayWith extends AbstractFunction implements StringFunction {

    protected JoinArrayWith() {
        super(2, StringVocabulary.joinArrayWith.stringValue());
    }

    private JoinArrayWith(final JoinArrayWith join) {
        super(join);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {


        final String separator = assertStringLiteral(values[0]).stringValue();
        final String[] pieces = assertStringLiteral(values[1]).stringValue().split("\u001f");

        return literal(StringUtils.joinWith(separator, pieces));
    }

    @Override
    public JoinArrayWith copy() {
        return new JoinArrayWith(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.joinArrayWith.name();
    }
}
