package by.gradomski.game.entity;

import com.badlogic.gdx.graphics.g2d.Animation;

public class AnimationWrap {
    private String name;
    private Animation animation;
    private float stateTime = 0f;
    private boolean loop;
    private float coordinateX;
    private float coordinateY;

    public AnimationWrap(){}

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public float getStateTime(){
        return stateTime;
    }

    public void increaseTime(float delta){
        stateTime += delta;
    }

    public void resetTime(){
        stateTime = 0f;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(float coordinateX) {
        this.coordinateX = coordinateX;
    }

    public float getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(float coordinateY) {
        this.coordinateY = coordinateY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimationWrap that = (AnimationWrap) o;

        if (Float.compare(that.stateTime, stateTime) != 0) return false;
        if (loop != that.loop) return false;
        if (Float.compare(that.coordinateX, coordinateX) != 0) return false;
        if (Float.compare(that.coordinateY, coordinateY) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return animation != null ? animation.equals(that.animation) : that.animation == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (animation != null ? animation.hashCode() : 0);
        result = 31 * result + (stateTime != +0.0f ? Float.floatToIntBits(stateTime) : 0);
        result = 31 * result + (loop ? 1 : 0);
        result = 31 * result + (coordinateX != +0.0f ? Float.floatToIntBits(coordinateX) : 0);
        result = 31 * result + (coordinateY != +0.0f ? Float.floatToIntBits(coordinateY) : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "name=" + name +
                ", animation=" + animation +
                ", stateTime=" + stateTime +
                ", loop=" + loop +
                ", x=" + coordinateX +
                ", y=" + coordinateY +
                '}';
    }
}
