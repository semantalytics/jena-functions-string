package com.semantalytics.jena.function.string;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

public final class AbbreviateWithMarker extends FunctionBase {

    public static final String name = StringVocabulary.abbreviateWithMarker.stringValue();

    @Override
    public NodeValue exec(List<NodeValue> args)  {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this)+": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 3 or 4, got " + args.size()) ;

      final String string = args.get(0).getString();
      final String abbrevMarker = args.get(1).getString();
      final int maxWidth = args.get(2).getInteger().intValue();

      if(maxWidth <= 3) {
          throw new ExprEvalException("maxWidth must be greater than 3. Found " + maxWidth);
      }

      switch(args.size()) {
        case 3:
            return NodeValue.makeString(StringUtils.abbreviate(string, abbrevMarker, maxWidth));
        case 4:
            final int offset = args.get(3).getInteger().intValue();
            return NodeValue.makeString(StringUtils.abbreviate(string, abbrevMarker, offset, maxWidth));
        default:
            throw new ExprEvalException("function takes 3 or 4 arguments. Found " + args.size());
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(3, 4).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
