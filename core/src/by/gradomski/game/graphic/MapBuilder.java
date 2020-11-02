package by.gradomski.game.graphic;

import by.gradomski.game.entity.MapParameter;
import by.gradomski.game.entity.TileParameter;
import by.gradomski.game.environment.TreeCreator;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

import java.util.Iterator;
import java.util.Set;

public class MapBuilder {
    private static Texture mapTexture;
    private static MapParameter mapParameter;

    public static TiledMapTileLayer buildMapLayer(MapParameter mapParameter, Set<TileParameter> tileParameters){
        MapBuilder.mapParameter = mapParameter;
        TiledMapTileLayer mapLayer = new TiledMapTileLayer(mapParameter.getTileMapWidth(),
                mapParameter.getTileMapHeight(), mapParameter.getTileWidth(), mapParameter.getTileHeight());
        mapLayer.setOffsetX(mapParameter.getOffsetX());
        mapLayer.setOffsetY(mapParameter.getOffsetY());

        FileHandle fileHandle = new FileHandle(mapParameter.getImagePath());
        mapTexture = new Texture(fileHandle);
        TextureRegion textureRegion;
        Iterator<TileParameter> iterator = tileParameters.iterator();
        while (iterator.hasNext()){
            TileParameter tileParameter = iterator.next();
            int index = tileParameter.getIndex();

            int[] coordinates = calculateTileCoordinates(index, tileParameter);
            textureRegion = new TextureRegion(mapTexture, coordinates[0], coordinates[1], tileParameter.getWidth(),
                    tileParameter.getHeight());
            TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
            cell.setTile(new StaticTiledMapTile(textureRegion));
            if (tileParameter.isFlipHorizontal()){
                cell.setFlipHorizontally(true);
            }
            if (tileParameter.isFlipVertical()){
                cell.setFlipVertically(true);
            }

            int xIndex = (tileParameter.getCoordinateX() - mapParameter.getOffsetX()) / tileParameter.getWidth();
            int yIndex = Math.abs(tileParameter.getCoordinateY() - (864)) / tileParameter.getHeight();
            mapLayer.setCell(xIndex, yIndex, cell);

            TreeCreator.placeTree(index, tileParameter.getCoordinateX(), Math.abs(tileParameter.getCoordinateY() - 864) - mapParameter.getOffsetY());
        }
        return mapLayer;
    }

    public static void disposeMapTexture(){
        mapTexture.dispose();
    }

    private static int[] calculateTileCoordinates(int index, TileParameter tileParameter){
        int positionInRow = index % mapParameter.getTilesInRow();
        int xCoordinate = positionInRow == 0
                ? (tileParameter.getWidth() * (mapParameter.getTilesInRow())) - tileParameter.getWidth()
                : (tileParameter.getWidth() * positionInRow) - tileParameter.getWidth();
        int xBorderOffset = 2 * mapParameter.getTileBorderSize() * (xCoordinate / tileParameter.getWidth());
        xCoordinate += xBorderOffset;

        int yCoordinate = index % mapParameter.getTilesInColumn() == 0
                ? (tileParameter.getHeight() * (index / mapParameter.getTilesInColumn())) - tileParameter.getHeight()
                : (tileParameter.getHeight() * ((index / mapParameter.getTilesInColumn()) + 1)) - tileParameter.getHeight();
        int yBorderOffset = 2 * mapParameter.getTileBorderSize() * (yCoordinate / tileParameter.getHeight());
        yCoordinate += yBorderOffset;

        return new int[]{xCoordinate, yCoordinate};
    }
}
