package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

public final class AbbreviateMiddle extends FunctionBase3 {

    public static final String name = StringVocabulary.abbreviateMiddle.stringValue();

    @Override
    public NodeValue exec(final NodeValue string, final NodeValue middle, final NodeValue length) {

      final String result = StringUtils.abbreviateMiddle(string.getString(),
                                                         middle.getString(),
                                                         length.getInteger().intValue());
      return NodeValue.makeString(result);
    }
}
