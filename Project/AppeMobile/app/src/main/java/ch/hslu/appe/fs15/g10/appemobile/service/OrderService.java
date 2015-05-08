package ch.hslu.appe.fs15.g10.appemobile.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.net.ServerSocket;
import java.util.List;


import ch.hslu.appe.fs15.g10.appemobile.dto.OrderDto;
import ch.hslu.appe.fs15.g10.appemobile.dto.RequestSecurityContext;


/**
 * Created by Simon on 25.04.2015.
 */
public class OrderService extends Service  {

    public List<OrderDto> getOrders(RequestSecurityContext requestSecurityContext) {
        return null;
    }


    public OrderDto getOrder(RequestSecurityContext requestSecurityContext, int i) {
        return null;
    }


    public OrderDto createOrder(RequestSecurityContext requestSecurityContext, OrderDto orderDto) {
        return null;
    }


    public OrderDto updateOrder(RequestSecurityContext requestSecurityContext, int i, OrderDto orderDto)  {
        return null;
    }


    public void removeOrder(RequestSecurityContext requestSecurityContext, int i) {

    }


    public IBinder onBind(Intent intent) {
        return null;
    }
}
