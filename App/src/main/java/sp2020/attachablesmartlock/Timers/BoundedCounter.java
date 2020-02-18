package sp2020.attachablesmartlock.Timers;

public class BoundedCounter {
    private int value;
    private int upperBound;

    public BoundedCounter(int startBound) {
        value = 0;
        upperBound = startBound;
    }

    public void increase() {
        this.value++;
        if (this.value > upperBound) {
            this.value=0;
        }
    }

    public void decrease() {
        this.value--;
        if (this.value < 0) {
            this.value=upperBound;
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
        if (newValue <= upperBound) {
            if (newValue < 0) {
                newValue = 0;
            }

            this.value = newValue;
        }
    }
}
