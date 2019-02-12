package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class ContainsNone extends FunctionBase2 {

    public static final String name = StringVocabulary.containsNone.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {
      
      final String string = arg0.asString();
      final String invalidChars = arg1.asString();
      
      return makeBoolean(containsNone(string, invalidChars));
    }
}
