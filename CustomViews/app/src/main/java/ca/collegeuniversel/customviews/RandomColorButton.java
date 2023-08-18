package ca.collegeuniversel.customviews;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.material.button.MaterialButton;

import java.util.Random;

public class RandomColorButton extends MaterialButton {
    public RandomColorButton(@NonNull Context context) {
        super(context);
        init();
    }

    public RandomColorButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate random background color
                int randomColor = Color.rgb(
                        new Random().nextInt(256),
                        new Random().nextInt(256),
                        new Random().nextInt(256)
                );

                setBackgroundColor(randomColor);

                // Calculate and set text color based on luminance
                float[] hsv = new float[3];
                Color.colorToHSV(randomColor, hsv);
                int textColor = (Color.luminance(hsv) <= 0.35) ? Color.WHITE : Color.BLACK;
                setTextColor(textColor);

                // Call the base implementation of performClick
                performClick();
            }
        });
    }

}
