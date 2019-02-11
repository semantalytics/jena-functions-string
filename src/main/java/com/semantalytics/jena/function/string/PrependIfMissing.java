package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;

public final class PrependIfMissing extends AbstractFunction implements StringFunction {

    protected PrependIfMissing() {
        super(Range.atLeast(2), StringVocabulary.prependIfMissing.stringValue());
    }

    private PrependIfMissing(final PrependIfMissing prependIfMissing) {
        super(prependIfMissing);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

      for(final Value value : values) {
          assertStringLiteral(value);
      }

      final String string = assertStringLiteral(values[0]).stringValue();
      final String prefix = assertStringLiteral(values[1]).stringValue();
      final String[] prefixes = Arrays.stream(values).skip(2).map(Value::stringValue).toArray(String[]::new);

      return Values.literal(StringUtils.prependIfMissing(string, prefix, prefixes));
    }

    @Override
    public PrependIfMissing copy() {
        return new PrependIfMissing(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.prependIfMissing.name();
    }
}
