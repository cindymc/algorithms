package jersey;

/**
 * Created by cindymc on 8/10/16.
 */
@Path("/myResource")
@Consumes("multipart/related")
public class EntityConsumer {
    @POST
    public String doPost(MimeMultipart mimeMultipartData) {
        ...
    }

    @POST
    @Consumes("application/x-www-form-urlencoded")
    public String doPost2(FormURLEncodedProperties formData) {
        ...
    }
}