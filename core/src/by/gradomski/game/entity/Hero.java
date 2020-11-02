package by.gradomski.game.entity;

import by.gradomski.game.state.State;
import by.gradomski.game.state.impl.WakeUpState;

public class Hero {
    public static final double EPSILON = 3.0;
    private static final int PIXEL_CONST = 55;
    private State state;
    private boolean withWood;
    private double currentX;
    private double currentY;
    private double targetX;
    private double targetY;
    private double xToTotal;
    private double yToTotal;
    private SkinEnum skinEnum;

    public Hero(){
         state = new WakeUpState();
         state.setHero(this);
         skinEnum = SkinEnum.CLOTH;
    }

    public void changeState(State newState){
        state = newState;
        state.setHero(this);
    }

    public void startState(double x, double y){
        state.startState(x, y);
    }

    public void endState(){
        state.endState();
    }

    public boolean isWithWood() {
        return withWood;
    }

    public void setWithWood(boolean withWood) {
        this.withWood = withWood;
    }

    public double getCurrentX() {
        return currentX;
    }

    public void setCurrentX(double currentX) {
        this.currentX = currentX;
    }

    public double getCurrentY() {
        return currentY;
    }

    public void setCurrentY(double currentY) {
        this.currentY = currentY;
    }

    public double getTargetX() {
        return targetX;
    }

    public void setTargetX(double targetX) {
        this.targetX = targetX;
    }

    public double getTargetY() {
        return targetY;
    }

    public void setTargetY(double targetY) {
        this.targetY = targetY;
    }

    public SkinEnum getSkinEnum() {
        return skinEnum;
    }

    public void setSkinEnum(SkinEnum skinEnum) {
        this.skinEnum = skinEnum;
    }

    public void calculate(){
        double totalDistance = Math.hypot(Math.abs(targetX - currentX),
                Math.abs(targetY - currentY));
        xToTotal = Math.abs(targetX - currentX) / totalDistance;
        yToTotal = Math.abs(targetY - currentY) / totalDistance;
    }

    public void updateCurrentPosition(float deltaTime){
        double xDirection = 1;
        if(targetX < currentX){
            xDirection = -1;
        }
        currentX = currentX + xDirection * PIXEL_CONST * deltaTime * xToTotal;
        double yDirection = 1;
        if(targetY < currentY){
            yDirection = -1;
        }
        currentY = currentY + yDirection * PIXEL_CONST * deltaTime * yToTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hero hero = (Hero) o;

        if (withWood != hero.withWood) return false;
        if (Double.compare(hero.currentX, currentX) != 0) return false;
        if (Double.compare(hero.currentY, currentY) != 0) return false;
        if (Double.compare(hero.targetX, targetX) != 0) return false;
        if (Double.compare(hero.targetY, targetY) != 0) return false;
        if (Double.compare(hero.xToTotal, xToTotal) != 0) return false;
        if (Double.compare(hero.yToTotal, yToTotal) != 0) return false;
        if (state != null ? !state.equals(hero.state) : hero.state != null) return false;
        return skinEnum == hero.skinEnum;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = state != null ? state.hashCode() : 0;
        result = 31 * result + (withWood ? 1 : 0);
        temp = Double.doubleToLongBits(currentX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(currentY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(targetX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(targetY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(xToTotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yToTotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (skinEnum != null ? skinEnum.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "state=" + state +
                ", withWood=" + withWood +
                ", currentX=" + currentX +
                ", currentY=" + currentY +
                ", targetX=" + targetX +
                ", targetY=" + targetY +
                ", xToTotal=" + xToTotal +
                ", yToTotal=" + yToTotal +
                ", skinEnum=" + skinEnum +
                '}';
    }
}
