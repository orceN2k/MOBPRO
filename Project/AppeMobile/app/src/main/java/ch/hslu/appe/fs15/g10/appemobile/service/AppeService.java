package ch.hslu.appe.fs15.g10.appemobile.service;

import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestHandle;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import ch.hslu.appe.fs15.g10.appemobile.Constants;
import ch.hslu.appe.fs15.g10.appemobile.R;
import ch.hslu.appe.fs15.g10.appemobile.dto.OrderDto;
import ch.hslu.appe.fs15.g10.appemobile.dto.OrderItemDto;
import ch.hslu.appe.fs15.g10.appemobile.dto.RequestSecurityContext;
import ch.hslu.appe.fs15.g10.appemobile.dto.UserInfo;

/**
 * Created by Simon on 04.05.2015.
 */
public class AppeService extends Service {

    //Bound Service
    //http://developer.android.com/guide/components/bound-services.html

    //Sources for Rest Service calls
    //http://programmerguru.com/android-tutorial/android-restful-webservice-tutorial-how-to-call-restful-webservice-in-android-part-3/
    //http://loopj.com/android-async-http/
    //https://github.com/loopj/android-async-http

    //POJO mapping
    //http://stackoverflow.com/questions/13465523/json-to-pojo-mapping-in-android

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
//    private Gson gson;

    private UserInfo userInfo;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private Gson getGson() {
        // because of java.util.date we need to handle it in our own serializer
        // http://stackoverflow.com/questions/6873020/gson-date-format
        JsonSerializer<Date> ser = new JsonSerializer<Date>() {
            @Override
            public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext
                    context) {
                return src == null ? null : new JsonPrimitive(src.getTime());
            }
        };

        JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
            @Override
            public Date deserialize(JsonElement json, Type typeOfT,
                                    JsonDeserializationContext context) throws JsonParseException {
                return json == null ? null : new Date(json.getAsLong());
            }
        };
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, ser)
                .registerTypeAdapter(Date.class, deser).create();

        return gson;
    }

    private StringEntity getStringEntity(Object dto) {
        String userInfoJson = getGson().toJson(dto);
        StringEntity entity = null;
        try {
            entity = new StringEntity(userInfoJson, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(getApplicationContext(), "Could not decode user info check if endcoding is UTF-8", Toast.LENGTH_LONG).show();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, Constants.Communication.ContentTypeJSON));
        return entity;
    }

    private AsyncHttpClient getHttpClientForAppeServices(final UserInfo userInfo) {
        final AsyncHttpClient client = new AsyncHttpClient();
        client.setBasicAuth(userInfo.getUser(), userInfo.getPassword());
        return client;
    }

    public interface ILoginCallback {
        void loginDone(boolean successFull, RequestSecurityContext context, int statusCode);
    }

    public void login(final UserInfo userInfo, final ILoginCallback callback) throws InterruptedException {
        if (userInfo == null || callback == null) {
            return;
        }

        final AsyncHttpClient client = getHttpClientForAppeServices(userInfo);
        StringEntity entity = getStringEntity(userInfo);
        String url = Constants.BaseURL + Constants.LoginServiceResourceName;
        client.post(this, url, entity, Constants.Communication.ContentTypeJSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                final RequestSecurityContext securityContext = getGson().fromJson(response.toString(), RequestSecurityContext.class);
                callback.loginDone(true, securityContext, 200);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                callback.loginDone(false, null, statusCode);
            }
        });
    }

    public interface IOrderServiceCallback {

        void onGetOrdersCallback(boolean successful, List<OrderDto> orderDtos, int statusCode);

        void onGetOrderCallback(boolean successful, OrderDto orderDto, int statusCode);

        void onUpdateOrderCallback(boolean successful, OrderDto updatedDto, int statusCode);
    }

    public void getOrders(UserInfo userInfo, final IOrderServiceCallback callback) {
        if (userInfo == null || callback == null) {
            return;
        }
        final AsyncHttpClient client = getHttpClientForAppeServices(userInfo);
        String url = Constants.BaseURL + Constants.OrderServiceResourceName;

        client.get(this, url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    String orderList = new String(responseBody, "UTF-8");
                    ArrayList<OrderDto> orderDtos = getGson().fromJson(orderList, new TypeToken<List<OrderDto>>() {
                    }.getType());
                    Iterator<OrderDto> iterator = orderDtos.iterator();
                    while (iterator.hasNext()) {
                        if (iterator.next().getStatus() != 0) {
                            iterator.remove();
                        }
                    }
                    callback.onGetOrdersCallback(true, orderDtos, 200);


                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onGetOrdersCallback(false, null, statusCode);
            }
        });

    }

    public void updateOrder(UserInfo userInfo, final IOrderServiceCallback callback, OrderDto orderDto) {
        if (userInfo == null || callback == null || orderDto == null) {
            return;
        }

        final AsyncHttpClient client = getHttpClientForAppeServices(userInfo);
        final StringEntity entity = getStringEntity(orderDto);
        String url = Constants.BaseURL + Constants.OrderServiceResourceName + "/" + orderDto.getId();
        client.put(this, url, entity, Constants.Communication.ContentTypeJSON, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                OrderDto updated = getGson().fromJson(response.toString(), OrderDto.class);
                callback.onUpdateOrderCallback(true, updated, 200);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                callback.onUpdateOrderCallback(false, null, statusCode);
            }

        });
    }

    public void getOrder(UserInfo userInfo, final IOrderServiceCallback callback, int orderId) {
        if (userInfo == null || callback == null) {
            return;
        }
        final AsyncHttpClient client = getHttpClientForAppeServices(userInfo);
        String url = Constants.BaseURL + Constants.OrderServiceResourceName + "/" + orderId;
        client.get(this, url, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    String orderList = new String(responseBody, "UTF-8");
                    OrderDto orderDto = getGson().fromJson(orderList, OrderDto.class);
                    callback.onGetOrderCallback(true, orderDto, 200);

                } catch (UnsupportedEncodingException e) {
                    callback.onUpdateOrderCallback(false, null, statusCode);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                callback.onUpdateOrderCallback(false, null, statusCode);
            }
        });
    }

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        public AppeService getService() {
            // Return this instance of LocalService so clients can call public methods
            return AppeService.this;
        }
    }


}
