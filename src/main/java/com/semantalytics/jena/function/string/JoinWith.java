package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import java.util.Arrays;

import com.google.common.collect.Range;

import static com.complexible.common.rdf.model.NodeValues.literal;

public final class JoinWith extends FunctionBase {

    protected JoinWith() {
        super(Range.atLeast(3), StringVocabulary.joinWith.stringNodeValue());
    }

    private JoinWith(final JoinWith join) {
        super(join);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3, got "+args.size()) ;



        for (final NodeValue value : values) {
            assertStringLiteral(value);
        }

        final String separator = assertStringLiteral(values[0]).stringNodeValue();
        final String[] pieces = Arrays.stream(values).skip(1).map(NodeValue::stringNodeValue).toArray(String[]::new);

        return literal(StringUtils.joinWith(separator, pieces));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
