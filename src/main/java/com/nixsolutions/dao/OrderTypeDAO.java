package com.nixsolutions.dao;


import com.nixsolutions.bean.OrderType;

import java.util.List;

public interface OrderTypeDAO {

    public boolean add(OrderType orderType);

    public boolean edit(OrderType orderType);

    public boolean delete(Integer id);

    public OrderType getOrderType(Integer id);

    public List<OrderType> getAllOrderTypes();
}
