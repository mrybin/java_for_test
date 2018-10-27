package ru.fortesting.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointClassTest {
    @Test
    public void Pointfunc() {
        Point p1= new Point(1.0,2.0);
        Point p2= new Point (4.0,6.0);
        Assert.assertEquals(p1.distance(p2),5.0);
    }
}
