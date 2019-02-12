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

public final class AppendIfMissingIgnoreCase extends FunctionBase {

    public static final String name = StringVocabulary.appendIfMissingIgnoreCase.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.atLeast(2).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3, got "+args.size()) ;


      final String string = args.get(0).asString();
      final String suffix = args.get(1).asString();

      final String[] suffixes = args.stream().skip(2).toArray(String[]::new);

      return makeString(appendIfMissingIgnoreCase(string, suffix, suffixes));
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.atLeast(2).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
