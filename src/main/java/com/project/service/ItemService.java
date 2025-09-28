package com.project.service;
import java.util.List;

import com.project.model.Item;

public interface ItemService {
public boolean addItem(Item item);
public boolean updateItem(Item item);
public Item showItem(Long id);
public List<Item> showItems();
public boolean deleteItem(Long id);	

}
