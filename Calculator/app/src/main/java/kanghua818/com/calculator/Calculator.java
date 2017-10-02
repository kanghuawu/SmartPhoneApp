package kanghua818.com.calculator;


import static kanghua818.com.calculator.Calculator.Action.*;

/**
 * Simple calculator including add, subtract, multiply, and divide.
 *
 * Created by bondk on 9/30/17.
 */
public class Calculator {

    private static final Double ZERO = 0D;
    private static final Double INITIAL_VALUE = null;
    private static final Double CUSTOMIZED_MAX_VALUE = 9999999D;

    private Double result = INITIAL_VALUE;
    private Double current = INITIAL_VALUE;
    private Action operation = INITIAL_OR_CLEAR;

    private Screen screen;

    public Calculator(final Screen screen) {
        this.screen = screen;
    }

    public void act(final Action newAction, final Double inputValue) {
        clearMarquee();
        if (isNegating(newAction)) {
            act(NEGATE, null);
            return;
        }
        switch (newAction) {
            case ADD:
            case SUBTRACT:
            case MULTIPLY:
            case DIVIDE:
                if (isClearState()) {
                    return;
                }
                if (result == INITIAL_VALUE) {
                    pushCurrentToResult();
                } else if (current == INITIAL_VALUE ) {
                    // DO NOTHING
                } else { // current is not initial and result is not initial
                    operate();
                }
                current = INITIAL_VALUE;
                this.operation = newAction;
                break;
            case EQUAL:
                if (current == INITIAL_VALUE || result == INITIAL_VALUE) {
                    err();
                    return;
                }
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

    private void pushCurrentToResult() {
        result = current;
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

    private boolean isOverflow(final Double value) {
        if (value == INITIAL_VALUE) {
            return false;
        }
        if (value > CUSTOMIZED_MAX_VALUE || value < -CUSTOMIZED_MAX_VALUE) {
            return true;
        }
        return false;
    }

    private Double getAppendedValue(Double newValue) {
        if (current == INITIAL_VALUE) {
            return new Double(newValue);
        } else {
            return new Double(this.current * 10D + newValue);
        }
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
