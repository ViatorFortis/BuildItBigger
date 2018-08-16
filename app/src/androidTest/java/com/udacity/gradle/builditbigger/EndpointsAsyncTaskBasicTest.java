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
            //MainActivity.class, false, false);

//    Incorrect!
//    @Test
//    public void checkTaskResult() throws Exception {
//        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
//        onView(withId(R.id.tell_joke_button) ).perform(click() );
//        //endpointsAsyncTask.execute(activityTestRule.getActivity() );
//        String joke = endpointsAsyncTask.get();
//        Assert.assertTrue(!joke.isEmpty() );
//    }

//    It works!
    @Test
    public void checkTaskResult() throws Exception {
        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
        endpointsAsyncTask.execute(activityTestRule.getActivity() );
        String joke = endpointsAsyncTask.get();
        Assert.assertTrue(!joke.isEmpty() );
    }


//    @Test
//    public void checkResultDisplayed() {
//        onView(withId(R.id.tell_joke_button) ).perform(click() );
//        onView(withId(R.id.tv_joke) ).check(matches(isDisplayed() ) );
//    }
}
