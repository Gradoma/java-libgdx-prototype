package by.gradomski.game.entity;

import java.util.ArrayList;

public class AnimationParameter {
    private String textureName;
    private String name;
    private ArrayList<Frame> frameList;

    public AnimationParameter(){}

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    public ArrayList<Frame> getFrameList() {
        return new ArrayList<>(frameList);
    }

    public void setFrameList(ArrayList<Frame> frameList) {
        this.frameList = frameList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimationParameter that = (AnimationParameter) o;

        if (textureName != null ? !textureName.equals(that.textureName) : that.textureName != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return frameList != null ? frameList.equals(that.frameList) : that.frameList == null;
    }

    @Override
    public int hashCode() {
        int result = textureName != null ? textureName.hashCode() : 0;
        result = 31 * result + name != null ? name.hashCode() : 0;
        result = 31 * result + (frameList != null ? frameList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + " {" +
                "name='" + name + '\'' +
                "textureName='" + textureName + '\'' +
                ", frameList=" + frameList +
                '}';
    }
}
