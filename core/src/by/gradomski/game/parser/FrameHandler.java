package by.gradomski.game.parser;

import by.gradomski.game.entity.AnimationParameter;
import by.gradomski.game.entity.Frame;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class FrameHandler extends DefaultHandler {
    private AnimationParameter animationParameter;
    private Frame currentFrame;
    private ArrayList<Frame> frameList;

    public FrameHandler(){
        animationParameter = new AnimationParameter();
    }

    public AnimationParameter getAnimationParameter(){
        return animationParameter;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        if (AnimationConst.ANIMATION.equalsIgnoreCase(qName)) {
            frameList = new ArrayList<>();
            for (int i = 0; i < attrs.getLength(); i++) {
                switch (attrs.getLocalName(i)) {
                    case AnimationConst.TEXTURE:
                        animationParameter.setTextureName(attrs.getValue(i));
                        break;
                    case AnimationConst.NAME:
                        animationParameter.setName(attrs.getValue(i));
                        break;
                }
            }
        } else if (AnimationConst.FRAME.equalsIgnoreCase(qName)) {
            currentFrame = new Frame();
            for (int i = 0; i < attrs.getLength(); i++) {
                switch (attrs.getLocalName(i)) {
                    case AnimationConst.FLIPPED_X:
                        currentFrame.setFlippedXOffset(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case AnimationConst.FLIPPED_Y:
                        currentFrame.setFlippedYOffset(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case AnimationConst.HEIGHT:
                        currentFrame.setHeight(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case AnimationConst.WIDTH:
                        currentFrame.setWidth(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case AnimationConst.TICKS:
                        currentFrame.setTicks(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case AnimationConst.X:
                        currentFrame.setCoordinateX(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case AnimationConst.Y:
                        currentFrame.setCoordinateY(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case AnimationConst.X_OFFSET:
                        currentFrame.setOffsetX(Integer.parseInt(attrs.getValue(i)));
                        break;
                    case AnimationConst.Y_OFFSET:
                        currentFrame.setOffsetY(Integer.parseInt(attrs.getValue(i)));
                        break;
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (AnimationConst.FRAME.equalsIgnoreCase(qName)){
            frameList.add(currentFrame);
        } else if(AnimationConst.ANIMATION.equalsIgnoreCase(qName)){
            animationParameter.setFrameList(frameList);
        }
    }
}
