package by.gradomski.game.state.impl;

import by.gradomski.game.entity.AnimationWrap;
import by.gradomski.game.entity.Hero;
import by.gradomski.game.graphic.AnimationManager;
import by.gradomski.game.graphic.AnimationNames;
import by.gradomski.game.graphic.AnimationPool;
import by.gradomski.game.state.State;

public class StandState implements State {
    private AnimationPool pool = AnimationPool.getInstance();
    private State walkState = new WalkState(this);
    private AnimationWrap standWrap;
    private AnimationWrap clothWrap;
    private AnimationWrap hatWrap;
    private Hero hero;

    @Override
    public void startState(double coordinateX, double coordinateY) {
        standWrap = pool.get(AnimationNames.STAND, AnimationNames.STAND_XML_PATH);
        standWrap.setCoordinateX((float) hero.getCurrentX());
        standWrap.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(standWrap);

        String clothName = null;
        String clothXmlPath = null;
        String hatName = null;
        String hatXmlPath = null;
        switch (hero.getSkinEnum()){
            case CLOTH:
                clothName = AnimationNames.CLOTH_STAND;
                clothXmlPath = AnimationNames.CLOTH_STAND_XML_PATH;
                hatName = AnimationNames.HAT_STAND;
                hatXmlPath = AnimationNames.HAT_STAND_XML_PATH;
                break;
            case DOUBLE_CLOTH:
                clothName = AnimationNames.D_CLOTH_STAND;
                clothXmlPath = AnimationNames.D_CLOTH_STAND_XML_PATH;
                hatName = AnimationNames.D_HAT_STAND;
                hatXmlPath = AnimationNames.D_HAT_STAND_XML_PATH;
                break;
        }

        clothWrap = pool.get(clothName, clothXmlPath);
        clothWrap.setCoordinateX((float) hero.getCurrentX());
        clothWrap.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(clothWrap);

        hatWrap = pool.get(hatName, hatXmlPath);
        hatWrap.setCoordinateX((float) hero.getCurrentX());
        hatWrap.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(hatWrap);
    }

    @Override
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    @Override
    public void endState() {
        AnimationManager.getInstance().endAnimation(standWrap);
        AnimationManager.getInstance().endAnimation(clothWrap);
        AnimationManager.getInstance().endAnimation(hatWrap);
        hero.changeState(walkState);
    }
}
