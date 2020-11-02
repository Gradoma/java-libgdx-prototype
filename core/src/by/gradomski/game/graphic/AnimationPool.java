package by.gradomski.game.graphic;

import by.gradomski.game.entity.AnimationParameter;
import by.gradomski.game.entity.AnimationWrap;
import by.gradomski.game.exception.BuilderException;
import by.gradomski.game.parser.FrameBuilder;

import java.util.HashMap;

public class AnimationPool {
    private static AnimationPool instance;
    private HashMap<String, AnimationWrap> pool = new HashMap<>();

    private AnimationPool(){}

    public static AnimationPool getInstance(){
        if(instance == null){
            instance = new AnimationPool();
        }
        return instance;
    }

    public AnimationWrap get(String animationName, String animationXmlPath){
        AnimationWrap animationWrap;
        if(poolContains(animationName)){
            animationWrap = pool.get(animationName);
        } else {
            animationWrap = create(animationXmlPath);
            put(animationWrap);
        }
        return animationWrap;
    }

    public AnimationWrap create(String animationXmlPath){
        AnimationParameter animationParameter = null;
        try {
            animationParameter = FrameBuilder.buildAnimationConfig(animationXmlPath);
        } catch (BuilderException e) {
            throw new RuntimeException(e);
        }
        return AnimationBuilder.buildAnimation(animationParameter);
    }

    private boolean poolContains(String animationName){
        return pool.containsKey(animationName);
    }

    private void put(AnimationWrap animationWrap){
        pool.put(animationWrap.getName(), animationWrap);
    }
}
