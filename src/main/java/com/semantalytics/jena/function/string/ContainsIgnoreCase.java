package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

import static com.complexible.common.rdf.model.Values.literal;

public final class ContainsIgnoreCase extends AbstractFunction implements StringFunction {

    protected ContainsIgnoreCase() {
        super(2, StringVocabulary.containsIgnoreCase.stringValue());
    }

    private ContainsIgnoreCase(final ContainsIgnoreCase containsIgnoreCase) {
        super(containsIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String sequence = assertStringLiteral(values[0]).stringValue();
        final String searchSequence = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.containsIgnoreCase(sequence, searchSequence));
    }
}
