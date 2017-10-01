package kanghua818.com.calculator;

import static kanghua818.com.calculator.Calculator.Action.*;

/**
 * Simple calculator including add, subtract, multiply, and divide.
 *
 * Created by bondk on 9/30/17.
 */
public class Calculator {

    private static final double INITIAL_VALUE = 0D;
    private static final double ZERO = 0D;
    private static final double CUSTOMIZED_MAX_VALUE = 9999999;

    private double result = INITIAL_VALUE;
    private double current = INITIAL_VALUE;
    private Action operation = INITIAL_OR_CLEAR;

    private Screen screen;

    public Calculator(final Screen screen) {
        this.screen = screen;
    }

    public void act(final Action newAction, double inputValue) {
        clearMarquee();
        if (isNegating(newAction)) {
            act(NEGATE, 0);
            return;
        }
        switch (newAction) {
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDE:
                if (result == INITIAL_VALUE || newAction == INITIAL_OR_CLEAR) {
                    result = current;
                } else {
                    operate();

                }
                current = INITIAL_VALUE;
                this.operation = newAction;
                break;
            case EQUAL:
                operate();
                current = INITIAL_VALUE;
                this.operation = newAction;
                break;
            case NEGATE:
                this.operation = newAction;
                break;
            case INITIAL_OR_CLEAR:
                screen.clear();
                clear();
                break;
            case APPEND_DIGIT:
                if (isOverflow(getAppendedValue(inputValue))) {
                    err();
                    return;
                } else {
                    if (this.operation == EQUAL) {
                        clear();
                    }
                    if (this.operation == NEGATE) {
                        current = -getAppendedValue(inputValue);
                    } else {
                        current = getAppendedValue(inputValue);
                    }
                    screen.setValue(current);
                }
                break;
            default:
                break;
        }
    }

    public void clearMarquee() {
        this.screen.clearMarquee();
    }

    private void err() {
        screen.showError();
        clear();
    }

    private void operate() {
        switch (operation) {
            case ADD:
                result += current;
                break;
            case SUBTRACT:
                result -= current;
                break;
            case MULTIPLY:
                result *= current;
                break;
            case DIVIDE:
                if (current == ZERO) {
                    err();
                    return;
                }
                result /= current;
                break;
        }
        operation = INITIAL_OR_CLEAR;
        screen.setValue(result);
    }

    private void clear() {
        current = INITIAL_VALUE;
        result = INITIAL_VALUE;
        operation = INITIAL_OR_CLEAR;
    }

    private boolean isClearState() {
        return current == INITIAL_VALUE && result == INITIAL_VALUE && operation == INITIAL_OR_CLEAR;
    }

    private boolean isNegating(final Action action) {
        if (action == SUBTRACT && isClearState()) {
            return true;
        }
        return false;
    }

    private boolean isOverflow(final double value) {
        if (value > CUSTOMIZED_MAX_VALUE || value < -CUSTOMIZED_MAX_VALUE) {
            return true;
        }
        return false;
    }

    private double getAppendedValue(double newValue) {
        return this.current * 10 + newValue;
    }

    /**
     * Actions of buttons on Calculator
     */
    public enum Action{
        INITIAL_OR_CLEAR,
        APPEND_DIGIT,
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        EQUAL,
        NEGATE
    }
}
