package cn.zy.service;

import cn.zy.pojo.Item;
import cn.zy.pojo.Order;
import cn.zy.pojo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    private static final Map<String, Order> MAP = new HashMap<String, Order>();

    static {
        // 构造测试数据
        Order order = new Order();
        order.setOrderId("59193738268961441");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

        Item item = new Item();// 此处并没有商品的数据，需要调用商品微服务获取
        item.setId(1L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        item = new Item(); // 构造第二个商品数据
        item.setId(2L);
        orderDetails.add(new OrderDetail(order.getOrderId(), item));

        order.setOrderDetails(orderDetails);

        MAP.put(order.getOrderId(), order);
    }

    @Autowired
    private ItemService itemService;

    public Order getOrderById(String id) {
        Order order = MAP.get(id);
        if (order == null) {
            return null;
        }
        List<OrderDetail> orderDetails = order.getOrderDetails();
        for (OrderDetail orderDetail : orderDetails) {
            Item item = orderDetail.getItem();
            Long itemId = item.getId();
            Item dbItem = itemService.getItemById(itemId + "");
            if (dbItem == null) {
                continue;
            }
            orderDetail.setItem(dbItem);
        }
        return order;
    }
}
