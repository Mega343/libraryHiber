package com.nixsolutions.dao;

import com.nixsolutions.bean.Shelf;

import java.util.List;

public interface ShelfDAO {

    public boolean add(Shelf shelf);

    public boolean edit(Shelf shelf);

    public boolean delete(Integer id);

    public Shelf getShelf(Integer id);

    public List<Shelf> getAllShelves();
}
