package jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

/**
 * Created by cindymc on 8/10/16.
 */
// The Java class will be hosted at the URI path "/helloworld"
@Path("/helloworld")
public class HelloWorldResource
{
    // The Java method will process HTTP GET requests
    @GET
    // The Java method will produce content identified by the MIME Media
    // type "text/plain"
    @Produces("text/plain")
    public String getClichedMessage() {
        // Return some cliched textual content
        return "Hello World";
    }

    // POST is used to create; requests that the serever acceept the contents in the message body to create a new
    // entity on the system.  The response should include that entity id (and its url?).  It is NOT idempotent
    @POST
    @Consumes("text/plain")
    public void postClichedMessage(String message) {
        // Store the message
    }

    // PUT is used to create or update.  It will overwrite an existing entity if it exists.  You can call it as many
    // times as you like (with the same parameters) with no side effects, so it is IDEMPOTENT
    @PUT
    public Response putContainer() {
        System.out.println("PUT CONTAINER " + container);

        URI uri =  uriInfo.getAbsolutePath();
        Container c = new Container(container, uri.toString());

        Response r;
        if (!MemoryStore.MS.hasContainer(c)) {
            r = Response.created(uri).build();
        } else {
            r = Response.noContent().build();
        }

        MemoryStore.MS.createContainer(c);
        return r;
    }
}
