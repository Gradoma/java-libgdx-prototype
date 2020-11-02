package by.gradomski.game.environment;

import by.gradomski.game.entity.Palm;
import by.gradomski.game.graphic.AnimationManager;
import by.gradomski.game.graphic.AnimationNames;

import java.util.Arrays;

public class TreeCreator {
    private static final TreePool treePool = TreePool.getInstance();
    private static final AnimationManager animationManager = AnimationManager.getInstance();
    private static double probability = 0.1;
    private static int[] growablePalmIndexes = {10, 12, 16, 19, 31};
    private static int[] growableBeachIndexes = {3, 6, 9, 11, 12, 31};

    public static boolean placeTree(int index, int coordinateX, int coordinateY){
        String name = null;
        if(isGrowable(index, growablePalmIndexes)) {
            name = AnimationNames.TROPIC_PALM;
        } else if(isGrowable(index, growableBeachIndexes)) {
            name = AnimationNames.PALM_01;
        }
        if (name != null && Math.random() < probability) {
            Palm palm = new Palm(name);
            palm.setCoordinateX(coordinateX);
            palm.setCoordinateY(coordinateY);
            treePool.add(palm);
            animationManager.startAnimation(palm.getTree());
            animationManager.startAnimation(palm.getShadow());
            return true;
        }
        return false;
    }

    private static boolean isGrowable(int index, int[] growableIndexes){
        return Arrays.stream(growableIndexes).anyMatch(i -> i == index);
    }
}
