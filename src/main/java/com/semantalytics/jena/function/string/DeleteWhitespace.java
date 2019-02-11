package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class DeleteWhitespace extends AbstractFunction implements StringFunction {

    protected DeleteWhitespace() {
        super(1, StringVocabulary.deleteWhitespace.stringValue());
    }

    private DeleteWhitespace(final DeleteWhitespace deleteWhitespace) {
        super(deleteWhitespace);
    }
}

