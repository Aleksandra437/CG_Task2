package com.cgvsu.protocurvefxapp;

import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Lagrange {
    static double point(final double x, final ArrayList<Point2D> points) {
        double lagrange = 0;
        for (Point2D i : points) {
            double basicsPol = 1;
            for (Point2D j : points) {
                if (i.getX() != j.getX()) {
                    basicsPol *= (x - j.getX()) / (i.getX() - j.getX());
                }
            }
            lagrange += basicsPol * i.getY();
        }
        return lagrange;
    }
}
