package jersey;

/**
 * Created by cindymc on 8/10/16.
 */
Path("/users/{username}")
public class PathParamExample {

    @GET
    @Produces("text/xml")
    public String getUser(@PathParam("username") String userName) {
        ...
    }
}

