package com.nixsolutions.dao;

import com.nixsolutions.bean.PublishingHouse;

import java.util.List;

public interface PublishingHouseDAO {

    public boolean add(PublishingHouse ph);

    public boolean edit(PublishingHouse ph);

    public boolean delete(Integer id);

    public PublishingHouse getPublishingHouse(Integer id);

    public List<PublishingHouse> getPublishingHouseByName(String name);

    public List<PublishingHouse> getAllPublishingHouses();
}
