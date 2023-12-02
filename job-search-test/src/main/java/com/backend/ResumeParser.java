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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public String extractEmail (String resumeText) {
        Pattern emailPattern = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(resumeText);

        if (matcher.find()) {
            return matcher.group();
        }

        return null;
    }

    public String extractName (String resumeText) {
        // this assumes that the first line of text in the resume is the persons name,so if it's not it won't work
        String firstLine = resumeText.split("\\r?\\n")[0];
        Pattern namePattern = Pattern.compile("^[a-zA-Z\\s.]+");
        Matcher matcher = namePattern.matcher(firstLine);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

}



