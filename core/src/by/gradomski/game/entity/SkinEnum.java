package by.gradomski.game.entity;

public enum SkinEnum {
    CLOTH,
    DOUBLE_CLOTH;

    private static final SkinEnum[] values = SkinEnum.values();

    public static SkinEnum nextSkin(SkinEnum current){
        int next = current.ordinal() + 1;
        if(next >= values().length){
            next = 0;
        }
        return values[next];
    }
}
