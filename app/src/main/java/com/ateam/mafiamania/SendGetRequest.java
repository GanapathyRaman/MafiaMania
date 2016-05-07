package com.ateam.mafiamania;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Security;


public class SendGetRequest {
    private static String username; /* So that it can be accessed by other class */
    private String urlPrefix;
    private Context context;

    public void setUrlPrefix(String urlPrefix) { this.urlPrefix = urlPrefix; }

    public void setUsername(String username) { this.username = username; }

    public void setContext(Context context) { this.context = context; }

    public String getUsername() { return this.username; }

    public SendGetRequest(Context context, String urlPrefix, String username) {
        setUsername(username);
        setUrlPrefix(urlPrefix);
        setContext(context);
    }

    public SendGetRequest() {
        /* Overloaded Constructor */
    }

    public void createGetRequestAndSend() {
        String urlSuffix = "?username=" + username;
        System.out.println("+++++++++++ " + urlSuffix);

         class LoginOrRegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(context, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_LONG).show();
                if(s.contentEquals("Login Successful")) {
                    Intent intent = new Intent(context.getApplicationContext(), Temp.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startActivity(intent);
                    ((Activity) context).overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                }
            }

            @Override
            protected String doInBackground(String... params) {
                String suffix = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(urlPrefix + suffix);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    // optional default is GET
                    con.setRequestMethod("GET");

                    //add request header
                    //private final String USER_AGENT = "Mozilla/5.0";
                    //con.setRequestProperty("User-Agent", USER_AGENT);


                    int responseCode = con.getResponseCode();
                    System.out.println("\nSending 'GET' request to URL : " + url);
                    System.out.println("Response Code : " + responseCode);

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    //print result
                    System.out.println(response.toString());
                    System.out.println("****************" + responseCode);

                    /* On Success return the response */
                    return response.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                    return "Error While sending the GET/POST request";
                }
            }
        }
        LoginOrRegisterUser loru = new LoginOrRegisterUser();
        loru.execute(urlSuffix);
    }
}