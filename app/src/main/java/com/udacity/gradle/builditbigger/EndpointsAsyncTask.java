package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import com.viatorfortis.displayjokelib.JokeActivity;
import com.viatorfortis.givemejokelib.Jokes;

import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    private static MyApi myApiService = null;
    private Context context;

    private TaskListener mTaskListener;

    public interface TaskListener {
        void onResult(String joke);

        void onError(String errorMessage);
    }

    public void setTaskListener(TaskListener taskListener) {
        mTaskListener = taskListener;
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(),
                    new HttpRequestInitializer() {
                        public void initialize(HttpRequest httpRequest) {
                            httpRequest.setConnectTimeout(20 * 1000);
                            httpRequest.setReadTimeout(10 * 1000);
                        }
                    }
                    //null
            )
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];
        String joke;

        try {
            joke = myApiService.getJoke().execute().getData();

        } catch (IOException e) {
            String errorMessage = e.getMessage();

            if (mTaskListener != null) {
                mTaskListener.onError(errorMessage);
            }

            return errorMessage;
        }

        if (mTaskListener != null) {
            mTaskListener.onResult(joke);
        }

        return joke;
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, JokeActivity.class);
        intent.putExtra(context.getString(com.udacity.gradle.builditbigger.R.string.joke_key), Jokes.giveMeJoke() );

        context.startActivity(intent);
    }
}
