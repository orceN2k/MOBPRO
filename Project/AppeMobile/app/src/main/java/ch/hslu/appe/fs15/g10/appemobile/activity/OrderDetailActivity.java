package ch.hslu.appe.fs15.g10.appemobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ch.hslu.appe.fs15.g10.appemobile.Constants;
import ch.hslu.appe.fs15.g10.appemobile.R;
import ch.hslu.appe.fs15.g10.appemobile.dto.OrderDto;
import ch.hslu.appe.fs15.g10.appemobile.dto.OrderItemDto;
import ch.hslu.appe.fs15.g10.appemobile.service.AppeService;

public class OrderDetailActivity extends BaseActivity implements AppeService.IOrderServiceCallback{


    private ListView positionList;
    private TextView header;
    private EditText deliveryDate;
    private EditText totalAmount;
    private int selectedOrderId;

    OrderDto selectedOrderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        deliveryDate = (EditText) findViewById(R.id.order_detail_delivery_date);
        header = (TextView) findViewById(R.id.order_detail_header);
        totalAmount = (EditText) findViewById(R.id.order_detail_total);
        positionList = (ListView) findViewById(R.id.order_detail_listposistion);
    }

    private void setSelectedOrderItem(){
        header.setText(String.format( "Order %d",selectedOrderItem.getId()));
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        totalAmount.setText(String.valueOf(selectedOrderItem.getTotalAmount()));
        deliveryDate.setText(formatter.format(selectedOrderItem.getDeliveryDate()));
    }

    @Override
    protected void onServiceReady() {
        super.onServiceReady();
        Intent intent = getIntent();
        selectedOrderId = getIntent().getIntExtra(Constants.IntentParamNames.OrderIdParamName,0);
        loadOrder();
    }

    private void loadOrder() {
        mService.getOrder(getUserInfoFromPreferences(),this,selectedOrderId);
    }


    @Override
    public void onGetOrdersCallback(boolean successful, List<OrderDto> orderDtos, int statusCode) {

    }

    @Override
    public void onGetOrderCallback(boolean successful, OrderDto orderDto, int statusCode) {

        if(successful){
            selectedOrderItem = orderDto;
            ArrayAdapter<OrderItemDto> adapter = new ArrayAdapter<OrderItemDto>(getApplicationContext(), android.R.layout.simple_list_item_1, selectedOrderItem.getOrderItems()) {
                @Override
                public View getView(int position, View convertView,
                                    ViewGroup parent) {
                    View view = super.getView(position, convertView, parent);
                    TextView textView = (TextView) view.findViewById(android.R.id.text1);
                    textView.setTextColor(R.color.list_item_font);
                    return view;
                }
            };
            positionList.setAdapter(adapter);
            setSelectedOrderItem();
        }
        else {
            showExceptionToast(statusCode);
        }
    }

    @Override
    public void onUpdateOrderCallback(boolean successful, OrderDto updatedDto, int statusCode) {

    }
}