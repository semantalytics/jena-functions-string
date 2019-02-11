package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class AppendIfMissingIgnoreCase extends AbstractFunction implements StringFunction {

    protected AppendIfMissingIgnoreCase() {
        super(Range.atLeast(2), StringVocabulary.appendIfMissingIgnoreCase.stringValue());
    }

    private AppendIfMissingIgnoreCase(final AppendIfMissingIgnoreCase appendIfMissingIgnoreCase) {
        super(appendIfMissingIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String suffix = assertStringLiteral(values[1]).stringValue();

      final String[] suffixes = new String[values.length -2];

      for(int i = 2; i < values.length; i++) {
          suffixes[i - 2] = assertStringLiteral(values[i]).stringValue();
      }

      return literal(StringUtils.appendIfMissingIgnoreCase(string, suffix, suffixes));
    }
}
