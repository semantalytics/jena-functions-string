package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static com.google.common.base.Strings.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class CommonSuffix extends FunctionBase2 {

    public static final String name = StringVocabulary.commonSuffix.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {
      
      final String firstString = arg0.asString();
      final String secondString = arg1.asString();

      return makeString(commonSuffix(firstString, secondString));
    }
}
