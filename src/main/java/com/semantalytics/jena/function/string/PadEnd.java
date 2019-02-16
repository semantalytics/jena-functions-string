package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import static org.apache.jena.ext.com.google.common.base.Strings.padEnd;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class PadEnd extends FunctionBase3 {

    public static final String name = StringVocabulary.padEnd.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String string = arg0.asString();
        final int minLength = arg1.getInteger().intValue();
        final char padChar = arg2.asString().chars().mapToObj(i -> (char) i).findFirst().orElseThrow(() -> new ExprEvalException("Pad character not found"));

        return makeString(padEnd(string, minLength, padChar));
    }
}
