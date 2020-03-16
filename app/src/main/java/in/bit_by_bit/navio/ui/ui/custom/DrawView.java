package in.bit_by_bit.navio.ui.ui.custom;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;

import in.bit_by_bit.navio.ui.gettersetter.PathConfig;
import in.bit_by_bit.navio.ui.gettersetter.PathGS;
import in.bit_by_bit.navio.R;

public class DrawView extends View {

    //drawing path
    private Path drawPath;
    //drawing and canvas paint
    private Paint drawPaint, canvasPaint;
    //initial color
    private int paintColor = 0xFF660000;
    //canvas
    private Canvas drawCanvas;
    //canvas bitmap
    private Bitmap canvasBitmap;

    private Bitmap resourceBitmap;

    private Drawable image;

    private int zoomControler=000;


    public DrawView(Context context, AttributeSet attrs){
        super(context, attrs);
        setupDrawing(context);
        setFocusable(true);
    }

    //setup drawing
    private void setupDrawing(Context context){

        //prepare for drawing and setup paint stroke properties
        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(15.0f);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        resourceBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.canvas);
        image = context.getResources().getDrawable(R.drawable.canvas);
    }

    //size assigned to view
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    //draw the view - will be called after touch event
    @Override
    protected void onDraw(Canvas canvas) {
        //image.setBounds((getWidth()/2)-zoomControler, (getHeight()/2)-zoomControler, (getWidth()/2)+zoomControler, (getHeight()/2)+zoomControler);
        Bitmap temp = Bitmap.createScaledBitmap(resourceBitmap, canvas.getWidth()+zoomControler, canvas.getHeight()+zoomControler, true);

        int tx = 00, ty = 00;

        canvas.drawBitmap(temp, tx, ty, canvasPaint);

        double xratio = canvas.getWidth()/500.0;
        double yratio = canvas.getHeight()/500.0;
        Log.e("ABHI", xratio + " y" + yratio);

        int width = 7;
        int width1 = 10;
        if(canvas.getHeight() > 500){
            width = 10;
            width1 = 18;
        }

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(width);
        paint.setColor(Color.BLUE);

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
        paint1.setStrokeWidth(2);
        paint1.setColor(Color.parseColor("#006400"));

        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setStrokeWidth(2);
        paint2.setColor(Color.parseColor("#8B0000"));

        Paint paint4 = new Paint();
        paint4.setStyle(Paint.Style.FILL_AND_STROKE);
        paint4.setStrokeWidth(2);
        paint4.setColor(Color.WHITE);

        ArrayList<PathGS> pathList = PathConfig.getInstance().getPathList();
        if(pathList != null && pathList.size() > 0){

            for(int i=0;i<pathList.size();i++){
                PathGS pathGS = pathList.get(i);
                canvas.drawLine((int)(pathGS.getFxcord()*xratio), (int)(pathGS.getFycord()*yratio)
                , (int)(pathGS.getTxcord()*xratio), (int)(pathGS.getTycord()*yratio), paint);
                canvas.drawCircle((int)(pathGS.getFxcord()*xratio), (int)(pathGS.getFycord()*yratio), width1, paint1);
                canvas.drawCircle((int)(pathGS.getTxcord()*xratio), (int)(pathGS.getTycord()*yratio), width1, paint1);
                canvas.drawCircle((int)(pathGS.getFxcord()*xratio), (int)(pathGS.getFycord()*yratio), 5, paint4);
                canvas.drawCircle((int)(pathGS.getTxcord()*xratio), (int)(pathGS.getTycord()*yratio), 5, paint4);
            }
        }

        int source_x = PathConfig.getInstance().xcord;
        int source_y = PathConfig.getInstance().ycord;

        if(source_x != 0 && source_y != 0){
            canvas.drawCircle((int)(source_x*xratio), (int)(source_y*yratio), width1, paint2);
            canvas.drawCircle((int)(source_x*xratio), (int)(source_y*yratio), 5, paint4);
        }

//        ArrayList<PathGS> arrayList = PathConfig.getInstance().getArrayList();
//        if(arrayList != null && arrayList.size() > 0){
//
//            Log.d("ABHI", "ondraw " + arrayList.size());
//            //canvas.drawCircle(100, 100, 10, paint);
//
//            int width = 3;
//            if(canvas.getHeight() > 500){
//                width = 5;
//            }
//
//            for(int j=0; j<arrayList.size(); j++){
//
//                //Log.d("ABHI", arrayList.get(j).getXcord().floatValue()*xratio + " Y " + arrayList.get(j).getYcord().floatValue()*yratio);
//                canvas.drawCircle((int)(arrayList.get(j).getXcord()*xratio),
//                        (int)(arrayList.get(j).getYcord()*yratio), width, paint);
//            }
//        }
//
//        Paint paint1 = new Paint();
//        paint1.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint1.setStrokeWidth(2);
//        paint1.setColor(Color.RED);
//
//        ArrayList<QrGS> qrGS = PathConfig.getInstance().getQrList();
//        if(qrGS != null && qrGS.size() > 0){
//            Log.d("ABHI", "ondraw " + qrGS.size());
//
//            int width = 8;
//            if(canvas.getHeight() > 500){
//                width = 15;
//            }
//
//            for(int j=0; j<qrGS.size(); j++){
//
//                //Log.d("ABHI", arrayList.get(j).getXcord().floatValue()*xratio + " Y " + arrayList.get(j).getYcord().floatValue()*yratio);
//                canvas.drawCircle((int)(qrGS.get(j).getXcord()*xratio),
//                        (int)(qrGS.get(j).getYcord()*yratio), width, paint1);
//            }
//        }

        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    //register user touches as drawing action
//    @Override
////    public boolean onTouchEvent(MotionEvent event) {
////        float touchX = event.getX();
////        float touchY = event.getY();
////        //respond to down, move and up events
////        switch (event.getAction()) {
////            case MotionEvent.ACTION_DOWN:
////                drawPath.moveTo(touchX, touchY);
////                break;
////            case MotionEvent.ACTION_MOVE:
////                drawPath.lineTo(touchX, touchY);
////                break;
////            case MotionEvent.ACTION_UP:
////                drawPath.lineTo(touchX, touchY);
////                drawCanvas.drawPath(drawPath, drawPaint);
////                drawPath.reset();
////                break;
////            default:
////                return false;
////        }
////        //redraw
////        invalidate();
////        return true;
////
////    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode== KeyEvent.KEYCODE_DPAD_UP){
            // zoom in
            zoomControler+=10;
        }
        if(keyCode== KeyEvent.KEYCODE_DPAD_DOWN){
            // zoom out
            zoomControler-=10;
        }
        if(zoomControler<10){
            zoomControler=10;
        }

        invalidate();
        return true;
    }

    //update color
    public void setColor(String newColor){
        invalidate();
        paintColor = Color.parseColor(newColor);
        drawPaint.setColor(paintColor);
    }

    //start new drawing
    public void startNew(){
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        invalidate();
    }
}