package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Strings;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class PadEnd extends AbstractFunction implements StringFunction {

    protected PadEnd() {
        super(3, StringVocabulary.padEnd.stringValue());
    }

    private PadEnd(final PadEnd padEnd) {
        super(padEnd);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int minLength = assertIntegerLiteral(values[1]).integerValue().intValue();
      final char padChar = assertStringLiteral(values[2]).stringValue().chars().mapToObj(i -> (char)i).findFirst().orElseThrow(() -> new ExpressionEvaluationException("Pad character not found"));

      return literal(Strings.padEnd(string, minLength, padChar));
    }

    @Override
    public PadEnd copy() {
        return new PadEnd(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.padEnd.name();
    }
}
