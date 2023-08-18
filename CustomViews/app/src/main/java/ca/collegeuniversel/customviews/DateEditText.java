package ca.collegeuniversel.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class DateEditText extends LinearLayout {

    public DateEditText(Context context) {
        super(context);
        init(context);
    }
    public DateEditText(Context context, AttributeSet attrs) {
        super(context);
        init(context);
        applyAttributes(context, attrs);
    }

    private void init(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.date_edit_text, this, true);

        // Initialize components and set up listeners if needed
        // For example, find views by id and set hints
    }


    private void applyAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DateEditText);

        // Read attributes and apply to components
        // For example, read label, hints, and text color attributes

        a.recycle();
    }
}
