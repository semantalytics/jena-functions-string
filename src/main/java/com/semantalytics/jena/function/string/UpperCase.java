package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public class UpperCase extends FunctionBase1 {

    public static final String name = StringVocabulary.upperCase.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0) {

        if (arg0.isLiteral()) {
            if (arg0.isLangString()) {
                return makeLangString(upperCase(arg0.asString()), arg0.getLang());
            } else {
                return makeString(upperCase(arg0.asString()));
            }
        } else {
            throw new ExprEvalException("Invalid argument. argument MUST be a literal value");
        }
    }
}
