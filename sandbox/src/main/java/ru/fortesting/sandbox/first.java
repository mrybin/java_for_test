package ru.fortesting.sandbox;

public class first {

    public static double distance (Point p1, Point p2) {
        return Math.sqrt((p2.x-p1.x)*(p2.x-p1.x)+(p2.y-p1.y)*(p2.y-p1.y));
    }
    public static void main(String [] args) {
        Point p1=new Point(1,2);
        Point p2=new Point(4,6);
        System.out.println("Result of function distance "+ distance(p1,p2));
        System.out.println("Result of method distance "+ p1.distance(p2));
}

}