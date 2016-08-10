package jersey;

/**
 * Entity providers are mapping services between HTTP representations and Java types.
 * Created by cindymc on 8/10/16.
 */
@Consumes("application/x-www-form-urlencoded")
@Provider
public class EntityProvider implements MessageBodyReader<NameValuePair>
{

    @Produces("text/html")
    @Provider
    public class FormWriter implements
            MessageBodyWriter<Hashtable<String, String>> {
      // do work
    }
}


//    The following example shows how to use ResponseBuilder:

@GET
public Response getItem() {
        System.out.println("GET ITEM " + container + " " + item);

        Item i = MemoryStore.MS.getItem(container, item);
        if (i == null)
        throw new NotFoundException("Item not found");
        Date lastModified = i.getLastModified().getTime();
        EntityTag et = new EntityTag(i.getDigest());
        ResponseBuilder rb = request.evaluatePreconditions(lastModified, et);
        if (rb != null)
        return rb.build();

        byte[] b = MemoryStore.MS.getItemData(container, item);
        return Response.ok(b, i.getMimeType()).
        lastModified(lastModified).tag(et).build();
        }