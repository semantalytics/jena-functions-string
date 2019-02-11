package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.stream.IntStream;

import static com.complexible.common.rdf.model.Values.literal;

public final class AppendIfMissing extends AbstractFunction implements StringFunction {

    protected AppendIfMissing() {
        super(Range.atLeast(2), StringVocabulary.appendIfMissing.stringValue());
    }

    private AppendIfMissing(final AppendIfMissing appendIfMissing) {
        super(appendIfMissing);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        switch(values.length) {
            case 2: {
                final String string = assertStringLiteral(values[0]).stringValue();
                final String suffix = assertStringLiteral(values[1]).stringValue();

                return literal(StringUtils.appendIfMissing(string, suffix));

            }
            case 3: {
                for(final Value value : values) {
                    assertIntegerLiteral(value);
                }
                final String string = values[0].stringValue();
                final String suffix = values[1].stringValue();
                final String[] suffixes = IntStream.range(2, values.length).mapToObj(i -> values[i].stringValue()).toArray(String[]::new);

                return literal(StringUtils.appendIfMissing(string, suffix, suffixes));

            }
            default: {
                throw new ExpressionEvaluationException("Should have at least 2 arguments. Found " + values.length);
            }
        }
    }
}
