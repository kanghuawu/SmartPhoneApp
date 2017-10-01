package kanghua818.com.calculator;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import kanghua818.com.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.screen.setSelected(true);

        final Calculator calculator = new Calculator(new Screen(binding.screen));

        setDigitalButtonHandler(binding.oneButton, calculator, 1);
        setDigitalButtonHandler(binding.twoButton, calculator, 2);
        setDigitalButtonHandler(binding.threeButton, calculator, 3);
        setDigitalButtonHandler(binding.fourButton, calculator, 4);
        setDigitalButtonHandler(binding.fiveButton, calculator, 5);
        setDigitalButtonHandler(binding.sixButton, calculator, 6);
        setDigitalButtonHandler(binding.sevenButton, calculator, 7);
        setDigitalButtonHandler(binding.eightButton, calculator, 8);
        setDigitalButtonHandler(binding.nineButton, calculator, 9);
        setDigitalButtonHandler(binding.zeroButton, calculator, 0);

        setOperandButtonHandler(binding.addButton, calculator, Calculator.Action.ADD);
        setOperandButtonHandler(binding.subtractButton, calculator, Calculator.Action.SUBTRACT);
        setOperandButtonHandler(binding.multiplyBbutton, calculator, Calculator.Action.MULTIPLY);
        setOperandButtonHandler(binding.divideButton, calculator, Calculator.Action.DIVIDE);
        setOperandButtonHandler(binding.equalButton, calculator, Calculator.Action.EQUAL);

        binding.clearButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    binding.clearButton.setPressed(true);
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    binding.clearButton.setPressed(false);
                    calculator.act(Calculator.Action.INITIAL_OR_CLEAR, 0);
                }
                return true;
            }
        });
    }

    private void setOperandButtonHandler(final Button button, final Calculator calculator, final Calculator.Action action) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    button.setPressed(true);
                    return true;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    button.setPressed(false);
                    calculator.act(action, 0);
                    return false;
                }
                return true;
            }
        });
    }

    private void setDigitalButtonHandler(final Button button, final Calculator calculator, final double buttonValue) {
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    button.setPressed(true);
                    return true;
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    button.setPressed(false);
                    calculator.act(Calculator.Action.APPEND_DIGIT, buttonValue);
                    return false;
                }
                return true;
            }
        });
    }

}
