package de.hhu.a08unittest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // A naïve approach
    public static int multiplyBySeven(int number) {
        return number * 7;
    }

    // Fortified!
    public static int multiplyBySevenSafe(int number) throws OverflowException {
        int maximum_allowed = Integer.MAX_VALUE / 7;
        int minimum_allowed = Integer.MIN_VALUE / 7;

        // Check range
        if (number > maximum_allowed) {
            throw new OverflowException(OverflowException.OverflowDirection.POSITIVE, number);
        } else if (number < minimum_allowed) {
            throw new OverflowException(OverflowException.OverflowDirection.NEGATIVE, number);
        } else {
            // Yay, we can do it!
            return number * 7;
        }
    }

    // This one's private!
    private static int signum(int number) {
        if (number == 0) {
            return 0;
        } else if (number < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    public static class OverflowException extends Exception {
        public enum OverflowDirection {
            POSITIVE,
            NEGATIVE
        }

        private OverflowDirection mDirection;
        private int mCausingNumber;

        public OverflowException(OverflowDirection direction, int number) {
            super();
            this.mDirection = direction;
            this.mCausingNumber = number;
        }

        @Override
        public String getMessage() {
            String result = "Number " + Integer.toString(mCausingNumber) + " causes a ";
            switch (mDirection) {
                case POSITIVE:
                    return result + "positive overflow";
                case NEGATIVE:
                    return result + "negative overflow";
                default:
                    return result + "strange overflow of invalid direction";
            }
        }

        // Useful for the test
        public OverflowDirection getDirection() {
            return mDirection;
        }

        public int getCausingNumber() {
            return mCausingNumber;
        }
    }
}
