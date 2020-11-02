package by.gradomski.game.state.impl;

import by.gradomski.game.entity.AnimationWrap;
import by.gradomski.game.entity.Hero;
import by.gradomski.game.graphic.AnimationManager;
import by.gradomski.game.graphic.AnimationNames;
import by.gradomski.game.graphic.AnimationPool;
import by.gradomski.game.state.State;

public class WakeUpState implements State {
    private AnimationPool pool = AnimationPool.getInstance();
    private Hero hero;
    private State standState = new StandState();

    public void setHero(Hero hero){
        this.hero = hero;
    }

    @Override
    public void startState(double coordinateX, double coordinateY) {
        AnimationWrap wakeUpWrap = pool.get(AnimationNames.WAKE_UP, AnimationNames.WAKE_UP_XML_PATH);
        wakeUpWrap.setCoordinateX((float) hero.getCurrentX());
        wakeUpWrap.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(wakeUpWrap);
    }

    @Override
    public void endState() {
        hero.changeState(standState);
        hero.startState(hero.getCurrentX(), hero.getCurrentY());
    }
}
