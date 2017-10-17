package com.example.administrator.armymessanger;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.view.*;
import java.io.*;
import java.net.*;
import android.widget.*;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class SearchActivity extends Activity {
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mTextView = (TextView)findViewById(R.id.testText);
        findViewById(R.id.buttonSubmit).setOnClickListener(buttonClick);
    };

    Button.OnClickListener buttonClick = new Button.OnClickListener() {
        public void onClick(View v) {

            EditText etCompanyName = (EditText) findViewById(R.id.companyName);
            EditText etJoinDay = (EditText) findViewById(R.id.joinDay);
            EditText etName = (EditText) findViewById(R.id.name);
            EditText etBirthDay = (EditText) findViewById(R.id.birthday);

            //new GetRequest().execute("http://10.53.128.114:8080/posts");
            //new GetRequest().execute("https://my-rest-api-oscha.c9users.io/posts");
            new PostRequest(etCompanyName.getText().toString(), etJoinDay.getText().toString(), etName.getText().toString(), etBirthDay.getText().toString()).execute("http://10.53.128.114:8080/posts");
            //PostRequest postRequest = new PostRequest(etCompanyName.getText().toString(), etJoinDay.getText().toString(), etName.getText().toString());
            //postRequest.execute("https://my-rest-api-oscha.c9users.io/posts");
        }
    };

    private class GetRequest extends AsyncTask<String, String, String> {
        HttpURLConnection client;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            BufferedInputStream bis = null;
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(args[0]);
                HttpURLConnection    con = (HttpURLConnection) url.openConnection();

                int responseCode;

                con.setConnectTimeout(1500);
                con.setReadTimeout(1500);

                responseCode = con.getResponseCode();
                if (responseCode == 200) {
                    bis = new BufferedInputStream(con.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));

                    String line = null;
                    while ((line = reader.readLine()) != null)
                        sb.append(line);
                    bis.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        protected void onProgressUpdate(String... progress) {

        }

        protected void onPostExecute(String result) {
            mTextView.setText(result);
        }
    }

    private class PostRequest extends AsyncTask<String, String, String> {
        String m_strCompanyName;
        String m_strJoinDay;
        String m_strName;
        String m_strBirthday;

        public PostRequest(String strCompany, String strJoinDay, String strName, String strBirthday){
            m_strCompanyName = strCompany;
            m_strJoinDay = strJoinDay;
            m_strName = strName;
            m_strBirthday = strBirthday;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... args) {
            try {
                URL url = new URL(args[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                con.setRequestProperty("Context_Type", "application/x-www-form-urlencoded");
                con.setRequestMethod("POST");
                con.setDoOutput(true);

                OutputStream os = con.getOutputStream();
                String strData = "CompanyName" + "=" + m_strCompanyName + "&" + "JoinDay=" + m_strJoinDay + "&" + "Name=" + m_strName + "&BirthDay=" + m_strBirthday;
                os.write(strData.getBytes("UTF-8"));
                os.flush();
                os.close();
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                } else {
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onProgressUpdate(String... progress) {

        }

        protected void onPostExecute(String result) {
            mTextView.setText(result);
        }
    }
};
