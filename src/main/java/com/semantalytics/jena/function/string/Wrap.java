package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Wrap extends FunctionBase2 {

    public static final String name = StringVocabulary.wrap.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        final String string = arg0.asString();
        final String wrapWith = arg1.asString();

        if (wrapWith.length() != 1) {
            throw new ExprEvalException("Expecting a single character for second argument. Found '" + wrapWith + "'");
        }

        return makeString(wrap(string, wrapWith.charAt(0)));
    }
}
