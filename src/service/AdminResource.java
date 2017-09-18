package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


import com.sun.jersey.multipart.FormDataParam;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import facade.AdminFacade;
import facade.ClientType;
import javabeens.Message;
import loginsystem.CarsSystem;
import messages.MessageException;
import upload.images.FileUploadForm;


@Path("/Admin")

public class AdminResource {
	public static final String Admin_Facade = "facade";
	private static final String FILE_PATH = "d:/PostFile.png";
	@GET
	@Path("/loginAdmin/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message login(@PathParam("username") String name, @PathParam("password") String password,
			@Context HttpServletRequest request) throws MessageException {

		/**
		 * Checking name and password for propriety
		 */
		System.out.println(name + " " + password);
		try {
		String message = null;
		HttpSession session = request.getSession(true);
		if ((name == null || name.trim().isEmpty() || name.length() <= 3)
				&& (password == null || password.trim().isEmpty() || password.length() <= 3)) {
			throw new MessageException("Illegal name or password");
		}
		/**
		 * Creates new AdminFacade and return Json message, otherwise throws
		 * exception in login fail.
		 */
			CarsSystem system = CarsSystem.getInstance();
			System.out.println(name + " " + password + " " + ClientType.ADMIN);
		//	system.login(name, password, ClientType.ADMIN);
			system.login(name, password, ClientType.ADMIN);
			AdminFacade af = new AdminFacade();
			session.setAttribute(Admin_Facade, af);
			message = "Login succesfull";
			message = request.getSession().getId();
			System.out.println(message);
			return new Message(message);
		} catch (Exception e) {
			throw new MessageException("Login failed. Possibly wrong user/password. Try again");
		}	}

	@GET
	@Path("/Logout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Message logout (@Context HttpServletRequest request) throws MessageException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		String message = "You successfull logged out";
		System.out.println(message);
		return new Message(message);
	}
	
 	@GET
 	@Path("/download-file")
 	@Produces("image/png")
 	public Response getImage() {
 		File file = new File(FILE_PATH);
 		ResponseBuilder response = Response.ok((Object) file);
 		response.header("Content-Disposition",
 			"attachment; filename=image_from_server.png");
 		return response.build();
 	}
 
