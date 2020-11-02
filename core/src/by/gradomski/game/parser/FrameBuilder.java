package by.gradomski.game.parser;

import by.gradomski.game.entity.AnimationParameter;
import by.gradomski.game.entity.Frame;
import by.gradomski.game.exception.BuilderException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

public class FrameBuilder {

    public static AnimationParameter buildAnimationConfig(String fileName) throws BuilderException {
        FrameHandler frameHandler = new FrameHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        AnimationParameter animationParameter;
        try {
            parser = factory.newSAXParser();
            parser.parse(fileName, frameHandler);
            animationParameter = frameHandler.getAnimationParameter();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new BuilderException(e);
        }
        return animationParameter;
    }
}
