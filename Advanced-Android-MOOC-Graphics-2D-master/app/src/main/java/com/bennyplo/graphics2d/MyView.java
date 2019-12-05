package com.bennyplo.graphics2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by benlo on 09/05/2018.
 */

public class MyView extends View {
    private Paint redPaint;
    private Paint bluePaint;
    private Path lineGraph;
    int viewHeight, viewWidth;

    Point[] points;
    Path path;

    public MyView(Context context) {
        super(context, null);

        //Add your initialisation code here
        redPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//stroke only no fill
        redPaint.setColor(0xffff0000);//color red
        redPaint.setStrokeWidth(5);//set the line stroke width to 5

        bluePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        bluePaint.setStyle(Paint.Style.STROKE);//stroke only no fill
        bluePaint.setColor(0xFF0000FF);//color blue
        bluePaint.setStrokeWidth(2);//set the line stroke width to 5

        viewHeight = getResources().getDisplayMetrics().heightPixels - 210;
        viewWidth = getResources().getDisplayMetrics().widthPixels;

        int plotData[]={11,29,10,20,12,5,31,24,21,13};
        lineGraph = createLineGraph(plotData, viewWidth, viewHeight);
    }

    public void createPoints() {
        points = new Point[5];
        points[0] = new Point(50,300);
        points[1] = new Point(150,400);
        points[2] = new Point(180,340);
        points[3] = new Point(240,420);
        points[4] = new Point(300,200);
    }

    public void createPoints2() {
        points = new Point[4];
        points[0] = new Point(500,300);
        points[1] = new Point(500,400);
        points[2] = new Point(600,400);
        points[3] = new Point(600,300);
    }

    protected void updatePath(Point[] newPoints) {
        path.reset();
        path.moveTo(newPoints[0].x, newPoints[0].y);
        for (int i=1; i<newPoints.length; i++)
            path.lineTo(newPoints[i].x, newPoints[i].y);
        path.close();
    }

    protected Point[] affineTransformation(Point[] vertices, double[][] matrix){
        Point[] result = new Point[vertices.length];
        for (int i=0; i<vertices.length; i++) {
            int t = (int) (matrix[0][0] * vertices[i].x + matrix[0][1] * vertices[i].y + matrix[0][2]);
            int u = (int) (matrix[1][0] * vertices[i].x + matrix[1][1] * vertices[i].y + matrix[1][2]);
            result[i] = new Point(t, u);
        }
        return result;
    }

    protected Point[] translate(Point[] input, int px, int py){
        double[][] matrix = new double[3][3];
        matrix[0][0] = 1; matrix[0][1] = 0; matrix[0][2] = px;
        matrix[1][0] = 0; matrix[1][1] = 1; matrix[1][2] = py;
        matrix[2][0] = matrix[2][1] = 0; matrix[2][2] = 1;
        return affineTransformation(input, matrix);
    }

    protected Point[] shear(Point[] input, double factorX, double factorY){
        double[][] matrix = new double[3][3];
        matrix[0][0] = 1; matrix[0][1] = factorX; matrix[0][2] = 0;
        matrix[1][0] = factorY; matrix[1][1] = 1; matrix[1][2] = 0;
        matrix[2][0] = matrix[2][1] = 0; matrix[2][2] = 1;
        return affineTransformation(input, matrix);
    }

    protected Point[] scale(Point[] input, double factorX, double factorY){
        double[][] matrix = new double[3][3];
        matrix[0][0] = factorX; matrix[0][1] = 0; matrix[0][2] = 0;
        matrix[1][0] = 0; matrix[1][1] = factorY; matrix[1][2] = 0;
        matrix[2][0] = matrix[2][1] = 0; matrix[2][2] = 1;
        return affineTransformation(input, matrix);
    }

    protected double convert2rad(double degree){
        return degree*Math.PI/180;
    }

    protected Point centroid(Point[] points){
        Point center = new Point(0,0);
        for (int i=0; i<points.length; i++) {
            center.x = center.x + points[i].x;
            center.y = center.y + points[i].y;
        }
        center.x = center.x/points.length;
        center.y = center.y/points.length;
        return center;
    }

    protected Point[] rotate(Point[] input, double degree){
        double[][] matrix = new double[3][3];
        double rad = convert2rad(degree);
        matrix[0][0] = Math.cos(rad); matrix[0][1] = -(Math.sin(rad)); matrix[0][2] = 0;
        matrix[1][0] = Math.sin(rad); matrix[1][1] = Math.cos(rad); matrix[1][2] = 0;
        matrix[2][0] = matrix[2][1] = 0; matrix[2][2] = 1;
        return affineTransformation(input, matrix);
    }

