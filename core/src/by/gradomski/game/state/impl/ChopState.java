package by.gradomski.game.state.impl;

import by.gradomski.game.entity.AnimationWrap;
import by.gradomski.game.entity.Hero;
import by.gradomski.game.graphic.AnimationManager;
import by.gradomski.game.graphic.AnimationNames;
import by.gradomski.game.graphic.AnimationPool;
import by.gradomski.game.state.State;

public class ChopState implements State {
    private static final AnimationPool pool = AnimationPool.getInstance();
    private State walkState;
    private Hero hero;
    private double returnToX;
    private double returnToY;

    public ChopState(State walkState){
        this.walkState = walkState;
    }

    @Override
    public void startState(double returnToX, double returnToY) {
        this.returnToX = returnToX;
        this.returnToY = returnToY;
        AnimationWrap woodcutAnimation = pool.get(AnimationNames.WOODCUT, AnimationNames.WOODCUT_XML_PATH);
        woodcutAnimation.setCoordinateX((float) hero.getCurrentX());
        woodcutAnimation.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(woodcutAnimation);

        String clothName = null;
        String clothXmlPath = null;
        String hatName = null;
        String hatXmlPath = null;
        switch (hero.getSkinEnum()){
            case CLOTH:
                clothName = AnimationNames.CLOTH_WOODCUT;
                clothXmlPath = AnimationNames.CLOTH_WOODCUT_XML_PATH;
                hatName = AnimationNames.HAT_WOODCUT;
                hatXmlPath = AnimationNames.HAT_WOODCUT_XML_PATH;
                break;
            case DOUBLE_CLOTH:
                clothName = AnimationNames.D_CLOTH_WOODCUT;
                clothXmlPath = AnimationNames.D_CLOTH_WOODCUT_XML_PATH;
                hatName = AnimationNames.D_HAT_WOODCUT;
                hatXmlPath = AnimationNames.D_HAT_WOODCUT_XML_PATH;
                break;
        }
        AnimationWrap clothWrap = pool.get(clothName, clothXmlPath);
        clothWrap.setCoordinateX((float) hero.getCurrentX());
        clothWrap.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(clothWrap);

        AnimationWrap hatWrap = pool.get(hatName, hatXmlPath);
        hatWrap.setCoordinateX((float) hero.getCurrentX());
        hatWrap.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(hatWrap);
    }

    @Override
    public void endState() {
        hero.setWithWood(true);
        hero.changeState(walkState);
        hero.startState(returnToX, returnToY);
    }

    @Override
    public void setHero(Hero hero) {
        this.hero = hero;
    }
}
