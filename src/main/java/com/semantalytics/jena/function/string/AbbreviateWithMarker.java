package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class AbbreviateWithMarker extends AbstractFunction implements StringFunction {

    protected AbbreviateWithMarker() {
        super(Range.closed(3, 4), StringVocabulary.abbreviateWithMarker.stringValue());
    }

    private AbbreviateWithMarker(final AbbreviateWithMarker abbreviateWithMarker) {
        super(abbreviateWithMarker);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String abbrevMarker = assertStringLiteral(values[1]).stringValue();
      final int maxWidth = assertIntegerLiteral(values[2]).intValue();

      if(maxWidth <= 3) {
          throw new ExpressionEvaluationException("maxWidth must be greater than 3. Found " + maxWidth);
      }

      switch(values.length) {
        case 3:
            return literal(StringUtils.abbreviate(string, abbrevMarker, maxWidth));
        case 4:
            final int offset = assertIntegerLiteral(values[3]).intValue();
            return literal(StringUtils.abbreviate(string, abbrevMarker, offset, maxWidth));
        default:
            throw new ExpressionEvaluationException("function takes 3 or 4 arguments. Found " + values.length);
        }
    }
}
