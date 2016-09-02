package io.intrepid.skeleton.smoke;

import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.intrepid.skeleton.InstrumentationTestApplication;
import io.intrepid.skeleton.R;
import io.intrepid.skeleton.rest.TestRestClient;
import io.intrepid.skeleton.rules.MockServerRule;
import io.intrepid.skeleton.screens.example1.Example1Activity;
import io.intrepid.skeleton.testutils.BaseUiTest;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@SmallTest
public class ExampleSmokeTest extends BaseUiTest {

    @Rule
    public MockServerRule mockServerRule = new MockServerRule();
    @Rule
    public ActivityTestRule<Example1Activity> activityTestRule = new ActivityTestRule<>(Example1Activity.class, true, false);

    @Before
    public void setUp() {
        InstrumentationTestApplication.overrideResApi(TestRestClient.getRestApi(mockServerRule));
    }

    @Test
    public void smokeTest() {
        activityTestRule.launchActivity(null);
        mockServerRule.enqueueResponse(io.intrepid.skeleton.debug.test.R.raw.mock_ip);
        onView(withId(R.id.example1_button)).perform(click());
        onView(withId(R.id.example2_text)).check(matches(withText("Your Ip address is 127.0.0.1")));
    }
}
