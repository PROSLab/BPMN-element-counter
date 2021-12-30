package pros.unicam.it.bpmnelementcounter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * Root resource (exposed at "uploadModel" path)
 */
@Path("/counter")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	@Path("/get")
	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
	@Path("/fileUpload")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
    public Response elementCounter( 
			@FormDataParam("model") InputStream uploadedInputStream,
			@FormDataParam("model") FormDataContentDisposition fileDetail ) throws Exception{
		
			  String xmlString = IOUtils.toString(uploadedInputStream, StandardCharsets.UTF_8.name());

			  //String xmlString = new String(request, StandardCharsets.UTF_8);
			  
			  //ElementCounter.countElements(xmlString);
			  
			  System.out.println("Received this: "+xmlString);
			  
			  String resultingCsv = ElementCounter.countElements(xmlString);
			  
			  System.out.println("Produced this: "+resultingCsv);
			  
		      // set file (and path) to be download
			  
			  
		      File file = new File("filename.csv");
		      try {
		          FileWriter myWriter = new FileWriter("filename.csv",false);
		          myWriter.write(resultingCsv);
		          myWriter.close();
		          System.out.println("Successfully wrote to the file.");
		        } catch (IOException e) {
		          System.out.println("An error occurred.");
		          e.printStackTrace();
		        }
		 
		      ResponseBuilder responseBuilder = Response.ok((Object) file);
		      responseBuilder.header("Content-Disposition", "attachment; filename=\"bpmn_stats.csv\"");
		     return responseBuilder.build();

	
    }
    
}