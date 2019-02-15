package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Mid extends FunctionBase3 {

    public static final String name = StringVocabulary.mid.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String string = arg0.asString();
        final int position = arg1.getInteger().intValue();
        final int length = arg2.getInteger().intValue();

        return makeString(mid(string, position, length));
    }
}
