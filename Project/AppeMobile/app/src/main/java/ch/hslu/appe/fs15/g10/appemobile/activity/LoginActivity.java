package ch.hslu.appe.fs15.g10.appemobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import ch.hslu.appe.fs15.g10.appemobile.R;
import ch.hslu.appe.fs15.g10.appemobile.dto.RequestSecurityContext;
import ch.hslu.appe.fs15.g10.appemobile.dto.UserInfo;
import ch.hslu.appe.fs15.g10.appemobile.service.AppeService;

/**
 * Created by Simon on 25.04.2015.
 */
public class LoginActivity extends BindingActivity {

    private TextView username;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = (TextView) findViewById(R.id.login_username);
        password = (TextView) findViewById(R.id.login_password);
    }

    public void onLoginClicked(View button) throws InterruptedException {
        final UserInfo userInfo = new UserInfo(username.getText().toString(), password.getText().toString());

        final AppeService.ILoginCallback iLoginCallback = new AppeService.ILoginCallback() {

            @Override
            public void loginDone(boolean successFull, RequestSecurityContext context, int statusCode) {

                if (successFull) {
                    BaseActivity.setUserTokenInformationPreferences(context, userInfo, getApplicationContext());
                    Toast.makeText(getApplicationContext(), "welcome to appe goes android", Toast.LENGTH_SHORT).show();
                    Intent showMain = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(showMain);
                } else {
                    BaseActivity.setUserTokenInformationPreferences(null, null, getApplicationContext());
                    if (statusCode == 401) {
                        Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        mService.login(userInfo, iLoginCallback);
    }

}
