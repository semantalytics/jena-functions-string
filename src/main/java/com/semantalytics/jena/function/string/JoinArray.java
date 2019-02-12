
package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.literal;

public final class JoinArray extends FunctionBase {

    protected JoinArray() {
        super(1, StringVocabulary.joinArray.stringNodeValue());
    }

    private JoinArray(final JoinArray join) {
        super(join);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String[] pieces = assertStringLiteral(values[0]).stringNodeValue().split("\u001f");

        return literal(StringUtils.join(pieces));
    }
}
