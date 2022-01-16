package com.chiendq.dao.impl;

import com.chiendq.dao.ITaskDAO;
import com.chiendq.entities.Item;
import com.chiendq.utils.JDBCConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDAOImpl implements ITaskDAO {
	public void create(Item item) {
        String SQLCreate = "INSERT INTO task(description) VALUES(?)";
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLCreate);
            preparedStatement.setString(1, item.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCConnection.closeConnection();
        }
	}
	
	public void deleteById(int id) {
        String SQLDelete = "DELETE FROM task WHERE id = ?";
		Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLDelete);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCConnection.closeConnection();
        }
	}
	
	public void updateDescription(int id, String newDescription) {
        String SQLUpdate = "UPDATE task SET description=?, status = ? WHERE id=?";
        Connection connection = JDBCConnection.getConnection();
        System.out.println(newDescription);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLUpdate);
            preparedStatement.setString(1, newDescription);
            preparedStatement.setInt(2,findById(id).getStatus());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCConnection.closeConnection();
        }
	}

    public void updateStatus(int id, int status) {
        String SQLUpdate = "UPDATE task SET description=?, status = ? WHERE id=?";
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQLUpdate);
            preparedStatement.setString(1, findById(id).getDescription());
            preparedStatement.setInt(2, status);
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCConnection.closeConnection();
        }
    }
	
	public List<Item> findAll(){
		String SQLFindAll = "SELECT * FROM task ORDER BY status";
		Connection connection = JDBCConnection.getConnection();
        List<Item> itemList = new ArrayList<>();
        try {
            PreparedStatement pst = connection.prepareStatement(SQLFindAll);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()){
                Item i = new Item();
                i.setDescription(resultSet.getString("description"));
                i.setStatus(resultSet.getInt("status"));
                i.setId(resultSet.getInt("id"));
                itemList.add(i);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCConnection.closeConnection();
        }
        return itemList;
    }

    public Item findById(int id){
        String SQLFind = "SELECT * FROM task WHERE id = ?";
        Connection connection = JDBCConnection.getConnection();
        try {
            PreparedStatement pst = connection.prepareStatement(SQLFind);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()){
                return new Item(resultSet.getString("description"), resultSet.getInt("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCConnection.closeConnection();
        }
        return null;
    }
}
