package com.semantalytics.jena.function.string;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

public class IndexOf extends FunctionBase {

    protected IndexOf() {
        super(Range.closed(2, 3), StringVocabulary.indexOf.stringNodeValue());
    }

    private IndexOf(final IndexOf indexOf) {
        super(indexOf);
    }

    @Override
    public NodeValue exec(final NodeValue... values) throws ExpressionEvaluationException {

        final String sequence = assertStringLiteral(values[0]).stringNodeValue();
        final String searchSequence = assertStringLiteral(values[1]).stringNodeValue();

        switch(values.length) {
            case 2: {
                return literal(StringUtils.indexOf(sequence, searchSequence));
            }
            case 3: {
                final int startPosition = assertNumericLiteral(values[2]).intNodeValue();

                return literal(StringUtils.indexOf(sequence, searchSequence, startPosition));
            }
            default: {
                throw new ExpressionEvaluationException("Expected 2 or 3 args. Found " + values.length);
            }
        }
    }
}
