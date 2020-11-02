package by.gradomski.game;

import by.gradomski.game.entity.*;
import by.gradomski.game.exception.BuilderException;
import by.gradomski.game.graphic.AnimationManager;
import by.gradomski.game.graphic.MapBuilder;
import by.gradomski.game.input.InputHandler;
import by.gradomski.game.parser.MapConfigBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class ZombieGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private Hero hero;
    private AnimationManager animationManager = AnimationManager.getInstance();
    private TextureRegion currentFrame;
    private SpriteBatch spriteBatch;

    @Override
    public void create () {
        MapConfigBuilder mapConfigBuilder = new MapConfigBuilder();
        try {
            mapConfigBuilder.buildMapConfig("maps/main_island/main_island_map_config.xml");
        } catch (BuilderException e) {
            e.printStackTrace();
        }

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1200, 800);
        MapParameter mapParameter = mapConfigBuilder.getMapParameter();
        camera.position.set(mapParameter.getOffsetX() + camera.viewportWidth / 2,
                -mapParameter.getOffsetY() + mapParameter.getTileMapHeight() / 2, 0);

        map = new TiledMap();
        MapLayers layers = map.getLayers();
        TiledMapTileLayer mapBuilder = MapBuilder.buildMapLayer(mapParameter,
                mapConfigBuilder.getTileParameters());
        layers.add(mapBuilder);

        spriteBatch = new SpriteBatch();
        hero = new Hero();
        hero.setCurrentX(1000);
        hero.setCurrentY(2300);
        hero.startState(hero.getCurrentX(), hero.getCurrentY());

        InputHandler inputHandler = new InputHandler(camera, mapConfigBuilder.getMapParameter());
        inputHandler.setHero(hero);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(map);
        renderer.setView(camera);
        renderer.render();

        ArrayList<AnimationWrap> animationList = animationManager.getAnimationList();
        Animation animation;
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        for(AnimationWrap animationWrap : animationList){
            animationWrap.increaseTime(Gdx.graphics.getDeltaTime());
            animation = animationWrap.getAnimation();
            currentFrame = (TextureRegion) animation
                    .getKeyFrame(animationWrap.getStateTime(),animationWrap.isLoop());

            boolean flip = false;
            if(animationWrap.getName().contains("woodcutter") && hero.getTargetX() > hero.getCurrentX()){
                flip = true;
            }
            spriteBatch.draw(currentFrame,
                    flip ? animationWrap.getCoordinateX() + currentFrame.getRegionWidth() : animationWrap.getCoordinateX(),
                    animationWrap.getCoordinateY(),
                    flip ? -currentFrame.getRegionWidth() : currentFrame.getRegionWidth(),
                    currentFrame.getRegionHeight());
            if(animationWrap.getName().contains("walk")){
                if(Math.abs(hero.getCurrentX() - hero.getTargetX()) <= hero.EPSILON |
                    Math.abs(hero.getCurrentY() - hero.getTargetY()) <= hero.EPSILON){
                    hero.setCurrentX(hero.getTargetX());
                    hero.setCurrentY(hero.getTargetY());
                    animationWrap.resetTime();
                    animationManager.endAnimation(animationWrap);
                    if(!animationWrap.getName().contains("hat")
                    & !animationWrap.getName().contains("cloth")){
                        hero.endState();
                    }
                    continue;
                }

                hero.updateCurrentPosition(Gdx.graphics.getDeltaTime());
                animationWrap.setCoordinateX((float) hero.getCurrentX());
                animationWrap.setCoordinateY((float) hero.getCurrentY());
                continue;
            }
            if (animationWrap.getAnimation().getKeyFrames().length > 1 &&
                    animation.isAnimationFinished(animationWrap.getStateTime())){
                if(animationWrap.getName().contains("woodcutter")){
                    animationWrap.resetTime();
                    animationManager.endAnimation(animationWrap);
                    hero.endState();
                }
                animationWrap.resetTime();
                animationManager.endAnimation(animationWrap);
            }
        }
        spriteBatch.end();
    }

    @Override
    public void dispose () {
        map.dispose();
        renderer.dispose();
        MapBuilder.disposeMapTexture();
        spriteBatch.dispose();
    }
}
