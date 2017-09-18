package javabeens;
import java.io.File;
import java.io.IOException; 

import org.apache.http.HttpEntity; 
import org.apache.http.HttpResponse; 
import org.apache.http.client.ClientProtocolException; 
import org.apache.http.client.HttpClient; 
import org.apache.http.client.methods.HttpPost; 
import org.apache.http.entity.mime.MultipartEntity; 
import org.apache.http.entity.mime.content.FileBody; 
import org.apache.http.entity.mime.content.StringBody; 
import org.apache.http.impl.client.DefaultHttpClient; 
import org.apache.http.util.EntityUtils; 

public class Test { 
       /** * @param args 
        * @throws IOException 
        * @throws ClientProtocolException 
        */ 
       public static void main(String[] args) throws ClientProtocolException, IOException {

              HttpClient httpclient = new DefaultHttpClient(); 
              HttpPost httppost = new HttpPost("http://localhost:8080/CarsApp/rest/Admin/upload-file");

              httppost.addHeader("Authorization", "Bearer 70e8e17d-e1ed-4b7a-8a8a-40383d74d467");
              httppost.addHeader("Accept", "application/json"); 
              httppost.addHeader("Content-type", "multipart/form-data"); 

              File fileToUse = new File("d:/PostFile.png"); //e.g. /temp/dinnerplate-special.jpg 
              FileBody data = new FileBody(fileToUse); 

              String file_type = "png" ; 
              String description = "Oppa Gangnam Style"; 
              String folder_id = "-1"; 
              String source = "MYCOMPUTER" ;

              MultipartEntity reqEntity = new MultipartEntity(); 
              reqEntity.addPart("file_name", new StringBody( fileToUse.getName() ) ); 
              reqEntity.addPart("folder_id", new StringBody(folder_id)); 
              reqEntity.addPart("description", new StringBody(description)); 
              reqEntity.addPart("source", new StringBody(source)); 
              reqEntity.addPart("file_type", new StringBody(file_type)); 
              reqEntity.addPart("data", data); 

              httppost.setEntity(reqEntity); 

             HttpResponse response = httpclient.execute(httppost); 
             System.out.println( response ) ; 

             HttpEntity resEntity = response.getEntity(); 
             System.out.println( resEntity ) ; 
             System.out.println( EntityUtils.toString(resEntity) ); 

              EntityUtils.consume(resEntity); 
              httpclient.getConnectionManager().shutdown(); 
      } 
}