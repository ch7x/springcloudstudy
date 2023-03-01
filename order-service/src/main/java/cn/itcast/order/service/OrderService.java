package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {
        //查询订单
        Order order = orderMapper.findById(orderId);
        //远程调用(用Feign)
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        //返回
        return order;
    }

   /* @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        //查询订单
        Order order = orderMapper.findById(orderId);
        //远程调用(用restTemplate)
        String url = "http://userservice/user/" + order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);
        //返回
        return order;
    }*/
}
