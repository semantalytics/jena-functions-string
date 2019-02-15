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

import static com.google.common.base.Joiner.*;
import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Split extends FunctionBase {

    public static final String name = StringVocabulary.split.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(1, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted between 1 and 3, got " + args.size()) ;

        String string = args.get(0).asString();

        switch (args.size()) {
            case 1: {
                return makeString(on("\u001f").join(split(string)));
            }
            case 2: {
                final String separator = args.get(1).asString();
                return makeString(on("\u001f").join(split(string, separator)));
            }
            case 3: {
                final String separator = args.get(1).asString();
                final int max = args.get(2).getInteger().intValue();
                return makeString(on("\u001f").join(split(string, separator, max)));
            }
            default: {
                throw new ExprEvalException("Takes 1 to 3 arguments. Found " + args.size());
            }
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(1, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes one or three arguments") ;
        }
    }
}
