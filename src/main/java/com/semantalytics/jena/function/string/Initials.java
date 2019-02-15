package com.semantalytics.jena.function.string;

import com.google.common.collect.Range;
import org.apache.commons.text.WordUtils;
import org.apache.jena.sparql.function.FunctionBase;

public final class Initials extends FunctionBase {

        super(Range.closed(1, 2), StringVocabulary.initials.stringNodeValue());

    private Initials(final Initials initials) {
        super(initials);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {


        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3, got "+args.size()) ;


        final String string = assertStringLiteral(values[0]).stringNodeValue();

        switch (values.length) {
            case 1:
                return literal(WordUtils.initials(string));
            case 2: {
                final String delimiters = assertStringLiteral(values[1]).stringNodeValue();
                return literal(WordUtils.initials(string, delimiters.toCharArray()));
            }
            default:
                throw new ExpressionEvaluationException("Incorrect number of parameters. Valid values are 1 or 2. Found " + values.length);
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
