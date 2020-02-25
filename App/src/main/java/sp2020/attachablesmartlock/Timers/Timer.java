package sp2020.attachablesmartlock.Timers;

/**
 * This class is for creating timer objects.
 * Timers track how long a user's friend has access to their lock.
 * A timer consists of 3 counters, for hours minutes and seconds.
 */
public class Timer {
    private BoundedCounter hours;
    private BoundedCounter minutes;
    private BoundedCounter seconds;
   
    public Timer(int hoursAtBeginning, int minutesAtBeginning, int secondsAtBeginning) {

        // 3 counters are created, for hours minutes and seconds.
        this.hours = new BoundedCounter(23);
        this.minutes = new BoundedCounter(59);
        this.seconds = new BoundedCounter(59);

        hours.setValue(hoursAtBeginning);
        minutes.setValue(minutesAtBeginning);
        seconds.setValue(secondsAtBeginning);
    }

    public void tickDown() {
        // Timer goes down by one second
        this.seconds.decrease();
        if(this.seconds.getValue() == 59) {
            this.minutes.decrease();
            if(this.minutes.getValue() == 59) {
                this.hours.decrease();
            }
        }
    }

    public int getTimeInSeconds() {
        return this.seconds.getValue() + this.minutes.getValue()*60 + this.hours.getValue()*60*60;
    }

    public void setTime (int newHours, int newMinutes, int newSeconds) {
        this.hours.setValue(newHours);
        this.minutes.setValue(newMinutes);
        this.seconds.setValue(newSeconds);
    }

    public void clear() {
        this.setTime(0,0,0);
    }

    public String toString() {
        // returns the string representation
        return this.hours + ":" + this.minutes + ":" + this.seconds;
    }
}
