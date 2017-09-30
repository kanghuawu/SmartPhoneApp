package kanghua818.com.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        final Calculator calculator = new Calculator(new Screen(textView));

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
        setDigitalButtonHandler(mOneButton, calculator, 1);
        setDigitalButtonHandler(mTwoButton, calculator, 2);
        setDigitalButtonHandler(mThreeButton, calculator, 3);
        setDigitalButtonHandler(mFourButton, calculator, 4);
        setDigitalButtonHandler(mFiveButton, calculator, 5);
        setDigitalButtonHandler(mSixButton, calculator, 6);
        setDigitalButtonHandler(mSevenButton, calculator, 7);
        setDigitalButtonHandler(mEightButton, calculator, 8);
        setDigitalButtonHandler(mNineButton, calculator, 9);
        setDigitalButtonHandler(mZeroButton, calculator, 0);

        mClearButton = (Button) findViewById(R.id.clearButton);
        mClearButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    calculator.act(Calculator.Action.INITIAL_OR_CLEAR, 0);
                }
                return true;
            }
        });

        mPlusButton = (Button) findViewById(R.id.addButton);
        setOperandButtonHandler(mPlusButton, calculator, Calculator.Action.ADD);

        mMinusButton = (Button) findViewById(R.id.subtractButton);
        setOperandButtonHandler(mMinusButton, calculator, Calculator.Action.SUBTRACT);

        mMultiplyButton = (Button) findViewById(R.id.multiplyBbutton);
        setOperandButtonHandler(mMultiplyButton, calculator, Calculator.Action.MULTIPLY);

        mDivideButton = (Button) findViewById(R.id.divideButton);
        setOperandButtonHandler(mDivideButton, calculator, Calculator.Action.DIVIDE);

        mEqualButton = (Button) findViewById(R.id.equalButton);
        setOperandButtonHandler(mEqualButton, calculator, Calculator.Action.EQUAL);
    }

    private void setOperandButtonHandler(final Button button, final Calculator calculator, final Calculator.Action action) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    button.setBackgroundColor(0xFF00FF00);
                    MainActivity.this.clearMarquee();
                    textView.setGravity(Gravity.RIGHT);
                    return true;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    button.setBackgroundResource(R.drawable.num_button);
                    clearMarquee();
                    textView.setGravity(Gravity.RIGHT);
                    calculator.act(action, 0);
                    return false;
                }
                return false;
            }
        });
    }

    private void setDigitalButtonHandler(final Button button, final Calculator calculator, final double buttonValue) {

//        button.setOnClickListener();

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
                    calculator.act(Calculator.Action.APPEND_DIGIT, buttonValue);
//                    screen.setValue(buttonValue);
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
