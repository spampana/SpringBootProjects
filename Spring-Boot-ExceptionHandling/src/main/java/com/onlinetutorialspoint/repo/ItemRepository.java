package com.onlinetutorialspoint.repo;

import com.onlinetutorialspoint.exception.ItemNotFoundException;
import com.onlinetutorialspoint.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {

    @Autowired
    JdbcTemplate template;

    /*Getting all Items from table*/
    public List<Item> getAllItems(){
        List<Item> items = template.query("select id, name,category from item",(result,rowNum)->new Item(result.getInt("id"),
                result.getString("name"),result.getString("category")));
        return items;
    }
    /*Getting a specific item by item id from table*/
    public Item getItem(int itemId){
        Item item = null;
        String query = "SELECT * FROM ITEM WHERE ID=?";
        try{
            item = template.queryForObject(query,new Object[]{itemId},new BeanPropertyRowMapper<>(Item.class));
        }catch(Exception e){
            throw new ItemNotFoundException("Item Not Found : "+itemId);
        }
        return item;
    }
    /*Adding an item into database table*/
    public int addItem(Item item){
        String query = "INSERT INTO ITEM VALUES(?,?,?)";
        return template.update(query,new Object[] {
                Integer.valueOf(item.getId()),item.getName(),item.getCategory()
        });
    }
    /*delete an item from database*/
    public int deleteItem(int id){
        String query = "DELETE FROM ITEM WHERE ID =?";
        int size = template.update(query,id);
        if(size == 0){
            throw new ItemNotFoundException("No Item Found to delete: "+id);
        }
        return size;
    }

    /*update an item from database*/
    public void updateItem(Item item){
        String query = "UPDATE ITEM SET name=?, category=? WHERE id =?";
        template.update(query,
                new Object[] {
                        item.getName(),item.getCategory(), Integer.valueOf(item.getId())
                });
    }
}
