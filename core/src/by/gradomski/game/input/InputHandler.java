package by.gradomski.game.input;

import by.gradomski.game.entity.*;
import by.gradomski.game.graphic.AnimationManager;
import by.gradomski.game.graphic.AnimationNames;
import by.gradomski.game.graphic.AnimationPool;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class InputHandler implements InputProcessor {
    private CameraHandler cameraHandler;
    private Hero hero;
    private AnimationPool pool = AnimationPool.getInstance();

    public InputHandler(OrthographicCamera camera, MapParameter parameter){
        cameraHandler = new CameraHandler(camera, parameter);
    }

    public void setHero(Hero hero){
        this.hero = hero;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.S){
            hero.setSkinEnum(SkinEnum.nextSkin(hero.getSkinEnum()));
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
            OrthographicCamera camera = cameraHandler.getCamera();
            float clickX = (screenX * camera.zoom) - camera.viewportWidth * camera.zoom / 2 + camera.position.x;
            float clickY = camera.position.y + camera.viewportHeight * camera.zoom / 2 - (screenY * camera.zoom);
            AnimationWrap waveWrap = pool.get(AnimationNames.WHITE_WAVE, AnimationNames.WHITE_WAVE_XML_PATH);
            TextureRegion[] frames = (TextureRegion[]) waveWrap.getAnimation().getKeyFrames();
            waveWrap.setCoordinateX(clickX - (frames[0].getRegionWidth() / 2));
            waveWrap.setCoordinateY(clickY - (frames[0].getRegionWidth() / 2));
            AnimationManager.getInstance().startAnimation(waveWrap);

            hero.endState();
            hero.startState(clickX, clickY);
        } else if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            cameraHandler.setLastScreenX(screenX);
            cameraHandler.setLastScreenY(screenY);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            cameraHandler.move(screenX, screenY);
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        if (amount < 0){
            cameraHandler.zoomIn();
        } else {
            cameraHandler.zoomOut();
        }
        return amount > 0;
    }
}
