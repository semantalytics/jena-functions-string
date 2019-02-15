package com.semantalytics.jena.function.string;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

public class IndexOf extends FunctionBase {

    protected IndexOf() {
        super(Range.closed(2, 3), StringVocabulary.indexOf.stringNodeValue());
    }

    private IndexOf(final IndexOf indexOf) {
        super(indexOf);
    }

    @Override
    public NodeValue exec(final NodeValue... values) throws ExpressionEvaluationException {


        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3, got "+args.size()) ;


        final String sequence = assertStringLiteral(values[0]).stringNodeValue();
        final String searchSequence = assertStringLiteral(values[1]).stringNodeValue();

        switch(values.length) {
            case 2: {
                return literal(StringUtils.indexOf(sequence, searchSequence));
            }
            case 3: {
                final int startPosition = assertNumericLiteral(values[2]).intNodeValue();

                return literal(StringUtils.indexOf(sequence, searchSequence, startPosition));
            }
            default: {
                throw new ExpressionEvaluationException("Expected 2 or 3 args. Found " + values.length);
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
