package by.gradomski.game.state;

import by.gradomski.game.entity.Hero;

public interface State {
    void startState(double coordinateX, double coordinateY);
    void endState();
    void setHero(Hero hero);
}
