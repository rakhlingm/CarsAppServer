package facade;

import java.util.Collection;

import dao.CarsDBDAO;
import exceptions.CarsSystemException;
import exceptions.ConnectionSystemException;
import javabeens.Car;


public class AdminFacade implements CarsApp {
	
	private CarsDBDAO carsDBDAO;
	public AdminFacade(){
		carsDBDAO = new CarsDBDAO();
	}
	void createCar(Car car) throws CarsSystemException, ConnectionSystemException {
		carsDBDAO.createCar(car);
	}; 
	void removeCar(long ID) throws CarsSystemException, ConnectionSystemException {
		carsDBDAO.removeCar(ID);
	};
	void updateCar(long ID, Car updatedCar) throws CarsSystemException, ConnectionSystemException {
		carsDBDAO.updateCar(ID, updatedCar);
	};
	Car getCar(long ID) throws CarsSystemException, ConnectionSystemException {
		return carsDBDAO.getCar(ID);		
	};
	Collection<Car> getCars() throws CarsSystemException, ConnectionSystemException {
		return carsDBDAO.getCars();	
	};
}
