/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.bit_by_bit.navio.ui.ui.scanner;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.Log;

import com.google.android.gms.vision.barcode.Barcode;

import in.bit_by_bit.navio.ui.ui.camera.GraphicOverlay;

import static java.lang.Math.PI;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Graphic instance for rendering barcode position, size, and ID within an associated graphic
 * overlay view.
 */
public class BarcodeGraphic extends GraphicOverlay.Graphic {

    private int mId;

    private static final int COLOR_CHOICES[] = {
            Color.BLUE,
            Color.CYAN,
            Color.GREEN
    };

    private static int mCurrentColorIndex = 0;

    private Paint mRectPaint;
    private Paint mTextPaint;
    private volatile Barcode mBarcode;

    BarcodeGraphic(GraphicOverlay overlay) {
        super(overlay);

        mCurrentColorIndex = (mCurrentColorIndex + 1) % COLOR_CHOICES.length;
        final int selectedColor = COLOR_CHOICES[mCurrentColorIndex];

        mRectPaint = new Paint();
        mRectPaint.setColor(selectedColor);
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(4.0f);

        mTextPaint = new Paint();
        mTextPaint.setColor(selectedColor);
        mTextPaint.setTextSize(36.0f);
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public Barcode getBarcode() {
        return mBarcode;
    }

    /**
     * Updates the barcode instance from the detection of the most recent frame.  Invalidates the
     * relevant portions of the overlay to trigger a redraw.
     */
    void updateItem(Barcode barcode) {
//        if(mBarcode != null && barcode != null){
//            if(!mBarcode.rawValue.equals(barcode.rawValue)){
//                FetchPathWorker.getInstance(this.).fetchPathDetails(123);
//            }
//        }
        mBarcode = barcode;
        Log.e("ABHI", barcode.rawValue + " Raw value");
        postInvalidate();
    }

    /**
     * Draws the barcode annotations for position, size, and raw value on the supplied canvas.
     */
    @Override
    public void draw(Canvas canvas) {
        Barcode barcode = mBarcode;
        if (barcode == null) {
            return;
        }

        // Draws the bounding box around the barcode.
        RectF rect = new RectF(barcode.getBoundingBox());
        rect.left = translateX(rect.left);
        rect.top = translateY(rect.top);
        rect.right = translateX(rect.right);
        rect.bottom = translateY(rect.bottom);
        canvas.drawRect(rect, mRectPaint);
        Log.d("ABHI", "ondraw called " + rect.left);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
        paint.setColor(Color.GREEN);


//        drawArrow(paint, canvas, (canvas.getWidth()/2)-100, (canvas.getHeight()/2)-100
//        ,(canvas.getWidth()/2)+100, (canvas.getHeight()/2)+100);
//        Path path = new Path();
//        path.moveTo((canvas.getWidth()/2)-50, (canvas.getHeight()/2)+50);
//        path.lineTo((canvas.getWidth()/2), (canvas.getHeight()/2)-25);
//        path.lineTo((canvas.getWidth()/2)+50, (canvas.getHeight()/2)+50);
//        path.close();
//        //path.offset(10, 40);
//        canvas.drawPath(path, paint);
//        canvas.drawRect((canvas.getWidth()/2)-25, (canvas.getHeight()/2)+50,
//                (canvas.getWidth()/2)+25, (canvas.getHeight()/2)+150, paint);
//
//        // Draws a label at the bottom of the barcode indicate the barcode value that was detected.
//        Paint paint2 = new Paint();
//        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
//        paint2.setStrokeWidth(2);
//        Paint.FontMetrics fm = new Paint.FontMetrics();
//        paint2.setColor(Color.RED);
//        paint2.getFontMetrics(fm);
//        paint2.setTextSize(35);
//        int margin = 5;
//        canvas.drawRect(rect.left - margin, rect.bottom + fm.top - margin,
//                50 + paint2.measureText(barcode.rawValue) + margin, 50 + fm.bottom
//                        + margin, paint2);

        //paint2.setColor(Color.RED);
//        Log.d("ABHI", "Ondraw called " +rect.left + " " + rect.bottom);
//        canvas.drawRect(100 - margin, 0, 150, 150, paint);
//        RectF rect1 = new RectF(barcode.getBoundingBox());
//        rect1.left = translateX(rect1.left - margin);
//        rect1.top = translateY(rect1.bottom + fm.top - margin);
//        rect1.right = translateX(rect1.left + 35*barcode.rawValue.length() + margin);
//        rect1.bottom = translateY(rect1.bottom + fm.bottom + margin);
//
//        canvas.drawRect(rect1, paint);
        //canvas.drawText(PathConfig.getInstance().getItemNameToShow(), rect.left, rect.bottom + 40, paint2);

        //canvas.drawText(barcode.rawValue, rect.left, rect.bottom, mTextPaint);
    }

    private void drawArrow(Paint paint, Canvas canvas, float from_x, float from_y, float to_x, float to_y)
    {
        float angle,anglerad, radius, lineangle;

        //values to change for other appearance *CHANGE THESE FOR OTHER SIZE ARROWHEADS*
        radius=10;
        angle=15;

        //some angle calculations
        anglerad= (float) (PI*angle/180.0f);
        lineangle= (float) (atan2(to_y-from_y,to_x-from_x));

        //tha line
        canvas.drawLine(from_x,from_y,to_x,to_y,paint);

        //tha triangle
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(to_x, to_y);
        path.lineTo((float)(to_x-radius*cos(lineangle - (anglerad / 2.0))),
                (float)(to_y-radius*sin(lineangle - (anglerad / 2.0))));
        path.lineTo((float)(to_x-radius*cos(lineangle + (anglerad / 2.0))),
                (float)(to_y-radius*sin(lineangle + (anglerad / 2.0))));
        path.close();

        canvas.drawPath(path, paint);
    }
}
