package by.gradomski.game.state.impl;

import by.gradomski.game.entity.AnimationWrap;
import by.gradomski.game.entity.Hero;
import by.gradomski.game.environment.TreePool;
import by.gradomski.game.graphic.AnimationManager;
import by.gradomski.game.graphic.AnimationNames;
import by.gradomski.game.graphic.AnimationPool;
import by.gradomski.game.state.State;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class WalkState implements State {
    private static final AnimationPool pool = AnimationPool.getInstance();
    private static final TreePool treePool = TreePool.getInstance();
    private State standState;
    private State chopState = new ChopState(this);
    private Hero hero;
    private boolean walkToChop;
    private double startX;
    private double startY;
    private double clickX;
    private double clickY;
    private String walkName;
    private String walkXmlPath;
    private String clothName ;
    private String clothXmlPath ;
    private String hatName ;
    private String hatXmlPath;

    public WalkState(State standState){
        this.standState = standState;
    }

    @Override
    public void startState(double coordinateX, double coordinateY) {
        defineAnimationType(coordinateY);

        AnimationWrap walkWrap = pool.get(walkName, walkXmlPath);
        walkWrap.setLoop(true);
        TextureRegion[] frames = (TextureRegion[]) walkWrap.getAnimation().getKeyFrames();
        walkWrap.setCoordinateX((float) hero.getCurrentX());
        walkWrap.setCoordinateY((float) hero.getCurrentY());
        hero.setTargetX(coordinateX - frames[0].getRegionWidth()/2);
        hero.setTargetY(coordinateY - frames[0].getRegionHeight()/2);
        if(TreePool.getInstance().pointToTree(coordinateX, coordinateY)){
            walkToChop = true;
            startX = hero.getCurrentX();
            startY = hero.getCurrentY();
            clickX = coordinateX;
            clickY = coordinateY;
        }
        hero.calculate();
        AnimationManager.getInstance().startAnimation(walkWrap);

        AnimationWrap clothWrap = pool.get(clothName, clothXmlPath);
        clothWrap.setLoop(true);
        clothWrap.setCoordinateX((float) hero.getCurrentX());
        clothWrap.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(clothWrap);

        AnimationWrap hatWrap = pool.get(hatName, hatXmlPath);
        hatWrap.setLoop(true);
        hatWrap.setCoordinateX((float) hero.getCurrentX());
        hatWrap.setCoordinateY((float) hero.getCurrentY());
        AnimationManager.getInstance().startAnimation(hatWrap);
    }

    @Override
    public void endState() {
        hero.setWithWood(false);
        if(walkToChop){
            treePool.treeFelled(clickX, clickY);
            hero.changeState(chopState);
            walkToChop = false;
            hero.startState(startX, startY);
        } else {
            hero.changeState(standState);
            hero.startState(hero.getCurrentX(), hero.getCurrentY());
        }
    }

    @Override
    public void setHero(Hero hero) {
        this.hero = hero;
    }

    private void defineAnimationType(double coordinateY){
        if(coordinateY >= hero.getCurrentY()){
            walkName = AnimationNames.WALK_UP;
            walkXmlPath = AnimationNames.WALK_UP_XML_PATH;
            switch (hero.getSkinEnum()){
                case CLOTH:
                    if(hero.isWithWood()){
                        clothName = AnimationNames.CLOTH_WALKWOOD_UP;
                        clothXmlPath = AnimationNames.CLOTH_WALKWOOD_UP_XML_PATH;
                        hatName = AnimationNames.HAT_WALKWOOD_UP;
                        hatXmlPath = AnimationNames.HAT_WALKWOOD_UP_XML_PATH;
                    } else {
                        clothName = AnimationNames.CLOTH_WALK_UP;
                        clothXmlPath = AnimationNames.CLOTH_WALK_UP_XML_PATH;
                        hatName = AnimationNames.HAT_WALK_UP;
                        hatXmlPath = AnimationNames.HAT_WALK_UP_XML_PATH;
                    }
                    break;
                case DOUBLE_CLOTH:
                    if(hero.isWithWood()){
                        clothName = AnimationNames.D_CLOTH_WALKWOOD_UP;
                        clothXmlPath = AnimationNames.D_CLOTH_WALKWOOD_UP_XML_PATH;
                        hatName = AnimationNames.D_HAT_WALKWOOD_UP;
                        hatXmlPath = AnimationNames.D_HAT_WALKWOOD_UP_XML_PATH;
                    } else {
                        clothName = AnimationNames.D_CLOTH_WALK_UP;
                        clothXmlPath = AnimationNames.D_CLOTH_WALK_UP_XML_PATH;
                        hatName = AnimationNames.D_HAT_WALK_UP;
                        hatXmlPath = AnimationNames.D_HAT_WALK_UP_XML_PATH;
                    }
                    break;
            }
        } else {
            walkName = AnimationNames.WALK_DOWN;
            walkXmlPath = AnimationNames.WALK_DOWN_XML_PATH;
            switch (hero.getSkinEnum()){
                case CLOTH:
                    if(hero.isWithWood()){
                        clothName = AnimationNames.CLOTH_WALKWOOD_DOWN;
                        clothXmlPath = AnimationNames.CLOTH_WALKWOOD_DOWN_XML_PATH;
                        hatName = AnimationNames.HAT_WALKWOOD_DOWN;
                        hatXmlPath = AnimationNames.HAT_WALKWOOD_DOWN_XML_PATH;
                    } else {
                        clothName = AnimationNames.CLOTH_WALK_DOWN;
                        clothXmlPath = AnimationNames.CLOTH_WALK_DOWN_XML_PATH;
                        hatName = AnimationNames.HAT_WALK_DOWN;
                        hatXmlPath = AnimationNames.HAT_WALK_DOWN_XML_PATH;
                    }
                    break;
                case DOUBLE_CLOTH:
                    if(hero.isWithWood()){
                        clothName = AnimationNames.D_CLOTH_WALKWOOD_DOWN;
                        clothXmlPath = AnimationNames.D_CLOTH_WALKWOOD_DOWN_XML_PATH;
                        hatName = AnimationNames.D_HAT_WALKWOOD_DOWN;
                        hatXmlPath = AnimationNames.D_HAT_WALKWOOD_DOWN_XML_PATH;
                    } else {
                        clothName = AnimationNames.D_CLOTH_WALK_DOWN;
                        clothXmlPath = AnimationNames.D_CLOTH_WALK_DOWN_XML_PATH;
                        hatName = AnimationNames.D_HAT_WALK_DOWN;
                        hatXmlPath = AnimationNames.D_HAT_WALK_DOWN_XML_PATH;
                    }
                    break;
            }
        }
    }
}
