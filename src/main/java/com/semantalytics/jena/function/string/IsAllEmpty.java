package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isAllEmpty;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class IsAllEmpty extends FunctionBase {

    public static final String name = StringVocabulary.isAllEmpty.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> arg0) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3, got "+args.size()) ;


        return makeBoolean(isAllEmpty(arg0.stream().map(NodeValue::toString).toArray(CharSequence[]::new)));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
