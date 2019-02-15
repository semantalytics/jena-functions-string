package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class LastIndexOfIgnoreCase extends FunctionBase {

    protected LastIndexOfIgnoreCase() {
        super(Range.closed(2, 3), StringVocabulary.lastIndexOfIgnoreCase.stringNodeValue());
    }

    private LastIndexOfIgnoreCase(final LastIndexOfIgnoreCase lastIndexOfIgnoreCase) {
        super(lastIndexOfIgnoreCase);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3, got "+args.size()) ;



        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String searchString = assertStringLiteral(values[1]).stringNodeValue();

        switch (values.length) {
            case 2: {
                return literal(StringUtils.lastIndexOfIgnoreCase(string, searchString));
            }
            case 3: {
                final int startPos = assertNumericLiteral(values[2]).intNodeValue();
                return literal(StringUtils.lastIndexOfIgnoreCase(string, searchString, startPos));
            }
            default:
                throw new ExpressionEvaluationException("function takes two or three arguments. Found " + values.length);
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
