package by.gradomski.game.input;

import by.gradomski.game.entity.MapParameter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraHandler {
    private OrthographicCamera camera;
    private float defaultScale;
    private float maxScale;
    private float minScale;
    private int lastScreenX;
    private int lastScreenY;
    private int offsetX;
    private int offsetY;
    private int tileMapWidth;
    private int tileMapHeight;
    private float zoomStep = 0.25f;

    CameraHandler(OrthographicCamera camera, MapParameter parameter){
        this.camera = camera;
        this.defaultScale = parameter.getDefaultScale();
        this.maxScale = parameter.getMaxScale();
        this.minScale = parameter.getMinScale();
        this.offsetX = parameter.getOffsetX();
        this.offsetY = parameter.getOffsetY();
        this.tileMapWidth = parameter.getTileMapWidth();
        this.tileMapHeight = parameter.getTileMapHeight();
        camera.zoom = defaultScale;
    }

    OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }


    void setLastScreenX(int lastScreenX) {
        this.lastScreenX = lastScreenX;
    }

    void setLastScreenY(int lastScreenY) {
        this.lastScreenY = lastScreenY;
    }

    void zoomIn(){
        if(camera.zoom > minScale){
            camera.zoom -= zoomStep;
        }
    }

    void zoomOut(){
        if(camera.zoom < maxScale){
            camera.zoom += zoomStep;
            float coordinateX = camera.position.x;
            float coordinateY = camera.position.y;
            if(camera.position.x - camera.viewportWidth * camera.zoom / 2 < offsetX){
                coordinateX = offsetX + camera.viewportWidth * camera.zoom / 2;
            } else if(offsetX + tileMapWidth < camera.position.x + camera.viewportWidth * camera.zoom / 2){
                coordinateX = offsetX + tileMapWidth - camera.viewportWidth * camera.zoom / 2;
            }
            if(camera.position.y - camera.viewportWidth * camera.zoom / 2 < Math.abs(offsetY)){
                coordinateY = Math.abs(offsetY) + camera.viewportWidth * camera.zoom / 2;
            } else if(camera.position.y + camera.viewportHeight * camera.zoom / 2 > Math.abs(offsetY) + tileMapHeight){
                coordinateY = Math.abs(offsetY) + tileMapHeight - camera.viewportWidth * camera.zoom / 2;
            }
            if(coordinateX != camera.position.x || coordinateY != camera.position.y){
                camera.position.set(coordinateX, coordinateY, 0);
            }
        }
    }

    void move(int screenX, int screenY){
        int deltaX = lastScreenX - screenX;
        int deltaY = lastScreenY - screenY;
        if(camera.position.x + deltaX - camera.viewportWidth * camera.zoom / 2 < offsetX
                || offsetX + tileMapWidth < camera.position.x + deltaX + camera.viewportWidth * camera.zoom / 2){
            deltaX = 0;
        }
        if(camera.position.y - deltaY - camera.viewportWidth * camera.zoom / 2 < Math.abs(offsetY)
                || camera.position.y - deltaY + camera.viewportHeight * camera.zoom / 2 > Math.abs(offsetY) + tileMapHeight){
            deltaY = 0;
        }
        camera.position.set(camera.position.x + deltaX, camera.position.y - deltaY, 0);
        lastScreenX = screenX;
        lastScreenY = screenY;
    }
}
