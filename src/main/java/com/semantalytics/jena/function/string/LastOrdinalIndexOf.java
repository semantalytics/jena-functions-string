package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class LastOrdinalIndexOf extends FunctionBase3 {

    public static final String name = StringVocabulary.lastOrdinalIndexOf.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String string = arg0.asString();
        final String searchString = arg1.asString();
        final int ordinal = arg2.getInteger().intValue();

        return makeInteger(lastOrdinalIndexOf(string, searchString, ordinal));
    }
}
