package service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("downloadfile")
@Produces("multipart/mixed")
public class DownloadFileResource {
/*
    @GET
    public Response getFile() {
    	System.out.println("getFile...1");
        java.io.File objFile = new java.io.File(
                "D:/PostFile.txt");
        System.out.println("getFile...2");
        MultiPart objMultiPart = new MultiPart();
        System.out.println("getFile...3");
        objMultiPart.type(new MediaType("multipart", "mixed"));
        System.out.println("getFile...4");
        objMultiPart
                .bodyPart(objFile.getName(), new MediaType("text", "plain"));
        System.out.println("getFile...5");
        objMultiPart.bodyPart("" + objFile.length(), new MediaType("text",
                "plain"));
        System.out.println("getFile...6");
        objMultiPart.bodyPart(objFile, new MediaType("multipart", "mixed"));
        System.out.println("getFile...7");
        return Response.ok(objMultiPart).build();
    }  */

}