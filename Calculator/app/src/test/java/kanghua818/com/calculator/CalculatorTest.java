package kanghua818.com.calculator;

import android.view.Gravity;
import android.widget.TextView;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Created by bondk on 10/1/17.
 */

public class CalculatorTest {

    private Calculator cal;

    /**
     * 1+2=3
     */
    @Test
    public void testCase1() {
        actAppendDigitAndVerify(1D, "1");
        actOperateAndVerify(Calculator.Action.ADD, "1");
        actAppendDigitAndVerify(2D, "2");
        actOperateAndVerify(Calculator.Action.EQUAL, "3");
    }

    /**
     * 222*30-5=6655
     */
    @Test
    public void testCase2() {
        actAppendDigitAndVerify(2D, "2");
        actAppendDigitAndVerify(2D, "22");
        actAppendDigitAndVerify(2D, "222");
        actOperateAndVerify(Calculator.Action.MULTIPLY, "222");
        actAppendDigitAndVerify(3D, "3");
        actAppendDigitAndVerify(0D, "30");
        actOperateAndVerify(Calculator.Action.SUBTRACT, "6660");
        actAppendDigitAndVerify(5D, "5");
        actOperateAndVerify(Calculator.Action.EQUAL, "6655");
    }

    /**
     * 2/5=0 CLEAR
     * 8/9=1 CLEAR
     * -5/2=-3 CLEAR
     */
    @Test
    public void testCase3() {
        actAppendDigitAndVerify(2D, "2");
        actOperateAndVerify(Calculator.Action.DIVIDE, "2");
        actAppendDigitAndVerify(5D, "5");
        actOperateAndVerify(Calculator.Action.EQUAL, "0");
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");

        actAppendDigitAndVerify(8D, "8");
        actOperateAndVerify(Calculator.Action.DIVIDE, "8");
        actAppendDigitAndVerify(9D, "9");
        actOperateAndVerify(Calculator.Action.EQUAL, "1");
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");

        actOperateAndVerify(Calculator.Action.SUBTRACT, "0");
        actAppendDigitAndVerify(5D, "-5");
        actOperateAndVerify(Calculator.Action.DIVIDE, "-5");
        actAppendDigitAndVerify(2D, "2");
        actOperateAndVerify(Calculator.Action.EQUAL, "-3");
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");
    }

    /**
     * * / + - 5+1=6
     */
    @Test
    public void testCase4() {
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");
        actOperateAndVerify(Calculator.Action.MULTIPLY, "0");
        actOperateAndVerify(Calculator.Action.DIVIDE, "0");
        actOperateAndVerify(Calculator.Action.ADD, "0");
        actOperateAndVerify(Calculator.Action.SUBTRACT, "0");
        actAppendDigitAndVerify(5D, "-5");
        actOperateAndVerify(Calculator.Action.ADD, "-5");
        actAppendDigitAndVerify(1D, "1");
        actOperateAndVerify(Calculator.Action.EQUAL, "-4");
    }

    /**
     * 5+= ERROR! CLEAR
     * 5*= ERROR! CLEAR
     * *5= ERROR! CLEAR
     * +5= ERROR! CLEAR
     */
    @Test
    public void testCase5() {
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");

        actAppendDigitAndVerify(5D, "5");
        actOperateAndVerify(Calculator.Action.ADD, "5");
        actOperateAndVerify(Calculator.Action.EQUAL, "ERROR!");
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");

        actAppendDigitAndVerify(5D, "5");
        actOperateAndVerify(Calculator.Action.MULTIPLY, "5");
        actOperateAndVerify(Calculator.Action.EQUAL, "ERROR!");
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");

        actOperateAndVerify(Calculator.Action.MULTIPLY, "0");
        actAppendDigitAndVerify(5D, "5");
        actOperateAndVerify(Calculator.Action.EQUAL, "ERROR!");
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");

        actOperateAndVerify(Calculator.Action.ADD, "0");
        actAppendDigitAndVerify(5D, "5");
        actOperateAndVerify(Calculator.Action.EQUAL, "ERROR!");
        actOperateAndVerify(Calculator.Action.INITIAL_OR_CLEAR, "0");
    }


    @Before
    public void setUp() {
        final Screen screen = new Screen(Mockito.mock(TextView.class)) {
            private String text = null;
            @Override
            public void setValue(double newValue) {
                text = customizedRound(newValue);
            }

            @Override
            public String getDisplayText() {
                return text;
            }

            @Override
            public void clear() {
                text = "0";
            }

            @Override
            public void showError() {
                text = "ERROR!";
            }
        };
        cal = new Calculator(screen);
    }

    private void actOperateAndVerify(final Calculator.Action action, final String expected) {
        cal.act(action, null);
        Assert.assertEquals(expected, cal.getDisplayText());
    }

    private void actAppendDigitAndVerify(double inputValue, String expected) {
        cal.act(Calculator.Action.APPEND_DIGIT, inputValue);
        Assert.assertEquals(expected, cal.getDisplayText());
    }
}
