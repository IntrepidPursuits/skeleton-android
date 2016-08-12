package io.intrepid.skeleton.smoke;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.intrepid.skeleton.R;
import io.intrepid.skeleton.screens.example1.Example1Activity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class ExampleSmokeTest {

    @Rule
    public ActivityTestRule<Example1Activity> activityTestRule = new ActivityTestRule<>(Example1Activity.class);

    @Before
    public void setUp() {
        activityTestRule.getActivity();
    }

    @Test
    public void smokeTest() {
        onView(withId(R.id.example1_button)).perform(click());
        onView(withId(R.id.example2_text)).check(matches(withText("Hello World!")));
    }
}
