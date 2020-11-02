package by.gradomski.game.entity;

public class Frame {
    private int flippedXOffset;
    private int  flippedYOffset;
    private int height;
    private int width;
    private int ticks;
    private int coordinateX;
    private int coordinateY;
    private int offsetX;
    private int offsetY;

    public Frame(){}

    public int getFlippedXOffset() {
        return flippedXOffset;
    }

    public void setFlippedXOffset(int flippedXOffset) {
        this.flippedXOffset = flippedXOffset;
    }

    public int getFlippedYOffset() {
        return flippedYOffset;
    }

    public void setFlippedYOffset(int flippedYOffset) {
        this.flippedYOffset = flippedYOffset;
    }

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

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
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

        Frame frame = (Frame) o;

        if (flippedXOffset != frame.flippedXOffset) return false;
        if (flippedYOffset != frame.flippedYOffset) return false;
        if (height != frame.height) return false;
        if (width != frame.width) return false;
        if (ticks != frame.ticks) return false;
        if (coordinateX != frame.coordinateX) return false;
        if (coordinateY != frame.coordinateY) return false;
        if (offsetX != frame.offsetX) return false;
        return offsetY == frame.offsetY;
    }

    @Override
    public int hashCode() {
        int result = flippedXOffset;
        result = 31 * result + flippedYOffset;
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + ticks;
        result = 31 * result + coordinateX;
        result = 31 * result + coordinateY;
        result = 31 * result + offsetX;
        result = 31 * result + offsetY;
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + " {" +
                "flippedXOffset=" + flippedXOffset +
                ", flippedYOffset=" + flippedYOffset +
                ", height=" + height +
                ", width=" + width +
                ", ticks=" + ticks +
                ", coordinateX=" + coordinateX +
                ", coordinateY=" + coordinateY +
                ", offsetX=" + offsetX +
                ", offsetY=" + offsetY +
                '}';
    }
}
