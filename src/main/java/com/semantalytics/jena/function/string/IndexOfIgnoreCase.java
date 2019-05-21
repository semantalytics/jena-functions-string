package com.semantalytics.jena.function.string;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public class IndexOfIgnoreCase extends FunctionBase2 {

    public static final String name = StringVocabulary.indexOfIgnoreCase.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        if(!arg0.isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");

        if(!arg1.isString())
            throw new ExprEvalException(Lib.className(this) + " second argument must be a string literal");

        final String string = arg0.asString();
        final String searchChars = arg1.asString();

        return makeInteger(indexOfIgnoreCase(string, searchChars));
    }
}
