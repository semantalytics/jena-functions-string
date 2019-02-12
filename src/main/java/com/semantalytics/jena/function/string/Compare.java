package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Compare extends FunctionBase2 {

    public static final String name = StringVocabulary.compare.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        final String string1 = arg0.asString();
        final String string2 = arg1.asString();

        return makeInteger(compare(string1, string2));
    }
}
