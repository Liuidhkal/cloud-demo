package cn.itcast.order.service;


import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private UserClient userClient;//feign远程调用
//    @Resource
//    private RestTemplate restTemplate;

    //用openFeign实现远程调用
    public Order queryOrderById(Long orderId){
        //1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.用Feign实现远程调用
        User user = userClient.findById(order.getUserId());
        //3.存入order
        order.setUser(user);
        //4.返回order
        return order;
    }


    //RestTemplate的远程调用
/*    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //2.远程调用
        String url = "http://userservice/user/"+order.getUserId();
        User user = restTemplate.getForObject(url, User.class);
        //3.存入order
        order.setUser(user);
        // 4.返回
        return order;
    }*/
}
