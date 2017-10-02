package kanghua818.com.calculator;

import android.view.Gravity;
import android.widget.TextView;


/**
 * Calculator screen displaying current processing number
 *
 * Created by bondk on 9/29/17.
 */

public class Screen {
    private static final String ERROR = "ERROR!";
    public double value = 0D;
    private final TextView textView;

    public Screen(final TextView textView) {
        this.textView = textView;
    }

    public void setValue(double newValue) {
        this.textView.setText(customizedRound(newValue));
    }

    String customizedRound(final double value) {
        return ((value<0) ? "-" : "") + Math.round(Math.abs(value));
    }

    public void clear() {
        this.value = 0D;
        this.textView.setGravity(Gravity.RIGHT);
        this.textView.setText("0");
    }

    public void showError() {
        this.textView.setText(ERROR);
    }

    public void clearMarquee() {
        this.textView.setEllipsize(null);
        this.textView.setGravity(Gravity.RIGHT);
    }

    public String getDisplayText() {
        return (textView != null) ? (String) textView.getText() : null;
    }

}
