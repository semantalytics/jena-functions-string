package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Truncate extends AbstractFunction implements StringFunction {

    protected Truncate() {
        super(2, StringVocabulary.truncate.stringValue());
    }

    private Truncate(final Truncate truncate) {
        super(truncate);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int maxWidth = assertIntegerLiteral(values[1]).intValue();
      
      return Values.literal(StringUtils.truncate(string, maxWidth));
    }

    @Override
    public Truncate copy() {
        return new Truncate(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.truncate.name();
    }
}
