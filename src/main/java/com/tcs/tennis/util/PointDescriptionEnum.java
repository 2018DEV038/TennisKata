package com.tcs.tennis.util;

import java.util.HashMap;
import java.util.Map;

public enum PointDescriptionEnum {

    LOVE(0),
    FIFTEEN(1),
    THIRTY(2),
    FORTY(3);

    private static final Map<Integer,PointDescriptionEnum> LOOKUP = new HashMap<>();

    static {
        for(PointDescriptionEnum pointDescriptionEnum : PointDescriptionEnum.values())
            LOOKUP.put(pointDescriptionEnum.getPoints(), pointDescriptionEnum);
    }

    private PointDescriptionEnum(final int points){
        this.points = points;
    }

    private int points;

    public int getPoints(){
        return points;
    }

    public static PointDescriptionEnum get(int points) {
        return LOOKUP.get(points);
    }

}
