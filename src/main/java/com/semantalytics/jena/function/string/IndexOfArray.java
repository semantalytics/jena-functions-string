package com.semantalytics.jena.function.string;

import com.google.common.base.Joiner;
import com.google.common.collect.Range;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.Arrays;

public final class IndexOfArray extends FunctionBase {

    protected IndexOfArray() {
        super(Range.closed(2, 3), StringVocabulary.arrayIndex.stringNodeValue());
    }

    private IndexOfArray(final IndexOfArray array) {
        super(array);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {


        final String[] stringArray = assertLiteral(values[0]).stringNodeValue().split("\u001f");

        switch (values.length) {
            case 2: {
                final int index = assertIntegerLiteral(values[1]).intNodeValue();

                if (index >= stringArray.length) {
                    throw new ExpressionEvaluationException("Index " + index + " out of bound. ArrayFunction length " + stringArray.length);
                }

                return literal(stringArray[index]);

            }
            case 3: {

                final int startIndex = assertIntegerLiteral(values[1]).intNodeValue();
                final int endIndex = assertIntegerLiteral(values[2]).intNodeValue();

                if (startIndex >= stringArray.length) {
                    throw new ExpressionEvaluationException("Index " + startIndex + " out of bound. ArrayFunction length " + stringArray.length);
                }
                if (endIndex >= stringArray.length) {
                    throw new ExpressionEvaluationException("Index " + endIndex + " out of bound. ArrayFunction length " + stringArray.length);
                }
                if (endIndex < startIndex) {
                    throw new ExpressionEvaluationException("Start index must be smaller or equal to end index");
                }

                return literal(Joiner.on("\u001f").join(Arrays.copyOfRange(stringArray, startIndex, endIndex)));
            }
            default: {
                throw new ExpressionEvaluationException("Function takes either 2 or three arguments. Found " + values.length);
            }

        }
    }
}
