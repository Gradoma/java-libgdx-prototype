package by.gradomski.game.entity;

import by.gradomski.game.graphic.AnimationManager;
import by.gradomski.game.graphic.AnimationNames;
import by.gradomski.game.graphic.AnimationPool;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.*;

public class Palm{
    private static final AnimationPool pool = AnimationPool.getInstance();
    private Rectangle rectangle;
    private double coordinateX;
    private double coordinateY;
    private boolean isFelled;
    private AnimationWrap tree;
    private AnimationWrap shadow;
    private AnimationWrap stump;

    public Palm(String name){
        String treeXmlPath = null;
        String shadowXmlPath = null;
        String stumpXmlPath = null;
        switch (name){
            case AnimationNames.TROPIC_PALM:
               treeXmlPath =  AnimationNames.TROPIC_PALM_XML_PATH;
               shadowXmlPath = AnimationNames.TROPIC_PALM_SHADOW_XML_PATH;
               stumpXmlPath = AnimationNames.TROPIC_PALM_STUMP_XML_PATH;
               break;
            case AnimationNames.PALM_01:
                treeXmlPath =  AnimationNames.PALM_01_XML_PATH;
                shadowXmlPath = AnimationNames.PALM_01_SHADOW_XML_PATH;
                stumpXmlPath = AnimationNames.PALM_01_STUMP_XML_PATH;
                break;
        }
        rectangle = new Rectangle();

        tree = pool.create(treeXmlPath);
        TextureRegion[] frames = (TextureRegion[]) tree.getAnimation().getKeyFrames();
        tree.setCoordinateX(- frames[0].getRegionWidth() / 2);
        tree.setCoordinateY(0);
        rectangle.width = frames[0].getRegionWidth() / 3;
        rectangle.height = frames[0].getRegionHeight() / 2;

        shadow = pool.create(shadowXmlPath);
        TextureRegion[] framesShadow = (TextureRegion[]) shadow.getAnimation().getKeyFrames();
        shadow.setCoordinateX(- framesShadow[0].getRegionWidth() / 2);
        shadow.setCoordinateY(- framesShadow[0].getRegionHeight() / 2);

        stump = pool.create(stumpXmlPath);
        TextureRegion[] framesStump = (TextureRegion[]) stump.getAnimation().getKeyFrames();
        stump.setCoordinateX(- framesStump[0].getRegionWidth() / 2);
        stump.setCoordinateY(0);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangleWidth(int width) {
        rectangle.width = width;
    }

    public void setRectangleHeight(int height) {
        rectangle.height = height;
    }

    public double getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(double coordinateX) {
        this.coordinateX = coordinateX;
        rectangle.x = (int)coordinateX - rectangle.width / 2;
        tree.setCoordinateX(tree.getCoordinateX() + (float)coordinateX);
        shadow.setCoordinateX(shadow.getCoordinateX() + (float)coordinateX);
        stump.setCoordinateX(stump.getCoordinateX() + (float)coordinateX);
    }

    public double getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(double coordinateY) {
        this.coordinateY = coordinateY;
        tree.setCoordinateY(tree.getCoordinateY() + (float)coordinateY);
        rectangle.y = (int)tree.getCoordinateY();
        shadow.setCoordinateY(shadow.getCoordinateY() + (float)coordinateY);
        stump.setCoordinateY(stump.getCoordinateY() + (float)coordinateY);
    }

    public boolean isFelled() {
        return isFelled;
    }

    public void setFelled(boolean felled) {
        if(felled){
            AnimationManager animationManager = AnimationManager.getInstance();
            animationManager.endAnimation(tree);
            animationManager.endAnimation(shadow);
            animationManager.startAnimation(stump);
        }
        isFelled = felled;
    }

    public AnimationWrap getTree() {
        return tree;
    }

    public void setTree(AnimationWrap tree) {
        this.tree = tree;
    }

    public AnimationWrap getShadow() {
        return shadow;
    }

    public void setShadow(AnimationWrap shadow) {
        this.shadow = shadow;
    }

    public AnimationWrap getStump() {
        return stump;
    }

    public void setStump(AnimationWrap stump) {
        this.stump = stump;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Palm palm = (Palm) o;

        if (Double.compare(palm.coordinateX, coordinateX) != 0) return false;
        if (Double.compare(palm.coordinateY, coordinateY) != 0) return false;
        if (isFelled != palm.isFelled) return false;
        if (rectangle != null ? !rectangle.equals(palm.rectangle) : palm.rectangle != null) return false;
        if (tree != null ? !tree.equals(palm.tree) : palm.tree != null) return false;
        if (shadow != null ? !shadow.equals(palm.shadow) : palm.shadow != null) return false;
        return stump != null ? stump.equals(palm.stump) : palm.stump == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = rectangle != null ? rectangle.hashCode() : 0;
        temp = Double.doubleToLongBits(coordinateX);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(coordinateY);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isFelled ? 1 : 0);
        result = 31 * result + (tree != null ? tree.hashCode() : 0);
        result = 31 * result + (shadow != null ? shadow.hashCode() : 0);
        result = 31 * result + (stump != null ? stump.hashCode() : 0);
        return result;
    }
}
