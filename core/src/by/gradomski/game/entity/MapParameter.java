package by.gradomski.game.entity;

public class MapParameter {
    private float defaultScale;
    private float maxScale;
    private float minScale;
    private int tileBorderSize;
    private int tileMapWidth;
    private int tileMapHeight;
    private int tileWidth;
    private int tileHeight;
    private String imagePath;
    private int tilesInColumn;
    private int tilesInRow;
    private int offsetX;
    private int offsetY;

    public MapParameter(){}

    public float getDefaultScale() {
        return defaultScale;
    }

    public void setDefaultScale(float defaultScale) {
        this.defaultScale = defaultScale;
    }

    public float getMaxScale() {
        return maxScale;
    }

    public void setMaxScale(float maxScale) {
        this.maxScale = maxScale;
    }

    public float getMinScale() {
        return minScale;
    }

    public void setMinScale(float minScale) {
        this.minScale = minScale;
    }

    public int getTileBorderSize() {
        return tileBorderSize;
    }

    public void setTileBorderSize(int tileBorderSize) {
        this.tileBorderSize = tileBorderSize;
    }

    public int getTileMapWidth() {
        return tileMapWidth;
    }

    public void setTileMapWidth(int tileMapWidth) {
        this.tileMapWidth = tileMapWidth;
    }

    public int getTileMapHeight() {
        return tileMapHeight;
    }

    public void setTileMapHeight(int tileMapHeight) {
        this.tileMapHeight = tileMapHeight;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getTilesInColumn() {
        return tilesInColumn;
    }

    public void setTilesInColumn(int tilesInColumn) {
        this.tilesInColumn = tilesInColumn;
    }

    public int getTilesInRow() {
        return tilesInRow;
    }

    public void setTilesInRow(int tilesInRow) {
        this.tilesInRow = tilesInRow;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapParameter that = (MapParameter) o;

        if (Float.compare(that.defaultScale, defaultScale) != 0) return false;
        if (Float.compare(that.maxScale, maxScale) != 0) return false;
        if (Float.compare(that.minScale, minScale) != 0) return false;
        if (tileBorderSize != that.tileBorderSize) return false;
        if (tileMapWidth != that.tileMapWidth) return false;
        if (tileMapHeight != that.tileMapHeight) return false;
        if (tileWidth != that.tileWidth) return false;
        if (tileHeight != that.tileHeight) return false;
        if (tilesInColumn != that.tilesInColumn) return false;
        if (tilesInRow != that.tilesInRow) return false;
        if (offsetX != that.offsetX) return false;
        if (offsetY != that.offsetY) return false;
        return imagePath != null ? imagePath.equals(that.imagePath) : that.imagePath == null;
    }

    @Override
    public int hashCode() {
        int result = (defaultScale != +0.0f ? Float.floatToIntBits(defaultScale) : 0);
        result = 31 * result + (maxScale != +0.0f ? Float.floatToIntBits(maxScale) : 0);
        result = 31 * result + (minScale != +0.0f ? Float.floatToIntBits(minScale) : 0);
        result = 31 * result + tileBorderSize;
        result = 31 * result + tileMapWidth;
        result = 31 * result + tileMapHeight;
        result = 31 * result + tileWidth;
        result = 31 * result + tileHeight;
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        result = 31 * result + tilesInColumn;
        result = 31 * result + tilesInRow;
        result = 31 * result + offsetX;
        result = 31 * result + offsetY;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "defaultScale=" + defaultScale +
                ", maxScale=" + maxScale +
                ", minScale=" + minScale +
                ", tileBorderSize=" + tileBorderSize +
                ", tileMapWidth=" + tileMapWidth +
                ", tileMapHeight=" + tileMapHeight +
                ", tileWidth=" + tileWidth +
                ", tileHeight=" + tileHeight +
                ", tilesInColumn=" + tilesInColumn +
                ", tilesInRow=" + tilesInRow +
                ", imagePath=" + imagePath +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY;
    }
}
