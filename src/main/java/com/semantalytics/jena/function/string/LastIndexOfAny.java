package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import java.util.Arrays;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class LastIndexOfAny extends FunctionBase {

        super(Range.atLeast(2), StringVocabulary.lastIndexOfAny.stringNodeValue());

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

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String[] searchStrings = Arrays.stream(values).skip(1).map(NodeValue::stringNodeValue).toArray(String[]::new);

        return literal(StringUtils.lastIndexOfAny(string, searchStrings));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
