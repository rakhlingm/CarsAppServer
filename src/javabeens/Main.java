package javabeens;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;


import dao.CarsDBDAO;
import exceptions.CarsSystemException;
import exceptions.ConnectionSystemException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.apache.poi.util.IOUtils;
import org.jboss.resteasy.client.ClientResponse;

import com.sun.jersey.multipart.FormDataMultiPart;



public class Main {
	private static final String BASE_URI = "http://localhost:8080/CarsApp/rest/Admin/getImage";
	private static final String URI_DOWNLOAD = "http://localhost:8080/CarsApp/rest/Admin/getImage";
	public static void main(String[] args) throws CarsSystemException, ConnectionSystemException, IOException {
		downloadClient();
	}
	public static void downloadClient() throws IOException{
	    Client client = ClientBuilder.newClient();
	    WebTarget target = client.target(URI_DOWNLOAD);

	    Response resp = target
	      .request("application/pdf,image/png,application/xml,application/vnd.ms-excel")
	      .get();

	    if(resp.getStatus() == Response.Status.OK.getStatusCode())
	    {
	        InputStream is = resp.readEntity(InputStream.class);
	        fetchFeedAnotherWay(is); 
	        //fetchFeedAnotherWay(is) //use for Java 7
	        IOUtils.closeQuietly(is);
	    } 
	    else{
	        throw new WebApplicationException("Http Call failed. response code is"+resp.getStatus()+". Error reported is"+resp.getStatusInfo());
	    }
	}

	/**
	* Alternate way to Store contents of file from response to local disk using
	* java 7, java.nio.file.Files
	 * @throws IOException 
	*/
	private static void fetchFeedAnotherWay(InputStream is) throws IOException{
	    File downloadfile = new File("D:/http/ImageFromServer.png");  
	    Files.copy(is, downloadfile.toPath(), StandardCopyOption.REPLACE_EXISTING);
	    System.out.println("the file details after call:"+downloadfile.getAbsolutePath()+", size is "+downloadfile.length() + " bytes");
	}
	/**
	 * Store contents of file from response to local disk using java 7 
	 * java.nio.file.Files
	 * @throws IOException 
	 */
	private static void fetchFeed(InputStream is) throws IOException{
		File downloadfile = new File("D:/http/ImageFromServer.png");  
		byte[] byteArray = IOUtils.toByteArray(is);
		FileOutputStream fos = new FileOutputStream(downloadfile);
		fos.write(byteArray);
		fos.flush();
		fos.close();
	}
}


/*		CarsDBDAO carsDBDAO = new CarsDBDAO();
		List<Car> cars = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			Make make = Make.values()[i];
			Colors color = Colors.values()[i];
			Car car = new Car(i, Integer.toString(10000 + i), make, color, 2000 + i, 2000, 2);
//			carsDBDAO.createCar(car);
		}
		cars = (List<Car>) carsDBDAO.getCars();
		for (Car fromCars : cars) {
			System.out.println(fromCars);					
		}
		System.out.println("====================================================================");
//		System.out.println(carsDBDAO.getCar(1));	
		System.out.println(carsDBDAO.getCar(1));
		System.out.println("====================================================================");
	//	Car updatedCar = new Car(0, "Number", Make.MAZDA, Colors.GREEN, 2017, 3600, 1);
		carsDBDAO.removeCar(1);
		cars = new ArrayList<>();
		cars = (List<Car>) carsDBDAO.getCars();
		for (Car fromCars : cars) {
			System.out.println(fromCars);					
		}  */