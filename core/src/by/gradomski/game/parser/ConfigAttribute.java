package by.gradomski.game.parser;

import by.gradomski.game.exception.IncorrectConfigTypeException;

import java.util.Arrays;
import java.util.Optional;

public enum ConfigAttribute {
    TILE_MAP ("tileMap"),
    DEF_SCALE_ATTR ("defaultScale"),
    IMAGE_ATTR("image"),
    MAX_SCALE_ATTR ("maxScale"),
    MIN_SCALE_ATTR ("minScale"),
    BORDER_SIZE_ATTR ("tileBorderSize"),
    TILE_HEIGHT("tileHeight"),
    TILE_WIDTH ("tileWidth"),
    MAP_HEIGHT_ATTR ("tileMapHeight"),
    MAP_WIDTH_ATTR ("tileMapWidth"),
    TILES_COLUMN_ATTR ("tilesPerAtlasColumn"),
    TILES_ROW_ATTR ("tilesPerAtlasRow"),
    TILE ("tile"),
    FLIP_HOR("flipHorizontal"),
    FLIP_VERT("flipVertical"),
    INDEX("index"),
    HEIGHT("height"),
    WIDTH("width"),
    X_COORDINATE("x"),
    Y_COORDINATE("y"),
    POINT("point");
    private String value;

    ConfigAttribute(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }

    public static ConfigAttribute getByValue(final String value) throws IncorrectConfigTypeException{
        ConfigAttribute[] configAttributes = ConfigAttribute.values();
        Optional<ConfigAttribute> optionalConfigAttribute = Arrays.stream(configAttributes)
                .filter(c -> c.getValue().equals(value))
                .findFirst();
        if (optionalConfigAttribute.isEmpty()){
            throw new IncorrectConfigTypeException(value + " type not present in ConfigAttribute enum");
        }
        return optionalConfigAttribute.get();
    }
}
