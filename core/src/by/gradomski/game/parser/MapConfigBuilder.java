package by.gradomski.game.parser;

import by.gradomski.game.entity.MapParameter;
import by.gradomski.game.entity.TileParameter;
import by.gradomski.game.exception.BuilderException;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.Set;

public class MapConfigBuilder {
    private MapParameter mapParameter;
    private Set<TileParameter> tileParameters;

    public MapParameter getMapParameter(){
        return mapParameter;
    }

    public Set<TileParameter> getTileParameters(){
        return tileParameters;
    }

    public void buildMapConfig(String fileName) throws BuilderException {
        TileHandler handler = new TileHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;
        try {
            parser = factory.newSAXParser();
            parser.parse(fileName, handler);
            mapParameter = handler.getMapParameter();
            tileParameters = handler.getTiles();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new BuilderException(e);
        }
    }
}
