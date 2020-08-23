package com.iiht.evaluation.coronokit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.coronokit.model.ProductMaster;

public class ProductMasterDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/coronakit";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	private Connection jdbcConnection;

	public ProductMasterDao(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}

	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		}
	}

	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public boolean insertProduct(ProductMaster productMaster) throws SQLException {
		String sql = "insert into product" + "(title,description,price) values" + "(?,?,?);";
		connect();
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, productMaster.getProductName());
		statement.setString(2, productMaster.getProductDescription());
		statement.setFloat(3, productMaster.getCost());

		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;

	}

	public List<ProductMaster> listAllProducts() throws SQLException {
		List<ProductMaster> listProduct = new ArrayList<>();
		String sql = "select * from product;";
		connect();
		Statement statement = jdbcConnection.createStatement();
		PreparedStatement prepareStatement = jdbcConnection.prepareStatement(sql);
		ResultSet resultSet = prepareStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("product_id");
			String productName = resultSet.getString("title");
			String productDescription = resultSet.getString("description");
			float cost = resultSet.getFloat("price");

			ProductMaster productMaster = new ProductMaster(id, productName, cost, productDescription);
			listProduct.add(productMaster);
		}
		resultSet.close();
		statement.close();
		disconnect();
		return listProduct;

	}

	public boolean deleteProduct(ProductMaster productMaster) throws SQLException {
		String sql = "DELETE FROM product where product_id = ?;";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, productMaster.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}

	public boolean updateProduct(ProductMaster productMaster) throws SQLException {
		String sql = "UPDATE product SET title = ?, price = ?, description = ? WHERE product_id = ?;";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, productMaster.getProductName());
		statement.setString(2, productMaster.getProductDescription());
		statement.setFloat(3, productMaster.getCost());
		statement.setInt(4, productMaster.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;
	}

	public ProductMaster getProduct(int id) throws SQLException {
		ProductMaster productMaster = null;
		String sql = "SELECT * FROM product WHERE product_id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			String productName = resultSet.getString("title");
			String productDescription = resultSet.getString("description");
			float cost = resultSet.getFloat("price");

			productMaster = new ProductMaster(productName, cost, productDescription);
		}

		resultSet.close();
		statement.close();

		return productMaster;
	}
}