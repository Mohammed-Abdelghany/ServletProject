package com.item.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import com.item.model.Item;

public class ItemServiceImp implements ItemService {
	private DataSource dataSource;

	public ItemServiceImp(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addItem(Item item) {
		String insert_q = "INSERT INTO items (name, price, total_number) VALUES (?, ?, ?)";
		try (Connection con = dataSource.getConnection();
				PreparedStatement insert_st = con.prepareStatement(insert_q);) {
			insert_st.setString(1, item.getName());
			insert_st.setDouble(2, item.getPrice());
			insert_st.setInt(3, item.getTotal_number());
			int rows = insert_st.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateItem(Item item) {
		String update_q = "UPDATE items SET price = ?, name = ?, total_number = ? WHERE id = ?";
		try (Connection con = dataSource.getConnection();
				PreparedStatement update_st = con.prepareStatement(update_q);) {
			update_st.setDouble(1, item.getPrice());
			update_st.setString(2, item.getName());
			update_st.setInt(3, item.getTotal_number());
			update_st.setLong(4, item.getId());

			int rows = update_st.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Item showItem(Long id) {
		String q = "SELECT * FROM items WHERE id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(q)) {
			st.setLong(1, id);
			try (ResultSet rs = st.executeQuery()) {
				if (rs.next()) {
					return new Item(rs.getLong("id"), rs.getInt("total_number"), rs.getDouble("price"),
							rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Item> showItems() {
		List<Item> items = new ArrayList<>();
		String query = "SELECT * FROM items";
		try {
			Connection connection = dataSource.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String name = resultSet.getString("name");
				int totalNumber = resultSet.getInt("total_number");
				double price = resultSet.getDouble("price");
				items.add(new Item(id, totalNumber, price, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	@Override
	public boolean deleteItem(Long id) {
		String q = "DELETE FROM items WHERE id=?";
		try (Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(q)) {
			st.setLong(1, id);
			int rows = st.executeUpdate();
		if(rows>0)
			return rows>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
