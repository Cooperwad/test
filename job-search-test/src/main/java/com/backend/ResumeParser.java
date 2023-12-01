package com.backend;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ResumeParser {

    public String parseResume(byte[] resumeData) throws IOException, TikaException, SAXException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        AutoDetectParser parser = new AutoDetectParser();

        try (InputStream stream = new ByteArrayInputStream(resumeData)) {
            parser.parse(stream, handler, metadata, new ParseContext());
        }

        return handler.toString();
    }
}



