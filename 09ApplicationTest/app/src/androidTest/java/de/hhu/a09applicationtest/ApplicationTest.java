package de.hhu.a09applicationtest;

import android.app.Application;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.PositionAssertions.isBelow;
import static android.support.test.espresso.assertion.PositionAssertions.isLeftAlignedWith;
import static android.support.test.espresso.assertion.PositionAssertions.isRightOf;
import static android.support.test.espresso.assertion.PositionAssertions.isTopAlignedWith;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testJumpingButton() {
        // the jumping button and the comparison TextView
        ViewInteraction buttonInteraction = onView(withId(R.id.jumping_button));
        Matcher<View> comparisonView = withId(R.id.comparison_view);

        // jump to top-left corner
        buttonInteraction.perform(click());
        buttonInteraction.check(isTopAlignedWith(comparisonView));

        // jump to top-right corner
        buttonInteraction.perform(click());
        buttonInteraction.check(isRightOf(comparisonView));

        // jump to bottom edge
        buttonInteraction.perform(click());
        buttonInteraction.check(isBelow(comparisonView));

        // jump back to the center
        buttonInteraction.perform(click());
        buttonInteraction.check(isBelow(comparisonView));

        // jump to the top-left corner again
        buttonInteraction.perform(click());
        buttonInteraction.check(isLeftAlignedWith(comparisonView));
    }
}