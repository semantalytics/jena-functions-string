package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import static org.apache.commons.lang3.StringUtils.deleteWhitespace;
import static org.apache.jena.sparql.expr.NodeValue.makeString;

public final class DeleteWhitespace extends FunctionBase1 {

    public static final String name = StringVocabulary.deleteWhitespace.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0) {

        return makeString(deleteWhitespace(arg0.asString()));
    }
}

