package de.shao.driver;

import java.awt.*;

public enum SystemConstraints {

    TEN_TEN(new Point(66,72)),
    SIXTEEN_SIXTEEN(new Point(66,72)),
    TWENTY_TWENTY(new Point(66,72)),
    TEN_TEN_TIME(new Point(300,607)),
    SIXTEEN_SIXTEEN_TIME(new Point(300, 897));

    private Point point;

    SystemConstraints(Point startPoint) {
        point = startPoint;
    }

    public Point point(){
        return point;
    }
}
