package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import static org.apache.jena.ext.com.google.common.base.Strings.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class PadStart extends FunctionBase3 {

    public static final String name = StringVocabulary.padStart.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String string = arg0.asString();
        final int minLength = arg0.getInteger().intValue();
        if (arg2.asString().length() != 1) {
            throw new ExprEvalException("Third argument must be a single char");
        }
        final char padChar = arg2.asString().charAt(0);

        return makeString(padStart(string, minLength, padChar));
    }
}
