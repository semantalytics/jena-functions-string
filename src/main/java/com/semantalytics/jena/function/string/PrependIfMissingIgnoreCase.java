package com.semantalytics.jena.function.string;

import com.google.common.collect.Range;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class PrependIfMissingIgnoreCase extends FunctionBase {

    public static final String name = StringVocabulary.prependIfMissingIgnoreCase.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.atLeast(2).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted at least 2, got "+args.size()) ;

        final String string = args.get(0).asString();
        final String suffix = args.get(1).asString();
        final String[] suffixes = args.stream().skip(2).map(NodeValue::asString).toArray(String[]::new);

        return makeString(prependIfMissingIgnoreCase(string, suffix, suffixes));
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3, got "+args.size()) ;



        for (final Value value : values) {
            assertStringLiteral(value);
>>>>>>> 334f73488a31de347ef55eba29cfc83d66898c77
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
