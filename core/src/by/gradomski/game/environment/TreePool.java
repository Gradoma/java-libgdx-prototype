package by.gradomski.game.environment;

import by.gradomski.game.entity.Palm;

import java.awt.*;
import java.util.HashSet;
import java.util.Iterator;

public class TreePool {
    private static TreePool instance;
    private HashSet<Palm> pool = new HashSet<>();

    private TreePool(){}

    public static TreePool getInstance(){
        if(instance == null){
            instance = new TreePool();
        }
        return instance;
    }

    public void add(Palm palm){
        pool.add(palm);
    }

    public boolean pointToTree(double coordinateX, double coordinateY){
        Iterator<Palm> iterator = pool.iterator();
        while (iterator.hasNext()){
            Palm palm = iterator.next();
            Rectangle palmRectangle = palm.getRectangle();
            if(palmRectangle.contains(coordinateX, coordinateY) && !palm.isFelled()){
                return true;
            }
        }
        return false;
    }

    public void treeFelled (double coordinateX, double coordinateY){
        Iterator<Palm> iterator = pool.iterator();
        while (iterator.hasNext()){
            Palm nextPalm = iterator.next();
            if (nextPalm.getRectangle().contains(coordinateX, coordinateY)){
                nextPalm.setFelled(true);
                break;
            }
        }
    }
}
