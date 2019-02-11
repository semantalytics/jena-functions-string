package com.semantalytics.jena.function.string;

import com.google.common.base.Joiner;
import com.google.common.collect.Range;

import java.util.Arrays;

import static com.complexible.common.rdf.model.Values.literal;

public final class IndexOfArray extends AbstractFunction implements StringFunction {

    protected IndexOfArray() {
        super(Range.closed(2, 3), StringVocabulary.arrayIndex.stringValue());
    }

    private IndexOfArray(final IndexOfArray array) {
        super(array);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {


        final String[] stringArray = assertLiteral(values[0]).stringValue().split("\u001f");

        switch(values.length) {
            case 2: {
                final int index = assertIntegerLiteral(values[1]).intValue();

                if(index >= stringArray.length) {
                    throw new ExpressionEvaluationException("Index " + index + " out of bound. ArrayFunction length " + stringArray.length);
                }

                return literal(stringArray[index]);

            }
            case 3: {

                final int startIndex = assertIntegerLiteral(values[1]).intValue();
                final int endIndex = assertIntegerLiteral(values[2]).intValue();

                if(startIndex >= stringArray.length) {
                    throw new ExpressionEvaluationException("Index " + startIndex + " out of bound. ArrayFunction length " + stringArray.length);
                }
                if(endIndex >= stringArray.length) {
                    throw new ExpressionEvaluationException("Index " + endIndex + " out of bound. ArrayFunction length " + stringArray.length);
                }
                if(endIndex < startIndex) {
                    throw new ExpressionEvaluationException("Start index must be smaller or equal to end index");
                }

                return literal(Joiner.on("\u001f").join(Arrays.copyOfRange(stringArray, startIndex, endIndex)));
            }
            default: {
                throw new ExpressionEvaluationException("Function takes either 2 or three arguments. Found " + values.length);
            }

        }
    }

    @Override
    public IndexOfArray copy() {
        return new IndexOfArray(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.arrayIndex.name();
    }
}
