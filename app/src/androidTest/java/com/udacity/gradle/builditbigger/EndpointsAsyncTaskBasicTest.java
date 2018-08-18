package com.udacity.gradle.builditbigger;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskBasicTest {
    private static final String TAG = "EndpointsAsyncTaskBasicTest";

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void checkTaskResult() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        try {
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
            endpointsAsyncTask.setTaskListener(new EndpointsAsyncTask.TaskListener() {
                @Override
                public void onResult(String joke) {
                    Assert.assertTrue(joke.length() > 0);
                    countDownLatch.countDown();
                }

                @Override
                public void onError(String errorMessage) {
                    Assert.assertFalse(errorMessage.length() > 0);
                    countDownLatch.countDown();
                }
            });

            endpointsAsyncTask.execute(activityTestRule.getActivity());
            countDownLatch.await();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage() );
        }
    }
}
