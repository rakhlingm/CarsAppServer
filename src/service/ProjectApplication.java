package service;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import web.provider.MessageErrorProvider;

@ApplicationPath("/rest")
public class ProjectApplication extends Application {

	/**
	 * Definition of jdbc driver
	 */
//	static {
//		try {
//			Class.forName("org.sqlite.JDBC");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
	
	static {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * definition of class singletons
	 * @see javax.ws.rs.core.Application#getSingletons()
	 */
	@Override
	public Set<Object> getSingletons() {
		Set<Object> singletons = new HashSet<>();
		singletons.add(new AdminResource());
		singletons.add(new MessageErrorProvider());
		singletons.add(new DownloadFileResource());
		singletons.add(new UploadFileService());
		return singletons;
	}

	@Override
	public Set<Class<?>> getClasses() {
		// TODO Auto-generated method stub
		return null;
	}

}
