package ch.hslu.appe.fs15.g10.appemobile.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.google.gson.Gson;

import ch.hslu.appe.fs15.g10.appemobile.Constants;
import ch.hslu.appe.fs15.g10.appemobile.dto.RequestSecurityContext;
import ch.hslu.appe.fs15.g10.appemobile.dto.UserInfo;

/**
 * Created by Simon on 04.05.2015.
 */
class BaseActivity extends Activity {

    @Override
    protected void onResume() {
        super.onResume();
        //every view has to check the credentials on startup!
        if (!hasLoginCredentials() && !this.getClass().getSimpleName().equals("LoginActitviy")) {
            showLoginActivity();
        }
    }

    private boolean hasLoginCredentials() {
        RequestSecurityContext requestSecurityContext = getRequestSecurityContext();
        if(requestSecurityContext == null){
            return  false;
        }
        //call service for login
//        else if()

        return true;
    }

    protected void showLoginActivity() {
        Intent showLogin = new Intent(this, LoginActitviy.class);
        startActivity(showLogin);
    }

    protected void showOrderOverview(){
        Intent showLogin = new Intent(this, OrderOverviewActivity.class);
        startActivity(showLogin);
    }

    protected void showNotImplemented(){
        Toast.makeText(getApplicationContext(), "Not Implemented", Toast.LENGTH_LONG).show();
    }

    public static void setUserTokenInformationPreferences(RequestSecurityContext securityContext,UserInfo userInfo, Context context){

        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        final Gson gson = new Gson();
        final SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constants.SecurityTokenPrefrencesName, gson.toJson(securityContext));
        editor.putString(Constants.UserInfoPreferencesName,gson.toJson(userInfo));
        editor.commit();
    }


    protected RequestSecurityContext getRequestSecurityContext() {
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final Gson gson = new Gson();
        return gson.fromJson(preferences.getString(Constants.SecurityTokenPrefrencesName, ""), RequestSecurityContext.class);
    }

    protected UserInfo getUserInfoFromPreferences(){
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final Gson gson = new Gson();
        return gson.fromJson(preferences.getString(Constants.UserInfoPreferencesName, ""), UserInfo.class);
    }

}
