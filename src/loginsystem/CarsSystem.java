package loginsystem;

import java.sql.SQLException;

import dao.CarsDBDAO;
import dao.ConnectionPool;
import exceptions.CarsSystemException;
import exceptions.ConnectionSystemException;
import facade.AdminFacade;
import facade.CarsApp;
import facade.ClientType;




public class CarsSystem {
	private CarsDBDAO carsDB = new CarsDBDAO();
	private String companyName;
	private final static CarsSystem singleton = new CarsSystem();
	private ConnectionPool pool = ConnectionPool.getInstance();
	
	public static CarsSystem getInstance() {
		return singleton;
	}

	public String getCompany() {
		return companyName;
	}
	
	 public CarsApp login(String username, String password, ClientType clientType)
			throws CarsSystemException, ConnectionSystemException {
		 System.out.println("How you are?   I'm : " + username);
		 CarsApp ccf = null;

/*		if (clientType == ClientType.ADVERTISER && compDB.login(username, password)) {
			Company company = compDB.getCompanyUser(username);
			companyName = company.getCompName();
			ccf = new CompanyFacade(companyName);
			System.out.println("Company: " + companyName);
		} else */ if (clientType == ClientType.ADMIN) {
			String user = "user";
			String pass = "1234";
			if (username.equals(user) && password.equals(pass)) {
				ccf = new AdminFacade();
				System.out.println("Admin");
			} else {
				throw new CarsSystemException();
			}
		} else {
			throw new CarsSystemException();
		}
		return ccf;
	}
	 
	 public void shutdown() throws SQLException, ConnectionSystemException {
			pool.closeAllConnections();
		}
}

