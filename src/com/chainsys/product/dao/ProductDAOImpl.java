package com.chainsys.product.dao;

import java.util.List;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.chainsys.product.model.Product;

public class ProductDAOImpl implements ProductDAO {

	private static Connection con;
	private static PreparedStatement pstmt;
	private static ResultSet rs;
	private static Set<Product> productSet;
	private static ArrayList<String> namelist;
	private static ArrayList<Integer> idlist;
	private static ArrayList<Date> datelist;
	

	public ProductDAOImpl() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
//			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "root", "password");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.20:1521:EBS1228", "apps", "apps");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Set<Product> findAll() {
		try {
			pstmt = con.prepareStatement("select * from product_2590");
			rs = pstmt.executeQuery();
			productSet = new HashSet<>();
			while (rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getString("name"),
						rs.getDate("expiry_date").toLocalDate());
				productSet.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productSet;
	}
	@Override
	public List<String> findAllName() {
		try {
			pstmt = con.prepareStatement("select name from product_2590");
			rs = pstmt.executeQuery();
			 namelist = new ArrayList<>();
			while (rs.next()) {
				namelist.add(rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return namelist;
	}
	@Override
	public List<Integer> findAllId() {
		try {
			pstmt = con.prepareStatement("select id from product_2590");
			rs = pstmt.executeQuery();
			 idlist = new ArrayList<>();
			while (rs.next()) {
				idlist.add(rs.getInt("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idlist;
	}
	@Override
	public List <Date> findAllDate(){
		try {
			pstmt = con.prepareStatement("select expiry_date from product_2590");
			rs = pstmt.executeQuery();
			 datelist = new ArrayList<>();
			while (rs.next()) {
				datelist.add(rs.getDate("expiry_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datelist;
	}

	@Override
	public Product findById(int id) {
		Product product = null;
		try {
			pstmt = con.prepareStatement("select * from product_2590 where id=?");
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDate("expiry_date").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}
	@Override
	public Product findByName(String name) {
		Product product = null;
		try {
			pstmt = con.prepareStatement("select * from product_2590 where name=?");
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDate("expiry_date").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public void save(Product product) {
		try {
			pstmt = con.prepareStatement("insert into product_2590 values(?,?,?)");
			pstmt.setInt(1, product.getId());
			pstmt.setString(2, product.getName());
			pstmt.setDate(3, Date.valueOf(product.getExpiryDate()));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(Product product) {
		try {
			pstmt = con.prepareStatement("update product_2590 set name=? where id=?");
			pstmt.setString(1, product.getName());
			pstmt.setInt(2, product.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void update_expire(Product updateproduct1) {
		try {
			pstmt = con.prepareStatement("update product_2590 set expiry_date=? where id=?");
			pstmt.setDate(1,Date.valueOf(updateproduct1.getExpiryDate()));
			pstmt.setInt(2, updateproduct1.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
		
	@Override
	public Product findByDate(LocalDate expiryDate) {
		Product product = null;
		try {
			pstmt = con.prepareStatement("select * from product_2590 where expiry_date=?");
			pstmt.setDate(1, Date.valueOf(expiryDate));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDate("expiry_date").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return product;
	}

	
	@Override
	public void delete_name(String name) {
		try {
			pstmt = con.prepareStatement("delete product_2590 where id=?");
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void delete(int id) {
		try {
			pstmt = con.prepareStatement("delete product_2590 where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	@Override
	public void delete_date(LocalDate expiryDate) {
		// TODO Auto-generated method stub
		
	}

	
}
	