package kanghua818.com.calculator;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
    Button mOneButton, mTwoButton, mThreeButton, mFourButton, mFiveButton, mSixButton, mSevenButton,
            mEightButton, mNineButton, mZeroButton, mClearButton, mPlusButton, mMinusButton,
            mMultiplyButton, mDivideButton, mEqualButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) this.findViewById(R.id.screen);
        textView.setSelected(true);

        final Screen screen = new Screen(textView);

        mOneButton = (Button) findViewById(R.id.oneButton);
        mTwoButton = (Button) findViewById(R.id.twoButton);
        mThreeButton = (Button) findViewById(R.id.threeButton);
        mFourButton = (Button) findViewById(R.id.fourButton);
        mFiveButton = (Button) findViewById(R.id.fiveButton);
        mSixButton = (Button) findViewById(R.id.sixButton);
        mSevenButton = (Button) findViewById(R.id.sevenButton);
        mEightButton = (Button) findViewById(R.id.eightButton);
        mNineButton = (Button) findViewById(R.id.nineButton);
        mZeroButton = (Button) findViewById(R.id.zeroButton);
        setDigitalButtonHandler(mOneButton, screen, 1);
        setDigitalButtonHandler(mTwoButton, screen, 2);
        setDigitalButtonHandler(mThreeButton, screen, 3);
        setDigitalButtonHandler(mFourButton, screen, 4);
        setDigitalButtonHandler(mFiveButton, screen, 5);
        setDigitalButtonHandler(mSixButton, screen, 6);
        setDigitalButtonHandler(mSevenButton, screen, 7);
        setDigitalButtonHandler(mEightButton, screen, 8);
        setDigitalButtonHandler(mNineButton, screen, 9);
        setDigitalButtonHandler(mZeroButton, screen, 0);

        mClearButton = (Button) findViewById(R.id.clearButton);
        mClearButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    screen.clear();
                }
                return true;
            }
        });

        mPlusButton = (Button) findViewById(R.id.addButton);
        mMinusButton = (Button) findViewById(R.id.subtractButton);
        mMultiplyButton = (Button) findViewById(R.id.multiplyBbutton);
        mDivideButton = (Button) findViewById(R.id.divideButton);
    }

    private void setDigitalButtonHandler(final Button button, final Screen screen, final double buttonValue) {

        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    button.setBackgroundColor(0xFF00FF00);
//                    button.setBackgroundResource(R.drawable.num_button);
                    MainActivity.this.clearMarquee();
                    textView.setGravity(Gravity.RIGHT);
                    return true;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    button.setBackgroundResource(R.drawable.num_button);
                    clearMarquee();
                    textView.setGravity(Gravity.RIGHT);
//                    textView.setText("123");
                    screen.setValue(buttonValue);
                    return false;
                }
                return false;
            }
        });
    }

    private void clearMarquee() {
        textView.setEllipsize(null);
    }
}
