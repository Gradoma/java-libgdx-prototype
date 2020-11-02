package by.gradomski.game.graphic;

import by.gradomski.game.entity.AnimationWrap;

import java.util.ArrayList;

public class AnimationManager {
    private static AnimationManager instance;
    private ArrayList<AnimationWrap> animationList = new ArrayList<>();

    private AnimationManager(){}

    public static AnimationManager getInstance(){
        if (instance == null){
            instance = new AnimationManager();
        }
        return instance;
    }

    public ArrayList<AnimationWrap> getAnimationList() {
        return new ArrayList<>(animationList);
    }

    public void startAnimation(AnimationWrap animationWrap){
        animationList.add(animationWrap);
    }

    public void endAnimation(AnimationWrap animationWrap){
        animationList.remove(animationWrap);
    }
}


