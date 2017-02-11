package com.nixsolutions.util;

public class OrderReturnDateUtil {

    public Long setExpectedReturnDate(Integer orderTypeID) {
        Long timeMonth = System.currentTimeMillis() + 2592000000L;
        Long timeDay = System.currentTimeMillis() + 86400000L;
        if (orderTypeID.equals(1)) {
            return timeMonth;
        } else if (orderTypeID.equals(2)) {
            return timeMonth;
        } else {
            return timeDay;
        }
    }
}
