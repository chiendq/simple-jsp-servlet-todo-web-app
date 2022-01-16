package com.chiendq.dao;

import com.chiendq.entities.Item;

import java.util.List;

public interface ITaskDAO {
    void create(Item item);
    void deleteById(int id);
    void updateDescription(int id, String newDescription);
    void updateStatus(int id, int status);
    List<Item> findAll();
    Item findById(int id);
}
