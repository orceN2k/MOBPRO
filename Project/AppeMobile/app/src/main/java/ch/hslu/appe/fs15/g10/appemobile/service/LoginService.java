package ch.hslu.appe.fs15.g10.appemobile.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import ch.hslu.appe.fs15.g10.appemobile.dto.RequestSecurityContext;
import ch.hslu.appe.fs15.g10.appemobile.dto.UserInfo;


/**
 * Created by Simon on 25.04.2015.
 */
public class LoginService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public RequestSecurityContext login(UserInfo userInfo) {


        return null;
    }
}
