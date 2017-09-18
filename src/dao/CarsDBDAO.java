package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.CarsSystemException;
import exceptions.ConnectionSystemException;
import javabeens.Car;
import javabeens.Colors;
import javabeens.Make;

public class CarsDBDAO implements CarsDAO{
	
	private ConnectionPool pool;
	
	public CarsDBDAO() {
	}
	
	@Override
	public void createCar(Car car) throws CarsSystemException, ConnectionSystemException {
		Connection con = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getconnection();
			String sql = "INSERT INTO Cars (licensePlate ,make ,colors, yearMunuf, capacity, ownersNum) VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, car.getLicensePlate());
			ps.setString(2, car.getMake().toString());
			ps.setString(3, car.getColor().toString());
			ps.setInt(4, car.getYear());
			ps.setInt(5, car.getCapacity());
			ps.setInt(6, car.getOwnersNum());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		//	throw new CompanySystemException("Company or companyId already exists");
		} finally {
			pool.returnConnection(con);
		}		
	}

	@Override
	public void removeCar(long ID) throws CarsSystemException, ConnectionSystemException {
		Connection con = null;
		System.out.println(ID);
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getconnection();
			String sql = "SELECT * FROM Cars WHERE ID = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, ID);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				throw new CarsSystemException("Car id doesn't exist");
			}
			sql = "DELETE FROM Cars WHERE ID = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, ID);
			ps.executeUpdate();
			System.out.println("Car was deleted");
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new CarsSystemException("Car or companyId doesn't exists");
		} finally {
			pool.returnConnection(con);
		}		
		
	}

	@Override
	public void updateCar(long ID, Car updatedCar) throws CarsSystemException, ConnectionSystemException {
		Connection con = null;
		Car car = null;
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getconnection();
			String sql = "SELECT * FROM Cars WHERE ID = ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, ID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				car = new Car();
				car.setId(rs.getLong(1));
				car.setLicensePlate(rs.getString(2));
				car.setMake(Make.valueOf(rs.getString(3)));
				car.setColor(Colors.valueOf(rs.getString(4)));
				car.setYear(rs.getInt(5));
				car.setCapacity(rs.getInt(6));
				car.setOwnersNum(rs.getInt(7));
			}
			//licensePlate ,make ,colors, yearMunuf, capacity, ownersNum
			String sql1 = "UPDATE Cars SET licensePlate=?, make=?, colors = ?, yearMunuf = ?, capacity = ?, ownersNum = ? WHERE ID = ?";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1 = con.prepareStatement(sql1);
			ps1.setString(1, updatedCar.getLicensePlate());
			ps1.setString(2, updatedCar.getMake().toString());
			ps1.setString(3, updatedCar.getColor().toString());
			ps1.setInt(4, updatedCar.getYear());
			ps1.setInt(5, updatedCar.getCapacity());
			ps1.setInt(6, updatedCar.getOwnersNum());
			ps1.setLong(7, car.getId());
			ps1.executeUpdate();
		} catch (SQLException e) {
		//	throw new CompanySystemException("throw new CompanySystemException("Company id doesn't exist");");
			e.printStackTrace();
		} finally {
			pool.returnConnection(con);
		}
		
	}

	@Override
	public Car getCar(long ID) throws CarsSystemException, ConnectionSystemException {
		Connection con = null;
		Car car = new Car();
		try {
			pool = ConnectionPool.getInstance();
			con = pool.getconnection();
			String sql = "SELECT * FROM Cars WHERE ID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, ID);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				car = new Car();
				car.setId(rs.getLong(1));
				car.setLicensePlate(rs.getString(2));
				car.setMake(Make.valueOf(rs.getString(3)));
				car.setColor(Colors.valueOf(rs.getString(4)));
				car.setYear(rs.getInt(5));
				car.setCapacity(rs.getInt(6));
				car.setOwnersNum(rs.getInt(7));
		
			}
		} catch (SQLException e) {
			//throw new CarsSystemException("Company id doesn't exist");
			e.printStackTrace();
		} finally {
			pool.returnConnection(con);
		}
		return car;
	}

	@Override
	public Collection<Car> getCars() throws CarsSystemException, ConnectionSystemException {
		Connection con = null;
			pool = ConnectionPool.getInstance();
			con = pool.getconnection();
			List<Car> cars = new ArrayList<>();
			try {
				String sql = "SELECT * FROM Cars";
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				Car car;
				while (rs.next()) {
					car = new Car();
					car.setId(rs.getLong(1));
					car.setLicensePlate(rs.getString(2));
					car.setMake(Make.valueOf(rs.getString(3)));
					car.setColor(Colors.valueOf(rs.getString(4)));
					car.setYear(rs.getInt(5));
					car.setCapacity(rs.getInt(6));
					car.setOwnersNum(rs.getInt(7));
					cars.add(car);
				}
			} catch (SQLException e) {
				throw new CarsSystemException("Parameters are invalid, please try again");
			} finally {
				pool.returnConnection(con);		
			}
			return cars;
	}

	@Override
	public File getImage() {
		// TODO Auto-generated method stub
		return null;
	}

}
