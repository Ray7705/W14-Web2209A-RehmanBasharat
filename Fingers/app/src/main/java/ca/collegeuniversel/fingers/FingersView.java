package ca.collegeuniversel.fingers;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

public class FingersView extends ConstraintLayout {

    private static final int MAX_FINGERS = 5;
    private Paint[] circlePaints;
    private Paint textPaint;
    private int fingersCount;
    private final int[] position = new int[2];
    private MotionEvent event;



    public FingersView(Context context) {
        super(context);
        inflate(context, R.layout.fingers_view,this);
    }

    public FingersView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.fingers_view,this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_POINTER_DOWN:
                updateView(event);
                // Return true to indicate event was handled.
                return true;
        }

        // Return false to indicate event was not handled.
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (event == null){
            return;
        }

        int action = event.getActionMasked();
        boolean pointerWasRemoved = action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP;
        int currentPointerCount = pointerWasRemoved ? event.getPointerCount() - 1 : event.getPointerCount();
        if (currentPointerCount > 0) {
            getLocationInWindow(position);



        }
    }


    private void updateFingersCount(int fingersCount) {

        TextView fingersCountTextView = findViewById(R.id.textView_number_of_fingers);

        if (fingersCount == 0) {
            fingersCountTextView.setText(getResources().getString(R.string.no_fingers));
        } else {
            String fingersText = getResources().getQuantityString(
                    R.plurals.number_of_fingers, fingersCount, fingersCount);
            fingersCountTextView.setText(fingersText);
        }
    }


    private void updateView(MotionEvent event) {
        int fingersCount = event.getPointerCount();
        updateFingersCount(fingersCount);
        invalidate();
    }
}
