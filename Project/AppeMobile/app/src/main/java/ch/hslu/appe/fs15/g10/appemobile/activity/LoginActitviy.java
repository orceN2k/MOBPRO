package ch.hslu.appe.fs15.g10.appemobile.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import ch.hslu.appe.fs15.g10.appemobile.Constants;
import ch.hslu.appe.fs15.g10.appemobile.R;
import ch.hslu.appe.fs15.g10.appemobile.dto.RequestSecurityContext;
import ch.hslu.appe.fs15.g10.appemobile.dto.UserInfo;

/**
 * Created by Simon on 25.04.2015.
 */
public class LoginActitviy extends Activity {

    TextView username;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextView) findViewById(R.id.login_username);
        password = (TextView) findViewById(R.id.login_password);
    }

    public void onLoginClicked(View button) {
        UserInfo userInfo = new UserInfo(username.getText().toString(), password.getText().toString());
        callLoginService(userInfo);
    }

    //http://programmerguru.com/android-tutorial/android-restful-webservice-tutorial-how-to-call-restful-webservice-in-android-part-3/
    //http://loopj.com/android-async-http/
    //https://github.com/loopj/android-async-http
    //http://stackoverflow.com/questions/13465523/json-to-pojo-mapping-in-android
    private void callLoginService(final UserInfo userInfo) {
        //Todo extract to service
        // Make RESTful webservice call using AsyncHttpClient object
        final Gson gson = new Gson();
        final AsyncHttpClient client = getHttpClientForAppeServices(userInfo);
        String userInfoJson = gson.toJson(userInfo);
        StringEntity entity = null;
        try {
            entity = new StringEntity(userInfoJson, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(getApplicationContext(), "Could not decode user info check if endcoding is UTF-8", Toast.LENGTH_LONG).show();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));

        String url = Constants.BaseURL + Constants.LoginServiceResourceName;

        client.post(this, url, entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                RequestSecurityContext securityContext = gson.fromJson(response.toString(), RequestSecurityContext.class);
                //TODO safe into preferences and close activity
                BaseActivity.setUserTokenInformationPreferences(securityContext,userInfo, getApplicationContext());
                Toast.makeText(getApplicationContext(),"welcome to appe goes android",Toast.LENGTH_LONG).show();
                Intent showMain = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(showMain);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (statusCode == 401) {
                    //reset credentials.
                    BaseActivity.setUserTokenInformationPreferences(null,null,getApplicationContext());
                    Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private AsyncHttpClient getHttpClientForAppeServices(final UserInfo userInfo){
        final AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth(userInfo.getUser(), userInfo.getPassword());
        return  client;
    }




}
