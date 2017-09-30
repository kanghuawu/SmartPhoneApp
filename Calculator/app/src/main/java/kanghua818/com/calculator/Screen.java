package kanghua818.com.calculator;

import android.view.Gravity;
import android.widget.TextView;

import static kanghua818.com.calculator.Screen.Action.INITIAL;


/**
 * Created by bondk on 9/29/17.
 */

public class Screen {
    public double value = 0D;
    public Action action = INITIAL;

    private final TextView textView;

    public Screen(final TextView textView) {
        this.textView = textView;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double newValue) {
        if (isOverflow(getAppendedValue(newValue))) {
            showOverflow();
            return;
        }
        this.value = getAppendedValue(newValue);
        this.textView.setText(Math.round(this.value) + "");
    }

    public void clear() {
        this.value = 0D;
        this.textView.setGravity(Gravity.RIGHT);
        this.textView.setText("0");
    }

    private boolean isInitialValue() {
        return this.value == 0D;
    }

    private double getAppendedValue(double newValue) {
        return this.value * 10 + newValue;
    }

    private boolean isOverflow(final double value) {
        if (value > 9999999 || value < -9999999) {
            return true;
        }
        return false;
    }

    private void showOverflow() {
        this.textView.setText("ERROR!");
    }

    public enum Action{
        INITIAL,
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        EQUALS,
        CLEAR
    }
}
