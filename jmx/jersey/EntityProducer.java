package jersey;

/**
 * Created by cindymc on 8/10/16.
 */

@Path("/myResource")
@Produces("text/plain")
public class EntityProducer {
    @GET
    public String doGetAsPlainText() {
        ...
    }

    @GET
    @Produces("text/html")
    public String doGetAsHtml() {
        ...
    }
}