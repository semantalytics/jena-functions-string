package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public class IndexOfIgnoreCase extends FunctionBase2 {

    public static final String name = StringVocabulary.indexOfIgnoreCase.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        final String string = Argument.isString(arg0);
        final String searchChars = Argument.isString(arg0);

        return makeInteger(indexOfIgnoreCase(string, searchChars));
    }
}
