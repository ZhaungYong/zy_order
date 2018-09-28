package cn.zy.controller;

import cn.zy.pojo.Order;
import cn.zy.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("order/{id}")
    public Order getOrder(@PathVariable("id") String id) {
        return orderService.getOrderById(id);
    }
}
