package by.gradomski.game.graphic;

import by.gradomski.game.entity.AnimationParameter;
import by.gradomski.game.entity.AnimationWrap;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationBuilder {
    private static final String PACKAGE = "animations";
    private static final String SLASH = "/";

    public static AnimationWrap buildAnimation(AnimationParameter animationParameter){
        int frameCount = animationParameter.getFrameList().size();
        String texturePath = PACKAGE + SLASH + animationParameter.getName() + SLASH + animationParameter.getTextureName();
        FileHandle fileHandle = new FileHandle(texturePath);
        Texture texture = new Texture(fileHandle);
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth()/frameCount,
                texture.getHeight());
        TextureRegion[] frames = tmp[0];
        Animation animation = new Animation(0.085f, frames);

        AnimationWrap animationWrap = new AnimationWrap();
        animationWrap.setName(animationParameter.getName());
        animationWrap.setAnimation(animation);
        return animationWrap;
    }
}
