package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class OrdinalIndexOf extends FunctionBase3 {

    public static final String name = StringVocabulary.ordinalIndexOf.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String string = arg0.asString();
        final String searchStr = arg1.asString();
        final int ordinal = arg2.getInteger().intValue();

        return makeInteger(ordinalIndexOf(string, searchStr, ordinal));
    }
}
