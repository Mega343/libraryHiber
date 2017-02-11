package com.nixsolutions.bean;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "order_type", uniqueConstraints={@UniqueConstraint(columnNames={"order_type"})})
public class OrderType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_type_id")
    private Integer orderTypeID;
    @NotNull
    @Column(name = "order_type")
    private String orderType;

    public OrderType() {
    }

    public OrderType( String orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderTypeID() {
        return orderTypeID;
    }

    public void setOrderTypeID(Integer orderTypeID) {
        this.orderTypeID = orderTypeID;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public String toString() {
        return "OrderType{" +
                "orderTypeID=" + orderTypeID +
                ", orderType='" + orderType + '\'' +
                '}';
    }
}
