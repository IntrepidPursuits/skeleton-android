package io.intrepid.skeleton.smoke;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import io.intrepid.skeleton.screens.demoSelection.DemoSelectionActivity;

public class ExampleSmokeTest {

    @Rule
    public ActivityTestRule<DemoSelectionActivity> activityTestRule = new ActivityTestRule<>(DemoSelectionActivity.class);

    @Before
    public void setUp() {
        activityTestRule.getActivity();
    }

    @Test
    public void smokeTest() {

    }
}
