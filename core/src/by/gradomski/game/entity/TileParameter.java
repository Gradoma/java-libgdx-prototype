package by.gradomski.game.entity;

public class TileParameter {
    private int height;
    private int width;
    private boolean flipHorizontal;
    private boolean flipVertical;
    private int index;
    private int coordinateX;
    private int coordinateY;

    public TileParameter(){}

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isFlipHorizontal() {
        return flipHorizontal;
    }

    public void setFlipHorizontal(boolean flipHorizontal) {
        this.flipHorizontal = flipHorizontal;
    }

    public boolean isFlipVertical() {
        return flipVertical;
    }

    public void setFlipVertical(boolean flipVertical) {
        this.flipVertical = flipVertical;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TileParameter tileParameter = (TileParameter) o;

        if (height != tileParameter.height) return false;
        if (width != tileParameter.width) return false;
        if (flipHorizontal != tileParameter.flipHorizontal) return false;
        if (flipVertical != tileParameter.flipVertical) return false;
        if (index != tileParameter.index) return false;
        if (coordinateX != tileParameter.coordinateX) return false;
        return coordinateY == tileParameter.coordinateY;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = height;
        result = prime * result + width;
        result = prime * result + (flipHorizontal ? 1 : 0);
        result = prime * result + (flipVertical ? 1 : 0);
        result = prime * result + index;
        result = prime * result + coordinateX;
        result = prime * result + coordinateY;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() +
                "height=" + height +
                ", width=" + width +
                ", flipHorizontal=" + flipHorizontal +
                ", flipVertical=" + flipVertical +
                ", index=" + index +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY;
    }
}