package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskBasicTest {
    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void checkTaskResult() throws Exception {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(activityTestRule.getActivity() );
        String joke = endpointsAsyncTask.get();
        Assert.assertTrue(!joke.isEmpty() );
    }
}
