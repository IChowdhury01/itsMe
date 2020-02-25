package sp2020.attachablesmartlock.Timers;

/**
 * This a class for creating counter objects.
 * The counter starts with value 0.
 * The counter's bound is the max value it can have.
 * When it goes over the bound, it resets to 0.
 */

public class BoundedCounter {
    private int value;
    private int bound;

    public BoundedCounter(int Bound) {
        value = 0;
        bound = Bound;
    }

    public void increase() {
        this.value++;
        if (this.value > bound) {
            this.value=0;
        }
    }

    public void decrease() {
        this.value--;
        if (this.value < 0) {
            this.value = bound;
        }
    }


    public String toString() {
        if(this.value < 10) {
            return "0" + Integer.toString(this.value);
        }
        else {
            return Integer.toString(this.value);
        }
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int newValue) {
        if (newValue < 0 || newValue > bound) {
            newValue = 0;
        }
        this.value = newValue;
    }
}
