package pros.unicam.it.jersey;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public byte[] getModel( byte[] request ) throws Exception{

			  String jsonString = new String(request, StandardCharsets.UTF_8);
			  
			  System.out.println("Received this: "+jsonString);
			  
			  try {

			        FileWriter myWriter = new FileWriter("/home/fabrizio/eclipse-workspace/jersey/src/main/resources/Json.txt");
			        myWriter.write(jsonString);
			        myWriter.close();
			        System.out.println("Successfully wrote to the file.");
			        
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
			  		
			  return "QUESTO".getBytes();
    }
    
}
