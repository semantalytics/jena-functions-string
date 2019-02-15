package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class IsMixedCase extends FunctionBase1 {

    public static final String name = StringVocabulary.isMixedCase.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0) {
        return makeBoolean(isMixedCase(arg0.asString()));
    }
}
