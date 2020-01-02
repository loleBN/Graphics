package com.bennyplo.android_mooc_graphics_3d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MyView extends View {
    private Paint redPaint; //paint object for drawing the lines
    private Coordinate[]cube_vertices;//the vertices of a 3D cube
    private Coordinate[]draw_cube_vertices;//the vertices for drawing a 3D cube
    public MyView(Context context) {
        super(context, null);
        final MyView thisview=this;
        //create the paint object
        redPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//Stroke
        redPaint.setColor(Color.RED);
        redPaint.setStrokeWidth(2);
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

        draw_cube_vertices=translate(cube_vertices,2,2,2);
        draw_cube_vertices=scale(draw_cube_vertices,40,40,40);
//        draw_cube_vertices=rotate(draw_cube_vertices,45,1);
//        draw_cube_vertices=rotate(draw_cube_vertices,45,0);
        thisview.invalidate();//update the view

        /*### 1st try
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int angle=45;
            @Override
            public void run() {
                draw_cube_vertices=translate(cube_vertices,2,2,2);
                draw_cube_vertices=scale(draw_cube_vertices,40,40,40);
                draw_cube_vertices=rotate(draw_cube_vertices,90,0);
                draw_cube_vertices=rotate(draw_cube_vertices,25,1);
                draw_cube_vertices=translate(cube_vertices,200,200,0);
                thisview.invalidate();
                angle+=10;
                if (angle>=360) angle=0;
            }
        };
        timer.scheduleAtFixedRate(task,100,100);*/

        ///2sd try
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            float position_x=0f;
            boolean dir=true;
            @Override
            public void run() {
                if (position_x+80>=getWidth() && dir == true)
                    dir=false;
                else if (dir==false && position_x<=0)
                    dir=true;
                if(dir){
                    draw_cube_vertices=translate(draw_cube_vertices,1f,0,0);
                    position_x+=1f;
                } else {
                    draw_cube_vertices=translate(draw_cube_vertices,-1f,0,0);
                    position_x-=1f;
                }
                thisview.invalidate();
            }
        };
        timer.scheduleAtFixedRate(task,100,2);
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
        DrawLinePairs(canvas, draw_cube_vertices, 0, 1, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 1, 3, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 3, 2, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 2, 0, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 4, 5, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 5, 7, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 7, 6, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 6, 4, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 0, 4, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 1, 5, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 2, 6, redPaint);
        DrawLinePairs(canvas, draw_cube_vertices, 3, 7, redPaint);
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

}