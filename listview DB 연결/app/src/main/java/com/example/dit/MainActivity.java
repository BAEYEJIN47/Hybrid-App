package com.example.dit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView reportListView;
    private ReportListAdapter adapter;
    private List<Report> reportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reportListView = (ListView)findViewById(R.id.reportListView);
        reportList = new ArrayList<Report>();
        adapter = new ReportListAdapter(getApplicationContext(), reportList);
        reportListView.setAdapter(adapter);

        new BackgroundTask().execute();
    }

class BackgroundTask extends AsyncTask<Void, Void, String> {
    String target;

    protected void onPreExecute() {
        target = "http://dit.dothome.co.kr/dit.php";
    }

    protected String doInBackground(Void... voids) {
        try {
            URL url = new URL(target);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String temp;
            StringBuilder stringBuilder = new StringBuilder();
            while ((temp = bufferedReader.readLine()) != null) {
                stringBuilder.append(temp + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onProgressUpdate(Void... values) {
        super.onProgressUpdate();
    }

    public void onPostExecute(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray jsonArray = jsonObject.getJSONArray("response");
            int count = 0;
            String reportContent, reportName, reportDate;
            while (count < jsonArray.length()) {
                JSONObject object = jsonArray.getJSONObject(count);
                reportContent = object.getString("reportContent");
                reportName = object.getString("reportName");
                reportDate = object.getString("reportDate");

                Report report = new Report(reportContent, reportName, reportDate);

                reportList.add(report);
                count++;
            }

            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
}