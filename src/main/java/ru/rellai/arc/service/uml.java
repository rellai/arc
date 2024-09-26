package ru.rellai.arc.service;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class uml {


    public String generateImageSvg(String plantUML) throws IOException {

        SourceStringReader reader = new SourceStringReader(plantUML);
        //SFile file = new SFile("output.svg");
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        reader.outputImage(stream,  new FileFormatOption(FileFormat.SVG, true));
        String response = stream.toString(StandardCharsets.UTF_8);
        stream.close();
        return response;
    }

}
