package com.bennyplo.android_mooc_graphics_3d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MyView extends View {
    private Paint redPaint, bluePaint, pinkPaint,greenPaint;
    private Coordinate[]cube_vertices;
    private Coordinate[]draw_cube_vertices;
    private Coordinate[]head_vertices;
    private Coordinate[]upbody_vertices;
    private Coordinate[]neck_vertices;
    private Coordinate[]hip_vertices;
    private Coordinate[]right_leg_vertices;
    private Coordinate[]long_right_leg_vertices;
    private Coordinate[]left_leg_vertices;
    private Coordinate[]long_left_leg_vertices;
    private Coordinate[]right_foot_vertices;
    private Coordinate[]left_foot_vertices;
    public MyView(Context context) {
        super(context, null);
        final MyView thisview=this;
        //create the paint object
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//Stroke
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(2);

        bluePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bluePaint.setStyle(Paint.Style.STROKE);//Stroke
        bluePaint.setColor(Color.BLUE);
        bluePaint.setStrokeWidth(2);

        pinkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pinkPaint.setStyle(Paint.Style.STROKE);//Stroke
        pinkPaint.setColor(Color.MAGENTA);
        pinkPaint.setStrokeWidth(2);

        greenPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        greenPaint.setStyle(Paint.Style.STROKE);//Stroke
        greenPaint.setColor(Color.GREEN);
        greenPaint.setStrokeWidth(2);

        //create a 3D cube
        cube_vertices = new Coordinate[8];
        cube_vertices[0] = new Coordinate(-1, -1, -1, 1);
        cube_vertices[1] = new Coordinate(-1, -1, 1, 1);
        cube_vertices[2] = new Coordinate(-1, 1, -1, 1);
        cube_vertices[3] = new Coordinate(-1, 1, 1, 1);
        cube_vertices[4] = new Coordinate(1, -1, -1, 1);
        cube_vertices[5] = new Coordinate(1, -1, 1, 1);
        cube_vertices[6] = new Coordinate(1, 1, -1, 1);
        cube_vertices[7] = new Coordinate(1, 1, 1, 1);

//        draw_cube_vertices=translate(cube_vertices,20,20,20);
        head_vertices=scale(cube_vertices,100,100,50);
        head_vertices=translate(head_vertices,500,300,50);

        neck_vertices=scale(cube_vertices,50,30,50);
        neck_vertices=translate(neck_vertices,500,430,50);

        upbody_vertices=scale(cube_vertices,200,250,50);
        upbody_vertices=translate(upbody_vertices,500,710,50);

        hip_vertices=scale(cube_vertices,200,50,50);
        hip_vertices=translate(hip_vertices,500,1010,50);

        right_leg_vertices=scale(cube_vertices,50,100,50);
        right_leg_vertices=translate(right_leg_vertices,350,1160,50);

        left_leg_vertices=scale(cube_vertices,50,100,50);
        left_leg_vertices=translate(left_leg_vertices,650,1160,50);

        long_right_leg_vertices=scale(cube_vertices,50,150,50);
        long_right_leg_vertices=translate(long_right_leg_vertices,350,1410,50);

        long_left_leg_vertices=scale(cube_vertices,50,150,50);
        long_left_leg_vertices=translate(long_left_leg_vertices,650,1410,50);

        right_foot_vertices=scale(cube_vertices,50,25,50);
        right_foot_vertices=translate(right_foot_vertices,350,1585,50);

        left_foot_vertices=scale(cube_vertices,50,25,50);
        left_foot_vertices=translate(left_foot_vertices,650,1585,50);


        head_vertices=rotate(head_vertices,45,1);
        upbody_vertices=rotate(upbody_vertices,45,1);
        neck_vertices=rotate(neck_vertices,45,1);
        hip_vertices=rotate(hip_vertices,45,1);
        right_leg_vertices=rotate(right_leg_vertices,45,1);
        long_right_leg_vertices=rotate(long_right_leg_vertices,45,1);
        left_leg_vertices=rotate(left_leg_vertices,45,1);
        long_left_leg_vertices=rotate(long_left_leg_vertices,45,1);
        right_foot_vertices=rotate(right_foot_vertices,45,1);
        left_foot_vertices=rotate(left_foot_vertices,45,1);

        thisview.invalidate();//update the view


//        Timer timer = new Timer();
//        TimerTask task = new TimerTask() {
//            float position_x=0f;
//            boolean dir=true;
//            @Override
//            public void run() {
//                if (position_x+80>=getWidth() && dir == true)
//                    dir=false;
//                else if (dir==false && position_x<=0)
//                    dir=true;
//                if(dir){
//                    head_vertices=quaternion(head_vertices,1,0,0.5,0);
//                    upbody_vertices=quaternion(upbody_vertices,1,0,0.5,0);
//                    neck_vertices=quaternion(neck_vertices,1,0,0.5,0);
//                    hip_vertices=quaternion(hip_vertices,1,0,0.5,0);
//                    right_leg_vertices=quaternion(right_leg_vertices,1,0,0.5,0);
//                    long_right_leg_vertices=quaternion(long_right_leg_vertices,1,0,0.5,0);
//                    left_leg_vertices=quaternion(left_leg_vertices,1,0,0.5,0);
//                    long_left_leg_vertices=quaternion(long_left_leg_vertices,1,0,0.5,0);
//                    right_foot_vertices=quaternion(right_foot_vertices,1,0,0.5,0);
//                    left_foot_vertices=quaternion(left_foot_vertices,1,0,0.5,0);
//                    position_x+=1f;
//                } else {
//                    head_vertices=quaternion(head_vertices,1,0,0.5,0);
//                    upbody_vertices=quaternion(upbody_vertices,1,0,0.5,0);
//                    neck_vertices=quaternion(neck_vertices,1,0,0.5,0);
//                    hip_vertices=quaternion(hip_vertices,1,0,0.5,0);
//                    right_leg_vertices=quaternion(right_leg_vertices,1,0,0.5,0);
//                    long_right_leg_vertices=quaternion(long_right_leg_vertices,1,0,0.5,0);
//                    left_leg_vertices=quaternion(left_leg_vertices,1,0,0.5,0);
//                    long_left_leg_vertices=quaternion(long_left_leg_vertices,1,0,0.5,0);
//                    right_foot_vertices=quaternion(right_foot_vertices,1,0,0.5,0);
//                    left_foot_vertices=quaternion(left_foot_vertices,1,0,0.5,0);
//                    position_x-=1f;
//                }
//                thisview.invalidate();
//            }
//        };
//        timer.scheduleAtFixedRate(task,100,2);

    }

    private  void DrawLinePairs(Canvas canvas, Coordinate[] vertices, int start, int end, Paint paint)
    {//draw a line connecting 2 points
        //canvas - canvas of the view
        //points - array of points
        //start - index of the starting point
        //end - index of the ending point
        //paint - the paint of the line
        canvas.drawLine((int)vertices[start].x,(int)vertices[start].y,(int)vertices[end].x,(int)vertices[end].y,paint);
    }

    private void DrawCube(Canvas canvas)
    {//draw a cube on the screen
        DrawLinePairs(canvas, head_vertices, 0, 1, bluePaint);
        DrawLinePairs(canvas, head_vertices, 1, 3, bluePaint);
        DrawLinePairs(canvas, head_vertices, 3, 2, bluePaint);
        DrawLinePairs(canvas, head_vertices, 2, 0, bluePaint);
        DrawLinePairs(canvas, head_vertices, 4, 5, bluePaint);
        DrawLinePairs(canvas, head_vertices, 5, 7, bluePaint);
        DrawLinePairs(canvas, head_vertices, 7, 6, bluePaint);
        DrawLinePairs(canvas, head_vertices, 6, 4, bluePaint);
        DrawLinePairs(canvas, head_vertices, 0, 4, bluePaint);
        DrawLinePairs(canvas, head_vertices, 1, 5, bluePaint);
        DrawLinePairs(canvas, head_vertices, 2, 6, bluePaint);
        DrawLinePairs(canvas, head_vertices, 3, 7, bluePaint);

        DrawLinePairs(canvas, neck_vertices, 0, 1, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 1, 3, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 3, 2, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 2, 0, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 4, 5, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 5, 7, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 7, 6, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 6, 4, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 0, 4, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 1, 5, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 2, 6, pinkPaint);
        DrawLinePairs(canvas, neck_vertices, 3, 7, pinkPaint);

        DrawLinePairs(canvas, upbody_vertices, 0, 1, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 1, 3, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 3, 2, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 2, 0, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 4, 5, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 5, 7, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 7, 6, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 6, 4, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 0, 4, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 1, 5, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 2, 6, redPaint);
        DrawLinePairs(canvas, upbody_vertices, 3, 7, redPaint);

        DrawLinePairs(canvas, hip_vertices, 0, 1, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 1, 3, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 3, 2, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 2, 0, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 4, 5, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 5, 7, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 7, 6, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 6, 4, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 0, 4, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 1, 5, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 2, 6, pinkPaint);
        DrawLinePairs(canvas, hip_vertices, 3, 7, pinkPaint);

        DrawLinePairs(canvas, right_leg_vertices, 0, 1, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 1, 3, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 3, 2, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 2, 0, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 4, 5, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 5, 7, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 7, 6, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 6, 4, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 0, 4, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 1, 5, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 2, 6, bluePaint);
        DrawLinePairs(canvas, right_leg_vertices, 3, 7, bluePaint);

        DrawLinePairs(canvas, left_leg_vertices, 0, 1, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 1, 3, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 3, 2, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 2, 0, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 4, 5, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 5, 7, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 7, 6, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 6, 4, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 0, 4, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 1, 5, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 2, 6, bluePaint);
        DrawLinePairs(canvas, left_leg_vertices, 3, 7, bluePaint);

        DrawLinePairs(canvas, long_right_leg_vertices, 0, 1, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 1, 3, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 3, 2, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 2, 0, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 4, 5, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 5, 7, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 7, 6, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 6, 4, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 0, 4, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 1, 5, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 2, 6, greenPaint);
        DrawLinePairs(canvas, long_right_leg_vertices, 3, 7, greenPaint);

        DrawLinePairs(canvas, long_left_leg_vertices, 0, 1, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 1, 3, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 3, 2, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 2, 0, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 4, 5, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 5, 7, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 7, 6, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 6, 4, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 0, 4, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 1, 5, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 2, 6, greenPaint);
        DrawLinePairs(canvas, long_left_leg_vertices, 3, 7, greenPaint);

        DrawLinePairs(canvas, right_foot_vertices, 0, 1, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 1, 3, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 3, 2, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 2, 0, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 4, 5, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 5, 7, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 7, 6, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 6, 4, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 0, 4, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 1, 5, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 2, 6, redPaint);
        DrawLinePairs(canvas, right_foot_vertices, 3, 7, redPaint);

        DrawLinePairs(canvas, left_foot_vertices, 0, 1, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 1, 3, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 3, 2, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 2, 0, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 4, 5, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 5, 7, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 7, 6, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 6, 4, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 0, 4, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 1, 5, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 2, 6, redPaint);
        DrawLinePairs(canvas, left_foot_vertices, 3, 7, redPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw objects on the screen
        super.onDraw(canvas);
        DrawCube(canvas);//draw a cube onto the screen
    }

    protected Coordinate centroid(Coordinate[] points){
        Coordinate center = new Coordinate(0,0,0,0);
        for (int i=0; i<points.length; i++) {
            center.x = center.x + points[i].x;
            center.y = center.y + points[i].y;
            center.z = center.z + points[i].z;
            center.w = center.w + points[i].w;
        }
        center.x = center.x/points.length;
        center.y = center.y/points.length;
        return center;
    }

    //*********************************
    //matrix and transformation functions
    public double []GetIdentityMatrix()
    {//return an 4x4 identity matrix
        double []matrix=new double[16];
        matrix[0]=1;matrix[1]=0;matrix[2]=0;matrix[3]=0;
        matrix[4]=0;matrix[5]=1;matrix[6]=0;matrix[7]=0;
        matrix[8]=0;matrix[9]=0;matrix[10]=1;matrix[11]=0;
        matrix[12]=0;matrix[13]=0;matrix[14]=0;matrix[15]=1;
        return matrix;
    }
    public Coordinate Transformation(Coordinate vertex,double []matrix)
    {//affine transformation with homogeneous coordinates
        //i.e. a vector (vertex) multiply with the transformation matrix
        // vertex - vector in 3D
        // matrix - transformation matrix
        Coordinate result=new Coordinate();
        result.x=matrix[0]*vertex.x+matrix[1]*vertex.y+matrix[2]*vertex.z+matrix[3];
        result.y=matrix[4]*vertex.x+matrix[5]*vertex.y+matrix[6]*vertex.z+matrix[7];
        result.z=matrix[8]*vertex.x+matrix[9]*vertex.y+matrix[10]*vertex.z+matrix[11];
        result.w=matrix[12]*vertex.x+matrix[13]*vertex.y+matrix[14]*vertex.z+matrix[15];
        return result;
    }
    public Coordinate[]Transformation(Coordinate []vertices,double []matrix)
    {   //Affine transform a 3D object with vertices
        // vertices - vertices of the 3D object.
        // matrix - transformation matrix
        Coordinate []result=new Coordinate[vertices.length];
        for (int i=0;i<vertices.length;i++)
        {
           result[i]=Transformation(vertices[i],matrix);
           result[i].Normalise();
        }
        return result;
    }
    //***********************************************************
    //Affine transformation
    public Coordinate []translate(Coordinate []vertices,double tx,double ty,double tz)
    {
        double []matrix=GetIdentityMatrix();
        matrix[3]=tx;
        matrix[7]=ty;
        matrix[11]=tz;
        return Transformation(vertices,matrix);
    }
    private Coordinate[]scale(Coordinate []vertices,double sx,double sy,double sz)
    {
        double []matrix=GetIdentityMatrix();
        matrix[0]=sx;
        matrix[5]=sy;
        matrix[10]=sz;
        return Transformation(vertices,matrix);
    }

    // axis (x=1, y=2, z=3)
    private Coordinate[]rotate(Coordinate []vertices,double degree, int axis)
    {
        double []matrix=GetIdentityMatrix();
        double radian = degree*Math.PI/180;
        if (axis==0) {
            matrix[5] = Math.cos(radian);
            matrix[6] = -Math.sin(radian);
            matrix[9] = Math.sin(radian);
            matrix[10] = Math.cos(radian);
        } else if (axis==1) {
            matrix[0] = Math.cos(radian);
            matrix[2] = Math.sin(radian);
            matrix[8] = -Math.sin(radian);
            matrix[10] = Math.cos(radian);
        } else if (axis==2){
            matrix[0] = Math.cos(radian);
            matrix[1] = -Math.sin(radian);
            matrix[4] = Math.sin(radian);
            matrix[5] = Math.cos(radian);
        }
        return Transformation(vertices,matrix);
    }
    private Coordinate[] shear(Coordinate[] vertices, double hx, double hy) {
        double[] matrix = GetIdentityMatrix();
        matrix[2] = hx;
        matrix[6] = hy;
        return Transformation(vertices, matrix);
    }

    private Coordinate[] quaternion(Coordinate[] vertices, double w, double x, double y, double z) {
        double[] matrix = GetIdentityMatrix();

        matrix[0] = (w*w)+(x*x)-(y*y)-(z*z);
        matrix[1] = 2*(x*y) - 2*(w*z);
        matrix[2] = 2*(x*z) + 2*(w*x);

        matrix[4] = 2*(x*y) + 2*(w*z);
        matrix[5] = (w*w)+(y*y)-(x*x)-(z*z);
        matrix[6] = 2*(y*z) - 2*(w*x);

        matrix[8] = 2*(x*z) - 2*(w*y);
        matrix[9] = 2*(y*z) + 2*(w*x);
        matrix[10] = (w*w)+(z*z)-(x*x)-(y*y);

        return Transformation(vertices, matrix);
    }

}