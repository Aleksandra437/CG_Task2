package com.cgvsu.protocurvefxapp;

import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class ProtoCurveController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;

    ArrayList<Point2D> points = new ArrayList<Point2D>();

    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        canvas.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY -> handlePrimaryClick(canvas.getGraphicsContext2D(), event);
            }
        });
    }

    static double maxXvalue(ArrayList<Point2D> points) {
        double max = Double.MIN_VALUE;
        for (Point2D element : points) {
            if (max < element.getX()) {
                max = element.getX();
            }
        }
        return max;
    }

    static double minXvalue(ArrayList<Point2D> points) {
        double min = Double.MAX_VALUE;
        for (Point2D element : points) {
            if (min > element.getX()) {
                min = element.getX();
            }
        }
        return min;
    }

    private void handlePrimaryClick(GraphicsContext graphicsContext, MouseEvent event) {
        final Point2D clickPoint = new Point2D(event.getX(), event.getY());
        points.add(clickPoint);

        graphicsContext.clearRect(0, 0, 800, 600);
        final int POINT_RADIUS = 4;
        for (Point2D i : points) {
            graphicsContext.fillOval(
                    i.getX() - POINT_RADIUS, i.getY() - POINT_RADIUS,
                    2 * POINT_RADIUS, 2 * POINT_RADIUS);
        }

        if (points.size() > 0) {
            for (double i = minXvalue(points); i < maxXvalue(points); i++) {
                graphicsContext.strokeLine(i, Lagrange.point(i, points), i + 1, Lagrange.point(i + 1, points));
            }
        }
    }
}
