package com.nixsolutions.dao;


import com.nixsolutions.bean.Order;

import java.util.List;

public interface OrderDAO {

    public boolean add(Order order);

    public boolean edit(Order order);

    public boolean delete(Long id);

    public Order getOrder(Long id);

    public List<Order> getAllOrders();

}
