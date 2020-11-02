package by.gradomski.game.parser;

import by.gradomski.game.entity.MapParameter;
import by.gradomski.game.entity.TileParameter;
import by.gradomski.game.exception.IncorrectConfigTypeException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


import java.util.HashSet;
import java.util.Set;

import static by.gradomski.game.parser.ConfigAttribute.*;

public class TileHandler extends DefaultHandler {
    private MapParameter mapParameter;
    private Set<TileParameter> tiles;
    private TileParameter tileParameter;

    public TileHandler(){
        mapParameter = new MapParameter();
        tiles = new HashSet<TileParameter>();
    }

    public MapParameter getMapParameter(){
        return mapParameter;
    }

    public Set<TileParameter> getTiles(){
        return tiles;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs){
        if (TILE_MAP.getValue().equalsIgnoreCase(qName)){
            for(int i = 0; i < attrs.getLength(); i++){
                ConfigAttribute currentEnum;
                try{
                    currentEnum = ConfigAttribute.getByValue(attrs.getLocalName(i));
                } catch (IncorrectConfigTypeException e){
                    throw new RuntimeException(e);
                }
                switch (currentEnum){
                    case DEF_SCALE_ATTR :
                        mapParameter.setDefaultScale(Float.parseFloat(attrs.getValue(i)));
                        break;
                    case IMAGE_ATTR:
                        mapParameter.setImagePath(attrs.getValue(i));
                        break;
                    case MAX_SCALE_ATTR:
                        mapParameter.setMaxScale(Float.parseFloat(attrs.getValue(i)));
                        break;
                    case MIN_SCALE_ATTR:
                        mapParameter.setMinScale(Float.parseFloat(attrs.getValue(i)));
                        break;
                    case BORDER_SIZE_ATTR:
                        mapParameter.setTileBorderSize(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case MAP_HEIGHT_ATTR:
                        mapParameter.setTileMapHeight(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case MAP_WIDTH_ATTR:
                        mapParameter.setTileMapWidth(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case TILES_COLUMN_ATTR:
                        mapParameter.setTilesInColumn(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case TILES_ROW_ATTR:
                        mapParameter.setTilesInRow(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case TILE_WIDTH:
                        mapParameter.setTileWidth(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case TILE_HEIGHT:
                        mapParameter.setTileHeight(Integer.parseInt(attrs.getValue(i)));
                        break;
                }
            }
        } else if (TILE.getValue().equalsIgnoreCase(qName)) {
            tileParameter = new TileParameter();
            for(int i = 0; i < attrs.getLength(); i++){
                ConfigAttribute currentEnum;
                try{
                    currentEnum = ConfigAttribute.getByValue(attrs.getLocalName(i));
                } catch (IncorrectConfigTypeException e){
                    throw new RuntimeException(e);
                }
                switch (currentEnum){
                    case FLIP_HOR :
                        tileParameter.setFlipHorizontal(Boolean.parseBoolean(attrs.getValue(i)));
                        break;
                    case FLIP_VERT:
                        tileParameter.setFlipVertical(Boolean.parseBoolean(attrs.getValue(i)));
                        break;
                    case HEIGHT:
                        tileParameter.setHeight(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case WIDTH:
                        tileParameter.setWidth(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case INDEX:
                        tileParameter.setIndex(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case X_COORDINATE:
                        tileParameter.setCoordinateX(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case Y_COORDINATE:
                        tileParameter.setCoordinateY(Integer.parseInt(attrs.getValue(i)));
                        break;
                }
            }
        } else if(POINT.getValue().equalsIgnoreCase(qName)){
            for(int i = 0; i < attrs.getLength(); i++){
                ConfigAttribute currentEnum;
                try{
                    currentEnum = ConfigAttribute.getByValue(attrs.getLocalName(i));
                } catch (IncorrectConfigTypeException e){
                    throw new RuntimeException(e);
                }
                switch (currentEnum){
                    case X_COORDINATE:
                        mapParameter.setOffsetX(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case Y_COORDINATE:
                        mapParameter.setOffsetY(Integer.parseInt(attrs.getValue(i)));
                        break;
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (TILE.getValue().equalsIgnoreCase(qName)){
            tiles.add(tileParameter);
        }
    }
}
