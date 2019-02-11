package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.text.WordUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Initials extends AbstractFunction implements StringFunction {

    protected Initials() {
        super(Range.closed(1,2), StringVocabulary.initials.stringValue());
    }

    private Initials(final Initials initials) {
        super(initials);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();

      switch(values.length) {
          case 1:
              return literal(WordUtils.initials(string));
          case 2: {
              final String delimiters = assertStringLiteral(values[1]).stringValue();
              return literal(WordUtils.initials(string, delimiters.toCharArray()));
          }
          default:
              throw new ExpressionEvaluationException("Incorrect number of parameters. Valid values are 1 or 2. Found " + values.length);
      }
    }

    @Override
    public Initials copy() {
        return new Initials(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.initials.name();
    }
}