	 @POST
	 @Path("/upload-file")
	 @Consumes("multipart/form-data")
	     public Response uploadFile(@MultipartForm FileUploadForm form) {
	   System.out.println("Entry to uploading...");
	         String fileName = form.getFileName() == null ? "Unknown" : form.getFileName() ;
	      
	     String completeFilePath = "d:/temp/" + fileName;
	     System.out.println(completeFilePath);
	     try
	     {
	         //Save the file
	         File file = new File(completeFilePath);
	           
	         if (!file.exists())
	         {
	             file.createNewFile();
	         }
	   
	         FileOutputStream fos = new FileOutputStream(file);
	   
	         fos.write(form.getFileData());
	         fos.flush();
	         fos.close();
	     }
	     catch (IOException e)
	     {
	         e.printStackTrace();
	     }
	     //Build a response to return
	     return Response.status(200)
	         .entity("uploadFile is called, Uploaded file name : " + fileName).build();
	 }

// 	@POST
// 	@Path("/setImage")
// 	@Consumes({MediaType.MULTIPART_FORM_DATA})
// 	public Response uploadPdfFile(  @FormDataParam("file") InputStream fileInputStream,
// 	                                @FormDataParam("file") FormDataContentDisposition fileMetaData) throws Exception
// 	{
// 	    String UPLOAD_PATH = "D:/uploaded/";
// 	    try
// 	    {
// 	        int read = 0;
// 	        byte[] bytes = new byte[1024];
// 	 
// 	        OutputStream out = new FileOutputStream(new File(UPLOAD_PATH + fileMetaData.getFileName()));
// 	        while ((read = fileInputStream.read(bytes)) != -1)
// 	        {
// 	            out.write(bytes, 0, read);
// 	        }
// 	        out.flush();
// 	        out.close();
// 	    } catch (IOException e)
// 	    {
// 	        throw new WebApplicationException("Error while uploading file. Please try again !!");
// 	    }
// 	    return Response.ok("Image uploaded successfully !!").build();
// 	}  
//	@GET
//	@Path("/GetImage")
//	public File getImage(@Context HttpServletRequest request) throws MessageException {
//
//		/**
//		 * Executes collection company and sends it as Json message. otherwise
//		 * throwing exception
//		 */
//		try {
//		HttpSession session = request.getSession(false);
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
////			File image = af.getAllCompanies();
//			return image;
//		} catch (Exception e) {
//			throw new MessageException("File sending exception");
//		}
//	}
/*	@POST
	@Path("/CreateCompany")
	public Message createCompany(Company Company, @Context HttpServletRequest request) throws MessageException {
		System.out.println("here");
		try {
			String message = null;
			HttpSession session = request.getSession(false);
			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
			af.createCompany(Company);
			message = "Company created succesfully";
			System.out.println(message);
			return new Message(message);
		} catch (Exception e) {
		//	throw new MessageException("Creating new company failed. Possibly ID exists. Try again or call us");
			e.printStackTrace();
		}
		return null; 
	}   */
//
//	@DELETE
//	@Path("/RemoveCompany/{compId}")
//	public Message removeCompany(@PathParam("compId") Long compId, @Context HttpServletRequest request)
//			throws MessageException {
//		HttpSession session = request.getSession(false);
//		String message = null;
//		/**
//		 * Removing company, saving changes in DB and sends Json message.
//		 * otherwise throwing exception
//		 */
//		try {
//			Company company = new Company(compId, "*******", "***", "***");
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			af.removeCompany(company);
//			message = "Company removed succesfully";
//			return new Message(message);
//		} catch (Exception e) {
//			throw new MessageException("Company did not removed. Possibly ID doesn't exists. Please try again or call us");
//		}
//	}
//
//	@PUT
//	@Path("/UpdateCompany")
//	public Message udpateCompany(Company Company, @Context HttpServletRequest request) throws MessageException {
//
//		try {
//			HttpSession session = request.getSession(false);
//			String message = null;
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			af.updateCompany(Company);
//			message = "Company updated succesfully";
//			return new Message(message);
//		} catch (Exception e) {
//			throw new MessageException("Company did not updated. Possibly ID doesn't exists. Please try again or call us");
//		}
//	}
//
//	@GET
//	@Path("/GetAllCompanies")
//	public Collection<Company> getAllCompanies(@Context HttpServletRequest request) throws MessageException {
//
//		/**
//		 * Executes collection company and sends it as Json message. otherwise
//		 * throwing exception
//		 */
//		try {
//		HttpSession session = request.getSession(false);
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			Collection<Company> coll = af.getAllCompanies();
//			return coll;
//		} catch (Exception e) {
//			throw new MessageException("List of companies failed. Try again or call us");
//		}
//	}
//
//	@GET
//	@Path("/ShowCompany/{compId}")
//	public Company showCompany(@PathParam("compId") Long compId, @Context HttpServletRequest request)
//			throws MessageException {
//		HttpSession session = request.getSession(false);
//		/**
//		 * Executes company and sends it as Json message. otherwise throwing
//		 * exception
//		 */
//		try {
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			Company company = af.getCompany(compId);
//			return company;
//		} catch (Exception e) {
//			throw new MessageException("Company show was unsuccsessfull. "
//					+ "Possibly ID doesn't exists. Please try again or call us");
//		}
//	}
//
//	@POST
//	@Path("CreateCustomer")
//	public Message createCustomer(Customer customer, @Context HttpServletRequest request)
//			throws MessageException {
//
//		try {
//			HttpSession session = request.getSession(false);
//			String message = null;
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			af.createCustomer(customer);
//			message = "Customer created and saved in data base";
//			return new Message(message);
//		} catch (Exception e) {
//			throw new MessageException("Customer creating failed. Possibly ID exists. Please try again or call us");
//
//		}
//	}
//
//	@DELETE
//	@Path("/RemoveCustomer/{custId}")
//	public Message removeCustomer(@PathParam("custId") Long custId, @Context HttpServletRequest request)
//			throws MessageException {
//		HttpSession session = request.getSession(false);
//		String message = null;
//
//		/**
//		 * Removing customer, saving changes in DB and sends Json message.
//		 * otherwise throwing exception
//		 */
//		try {
//			Customer customer = new Customer(custId, "*******", "***");
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			af.removeCustomer(customer);
//			message = "Customer removed removed succesfully";
//			return new Message(message);
//		} catch (Exception e) {
//			throw new MessageException("Customer did not removed. Please try again or call us");
//		}
//	}
//
//	@PUT
//	@Path("/UpdateCustomer")
//	public Message udpateCustomer(Customer customer, @Context HttpServletRequest request) throws MessageException {
//
//		try {
//		HttpSession session = request.getSession(false);
//		String message = null;
//		AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			af.updateCustomer(customer);
//		message = "Customer updated succesfully";
//		return new Message(message);
//		} catch (Exception e) {
//			throw new MessageException("Company did not updated. Please try again or call us");
//		}
//	}
//
//	@GET
//	@Path("/GetAllCustomers")
//	public Collection<Customer> getAllCustomers(@Context HttpServletRequest request) throws MessageException {
//
//		/**
//		 * Creating customer collection and sends it as Json message. otherwise
//		 * throwing exception
//		 */
//		HttpSession session = request.getSession(false);
//		try {
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			Collection<Customer> coll = af.getAllCustomer();
//			return coll;
//		} catch (Exception e) {
//			throw new MessageException("Table customer failed. Try again or call us");
//		}
//	}
//
//	@GET
//	@Path("/ShowCustomer/{custId}")
//	public Customer showCustomer(@PathParam("custId") Long custId, @Context HttpServletRequest request)
//			throws MessageException {
//		HttpSession session = request.getSession(false);
//		try {
//			AdminFacade af = ((AdminFacade) session.getAttribute(Admin_Facade));
//			Customer customer = af.getCustomer(custId);
//			return customer;
//		} catch (Exception e) {
//			throw new MessageException("Customer show failed. Please try again or call us");
//		}
//	}
//	
}
