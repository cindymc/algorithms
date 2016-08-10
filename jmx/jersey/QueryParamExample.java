package jersey;

/**
 * Created by cindymc on 8/10/16.
 */
@Path("example")
public class QueryParamExample
{
    @Path("smooth")
    @GET
    public Response smooth(
            @DefaultValue("2") @QueryParam("step") int step,
            @DefaultValue("true") @QueryParam("min-m") boolean hasMin,
            @DefaultValue("true") @QueryParam("max-m") boolean hasMax,
            @DefaultValue("true") @QueryParam("last-m") boolean hasLast,
            @DefaultValue("blue") @QueryParam("min-color") ColorParam minColor,
            @DefaultValue("green") @QueryParam("max-color") ColorParam maxColor,
            @DefaultValue("red") @QueryParam("last-color") ColorParam lastColor
    ) { ... }

    To obtain a general map of parameter names and values for query and path parameters, use the following code:

    @GET
    public String get(@Context UriInfo ui) {
        MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
        MultivaluedMap<String, String> pathParams = ui.getPathParameters();
    }
    The following method extracts header and cookie parameter names and values into a map:

    @GET
    public String get(@Context HttpHeaders hh) {
        MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
        Map<String, Cookie> pathParams = hh.getCookies();
    }

}
