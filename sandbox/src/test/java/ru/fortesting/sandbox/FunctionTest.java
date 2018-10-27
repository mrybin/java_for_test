package ru.fortesting.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.fortesting.sandbox.first.distance;

public class FunctionTest {
    @Test
    public void testfunc(){
        Point p1= new Point(1.0,2.0);
        Point p2= new Point (4.0, 6.0);
        Assert.assertEquals(distance(p1,p2),5.0);
    }
}
