package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Strings;
import org.openrdf.model.Value;

public final class PadStart extends AbstractFunction implements StringFunction {

    protected PadStart() {
        super(3, StringVocabulary.padStart.stringValue());
    }

    private PadStart(final PadStart padStart) {
        super(padStart);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int minLength = assertIntegerLiteral(values[1]).integerValue().intValue();
      if(values[2].stringValue().length() != 1) {
          throw new ExpressionEvaluationException("Third argument must be a single char");
      }
      final char padChar = assertStringLiteral(values[2]).stringValue().charAt(0);
      
      return Values.literal(Strings.padStart(string, minLength, padChar));
    }

    @Override
    public PadStart copy() {
        return new PadStart(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.padStart.name();
    }
}
