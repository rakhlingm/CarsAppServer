package dao;

import java.io.File;
import java.util.Collection;

import exceptions.CarsSystemException;
import exceptions.ConnectionSystemException;
import javabeens.Car;

public interface CarsDAO {

	void createCar(Car car) throws CarsSystemException, ConnectionSystemException; 
	void removeCar(long ID) throws CarsSystemException, ConnectionSystemException;
	void updateCar(long ID, Car updatedCar) throws CarsSystemException, ConnectionSystemException;
	Car getCar(long id) throws CarsSystemException, ConnectionSystemException;
	Collection<Car> getCars() throws CarsSystemException, ConnectionSystemException;
	File getImage(); 
//	boolean login(String compName,String password) throws CarsSystemException, ConnectionSystemException;
}
