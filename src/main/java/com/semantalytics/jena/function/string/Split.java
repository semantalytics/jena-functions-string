package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Joiner;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Split extends FunctionBase {

    protected Split() {
        super(Range.closed(1, 3), StringVocabulary.split.stringValue());
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3, got "+args.size()) ;



        final String string = assertStringLiteral(values[0]).stringValue();

        switch (values.length) {
            case 1: {
                return literal(Joiner.on("\u001f").join(StringUtils.split(string)));
            }
            case 2: {
                final String separator = assertStringLiteral(values[1]).stringValue();
                return literal(Joiner.on("\u001f").join(StringUtils.split(string, separator)));
            }
            case 3: {
                final String separator = assertStringLiteral(values[1]).stringValue();
                final int max = assertIntegerLiteral(values[2]).intValue();
                return literal(Joiner.on("\u001f").join(StringUtils.split(string, separator, max)));
            }
            default: {
                throw new ExpressionEvaluationException("Takes 1 to 3 arguments. Fount " + values.length);
            }
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
