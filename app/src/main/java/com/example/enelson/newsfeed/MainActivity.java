package com.example.enelson.newsfeed;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class MainActivity extends AppCompatActivity {

    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/search?q=debates&api-key=FILLMEIN";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NewsAsyncTask task = new NewsAsyncTask();
        task.execute();
    }

    /**
     * Update the page to show information for {@link Event}
     */
    private void updateUi(Event news){

        TextView titleTextView = (TextView) findViewById(R.id.title);
        titleTextView.setText(news.title);
    }

    /**
     * {@link AsyncTask} to perform the network request on a background thread.
     * The UI will then update with the first news story
     */

    private class NewsAsyncTask extends AsyncTask<URL, Void, Event> {

        @Override
        protected Event doInBackground(URL... urls){

            URL url = createUrl(GUARDIAN_REQUEST_URL);

            String jsonResponse = "";
            try {
                jsonResponse = makeHttpRequest(url);
            } catch (IOException e){
                Log.e("Log","Http request error", e);
            }

            Event news = extractFeatureFromJson(jsonResponse);

            return news;
        }

        /**
         * Update page with news
         */
        @Override
        protected void onPostExecute(Event news){
            if (news == null){
                return;
            }

            updateUi(news);
        }

        /**
         * Create new URL object from input string
         */
        private URL createUrl(String urlString){
            URL url = null;
            try {
                url = new URL(urlString);
            } catch (MalformedURLException exception){
                Log.e("Log","URL Error", exception);
                return null;
            }
            return url;
        }

        /**
         * Call the HTTP request and return response string
         */

        private String makeHttpRequest(URL url) throws IOException {
            String jsonResponse = "";

            if(url == null){
                return jsonResponse;
            }

            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;

            try {
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();

                if (urlConnection.getResponseCode() == 200) {
                    inputStream = urlConnection.getInputStream();
                    jsonResponse = readFromStream(inputStream);
                } else {
                    Log.e("Log", "Response" + urlConnection.getResponseCode());
                }
            } catch (IOException e){
                Log.e("Log","Could not receive results",e);
            } finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
                if(inputStream != null){
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        /**
         * Convert the {@link InputStream} to a String
         */
        private String readFromStream(InputStream inputStream) throws IOException{
            StringBuilder output = new StringBuilder();
            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null) {
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return output.toString();
        }

        /**
         * Return {@link Event} after parsing out JSON news info
         */

        private Event extractFeatureFromJson(String newsJSON){
            if (TextUtils.isEmpty(newsJSON)) {
                return null;
            }

            try {
                JSONObject baseJsonResponse = new JSONObject(newsJSON);
                JSONObject baseJsonResult = baseJsonResponse.getJSONObject("reponse");

                if (baseJsonResponse.length() > 0){
                    JSONArray featureArray = baseJsonResult.getJSONArray("results");
                    JSONObject properties = featureArray.getJSONObject(0);

                    String title = properties.getString("id");

                    return new Event(title);
                }
            } catch (JSONException e){
                Log.e("Log", "Error parsing news", e);
            }
            return null;
        }
    }
}
