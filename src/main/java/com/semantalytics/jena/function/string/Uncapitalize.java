package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Uncapitalize extends AbstractFunction implements StringFunction {

    protected Uncapitalize() {
        super(1, StringVocabulary.uncapitalize.stringValue());
    }

    private Uncapitalize(final Uncapitalize uncapitalize) {
        super(uncapitalize);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();

      return Values.literal(StringUtils.uncapitalize(string));
    }

    @Override
    public Uncapitalize copy() {
        return new Uncapitalize(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.uncapitalize.name();
    }
}
