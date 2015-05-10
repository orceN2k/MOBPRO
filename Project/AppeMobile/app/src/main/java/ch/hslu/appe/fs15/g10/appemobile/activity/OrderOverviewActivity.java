package ch.hslu.appe.fs15.g10.appemobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.List;

import ch.hslu.appe.fs15.g10.appemobile.Constants;
import ch.hslu.appe.fs15.g10.appemobile.R;
import ch.hslu.appe.fs15.g10.appemobile.dto.OrderDto;
import ch.hslu.appe.fs15.g10.appemobile.dto.UserInfo;
import ch.hslu.appe.fs15.g10.appemobile.service.AppeService;

/**
 * Created by Simon on 07.05.2015.
 */
public class OrderOverviewActivity extends BaseActivity implements AppeService.IOrderServiceCallback {

    Button cancelation;
    Button details;
    Button newOrder;
    ListView ordersList;
    OrderDto selectedOrderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_overview);
        cancelation = (Button) findViewById(R.id.order_overview_cancel);
        cancelation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    cancelOrder(selectedOrderItem);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        details = (Button) findViewById(R.id.order_overview_details);
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOrderDetails();
            }
        });
        newOrder = (Button) findViewById(R.id.order_overview_new);
        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNotImplemented();
            }
        });
        ordersList = (ListView) findViewById(R.id.order_overview_listorders);

        ordersList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                view.setSelected(true);
                toggleButtons(true);
                selectedOrderItem = (OrderDto) ordersList.getItemAtPosition(position);

            }

        });

    }

    private void toggleButtons(boolean toggle) {
        details.setEnabled(toggle);
        cancelation.setEnabled(toggle);
    }

    @Override
    protected void onResume() {
        super.onResume();
        toggleButtons(false);

    }

    @Override
    protected void onServiceReady() {
        super.onServiceReady();
        loadOrderItems();
    }

    private void cancelOrder(OrderDto orderDto) throws UnsupportedEncodingException {

        // canceled
        orderDto.setStatus(2);

        UserInfo userInfo = getUserInfoFromPreferences();
        mService.updateOrder(userInfo, this, orderDto);

    }

    private void loadOrderItems() {
        UserInfo userInfo = getUserInfoFromPreferences();
        mService.getOrders(userInfo, this);
    }

    private void showOrderDetails() {
        Intent showOrderDetails = new Intent(this, OrderDetailActivity.class);
        if (selectedOrderItem != null) {
            showOrderDetails.putExtra(Constants.IntentParamNames.OrderIdParamName, selectedOrderItem.getId());
            startActivity(showOrderDetails);
        }
    }

    @Override
    public void onGetOrdersCallback(boolean successful, List<OrderDto> orderDtos, int statusCode) {

        if (successful) {
            ArrayAdapter<OrderDto> adapter = new ArrayAdapter<OrderDto>(getApplicationContext(), android.R.layout.simple_list_item_1, orderDtos) {
                @Override
                public View getView(int position, View convertView,
                                    ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    textView.setTextColor(R.color.list_item_font);
                    return view;
                }
            };
            ordersList.setAdapter(adapter);
        }
        else {
            showExceptionToast(statusCode);
        }
    }

    @Override
    public void onGetOrderCallback(boolean successful, OrderDto orderDto, int statusCode) {

    }

    @Override
    public void onUpdateOrderCallback(boolean successful, OrderDto updatedDto, int statusCode) {
        if (successful) {
            loadOrderItems();
        }
        else {
            showExceptionToast(statusCode);
        }
    }
}
