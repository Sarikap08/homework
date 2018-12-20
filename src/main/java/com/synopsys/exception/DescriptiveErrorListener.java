package com.synopsys.exception;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.apache.log4j.Logger;

/**
 * Custom Error Listener
 */
public class DescriptiveErrorListener extends BaseErrorListener {

    Logger LOGGER = Logger.getLogger(DescriptiveErrorListener.class);

    public static DescriptiveErrorListener INSTANCE = new DescriptiveErrorListener();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {
       String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }
        LOGGER.error(sourceName+"line "+line+":"+charPositionInLine+" "+msg);
        throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
    }
}