    public void drawPolyline(Canvas canvas){
        Paint paint = new Paint();
        Path path = new Path();

        path.moveTo(50,300);
        path.lineTo(160,280);
        path.lineTo(300,380);
        path.lineTo(380,370);
        path.lineTo(280,450);
        path.lineTo(100,390);
        path.lineTo(160,380);
        path.lineTo(50,300);

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GREEN);
        canvas.drawPath(path, paint);
    }

    public void drawPolygon(Canvas canvas){
        Paint redFillPaint = new Paint();
        Paint blackPaint = new Paint();
        Path path = new Path();

//        path.moveTo(50,300);
//        path.lineTo(150,400);
//        path.lineTo(190,350);
//        path.lineTo(250,420);
//        path.lineTo(310,150);

//        path.moveTo(190,350);
//        path.lineTo(150,400);
//        path.lineTo(250,420);
//        path.lineTo(310,150);
//        path.lineTo(50,300);

        //4->1->3->2->0
        path.moveTo(310,150);
        path.lineTo(150,400);
        path.lineTo(250,420);
        path.lineTo(190,350);
        path.lineTo(50,300);
        path.close();

        redFillPaint.setStyle(Paint.Style.FILL);
        redFillPaint.setARGB(255,255,0,0);
        blackPaint.setStyle(Paint.Style.STROKE);
        blackPaint.setStrokeWidth(5);
        blackPaint.setColor(Color.BLACK);

        canvas.drawPath(path, redFillPaint);
        canvas.drawPath(path, blackPaint);
    }

    public void drawPolynFillGradient(Canvas canvas){
        Paint paint = new Paint();
        Path path = new Path();

        path.moveTo(50,300);
        path.lineTo(150,400);
        path.lineTo(190,350);
        path.lineTo(250,420);
        path.lineTo(300,200);
        path.close();

        //(x_0, y_0) : (50, 300)
        //(x_4, y_4) : (300, 200)
        LinearGradient linear = new LinearGradient(50, 300, 1000, 1000, Color.BLUE, Color.RED, Shader.TileMode.MIRROR);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(linear);

        canvas.drawPath(path, paint);
    }

    public void affineTransformationTest01(Canvas canvas){
        Paint paint = new Paint();
        createPoints();

        path = new Path();
        path.moveTo(points[0].x, points[0].y);
        path.lineTo(points[1].x, points[1].y);
        path.lineTo(points[2].x, points[2].y);
        path.lineTo(points[3].x, points[3].y);
        path.lineTo(points[4].x, points[4].y);
        canvas.drawPath(path,redPaint);

        Point[] newPoints = shear(points, 0.2, 0);
        newPoints = scale(newPoints, 0.5, 3);

        Point center = centroid(newPoints);
        newPoints = translate(newPoints,-center.x,-center.y);
        newPoints = rotate(newPoints, 45);
        newPoints = translate(newPoints,center.x,center.y);

        newPoints = translate(newPoints, 550,0);

        updatePath(newPoints);
        canvas.drawPath(path, paint);

        //(x_0, y_0) : (50, 300)
        //(x_4, y_4) : (300, 200)
        LinearGradient linear = new LinearGradient(newPoints[0].x, newPoints[0].y, newPoints[4].x, newPoints[4].y, Color.BLUE, Color.RED, Shader.TileMode.MIRROR);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(linear);

        canvas.drawPath(path, paint);
    }

    public void affineTransformationTest02(Canvas canvas){
        Paint paint = new Paint();
        createPoints2();
        path = new Path();
        path.moveTo(points[0].x, points[0].y);
        path.lineTo(points[1].x, points[1].y);
        path.lineTo(points[2].x, points[2].y);
        path.lineTo(points[3].x, points[3].y);
        canvas.drawPath(path,redPaint);

        Point center = centroid(points);

        Point[] newPoints = translate(points,-center.x,-center.y);
        newPoints = rotate(newPoints, 45);
        newPoints = translate(newPoints,center.x,center.y);

        updatePath(newPoints);
        canvas.drawPath(path, paint);

        //(x_0, y_0) : (50, 300)
        //(x_4, y_4) : (300, 200)
        LinearGradient linear = new LinearGradient(500, 400, 600, 300, Color.BLUE, Color.RED, Shader.TileMode.MIRROR);
        paint.setStyle(Paint.Style.FILL);
        paint.setShader(linear);

        canvas.drawPath(path, paint);
    }

    private Path createLineGraph(int[] input , int width, int height){
        Point[] ptArray = new Point[input.length];
        int minValue = 999999, maxValue = -999999;
        for (int i=0; i<input.length;i++){
            ptArray[i]=new Point(i, input[i]);
            minValue = Math.min(minValue, input[i]);
            maxValue = Math.max(maxValue, input[i]);
        }
        ptArray = translate(ptArray,0,-minValue);
        double yScale = height/(double) (maxValue-minValue), xScale = width/(double)(input.length-1);

        ptArray=scale(ptArray,xScale, yScale);
        Path result = new Path();
        result.moveTo(ptArray[0].x,ptArray[0].y);

        for(int i=1;i<ptArray.length;i++)
            result.lineTo(ptArray[i].x, ptArray[i].y);

        return result;
    }
    private Path createPieGraph(int[] input , int width, int height){
        Point[] ptArray = new Point[input.length];
        int minValue = 999999, maxValue = -999999;
        for (int i=0; i<input.length;i++){
            ptArray[i]=new Point(i, input[i]);
            minValue = Math.min(minValue, input[i]);
            maxValue = Math.max(maxValue, input[i]);
        }
        ptArray = translate(ptArray,0,-minValue);
        double yScale = height/(double) (maxValue-minValue), xScale = width/(double)(input.length-1);

        ptArray=scale(ptArray,xScale, yScale);
        Path result = new Path();
        result.moveTo(ptArray[0].x,ptArray[0].y);

        for(int i=1;i<ptArray.length;i++)
            result.lineTo(ptArray[i].x, ptArray[i].y);

        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Add your drawing code here
        //canvas.drawRect(500, 500,700,700,redPaint);
        //canvas.drawCircle(600,600,145,bluePaint);
        //affineTransformationTest01(canvas);
//        canvas.drawPath(lineGraph, redPaint);

//        RectF rectF = new RectF(10,viewHeight/2, 10);
//        canvas.drawArc(new RectF('oval'), 0F, 90F, true, turquoisePaint)
//        canvas.drawArc(oval, 90F, 90F, true, orangePaint)
//        canvas.drawArc(oval, 180F, 90F, true, yellowPaint)
//        canvas.drawArc(oval, 270F, 90F, true, hotPinkPaint)

        for (int i=0; i<50;i++)
            canvas.drawLine(i, (float)(10*Math.sin((i*Math.PI/180))),i+1, (float)(10*Math.sin(((i+1)*Math.PI/180))), bluePaint);
    }
}
