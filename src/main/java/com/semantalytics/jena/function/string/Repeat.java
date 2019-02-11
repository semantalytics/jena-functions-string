package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Strings;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Repeat extends AbstractFunction implements StringFunction {

    protected Repeat() {
        super(2, StringVocabulary.repeat.stringValue());
    }

    private Repeat(final Repeat repeat) {
        super(repeat);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int count = assertIntegerLiteral(values[1]).integerValue().intValue();

      return literal(Strings.repeat(string, count));
    }

    @Override
    public Repeat copy() {
        return new Repeat(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.repeat.name();
    }
}
