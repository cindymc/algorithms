Jersey supports the use of Java EE Managed beans as root resource classes, providers as well as Application subclasses.

In the code below, you can find an example of a bean, that uses a managed-bean interceptor defined as a JAX-RS bean.
The bean is used to intercept calls to the resource method getIt():


@AroundInvoke
Defines an interceptor method that interposes on business methods. May be applied to any non-final, non-static method
with a single parameter of type InvocationContext and return type Object of the target class (or superclass) or of any
interceptor class.

 @AroundInvoke
 public Object intercept(InvocationContext ctx) throws Exception { ... }


@ManagedBean
@Path("/managedbean")
public class ManagedBeanResource {

    public static class MyInterceptor {
        @AroundInvoke
        public String around(InvocationContext ctx) throws Exception {
            System.out.println("around() called");
            return (String) ctx.proceed();   // go to the next Interceptor in the interceptor chain
        }
    }

    @GET
    @Produces("text/plain")
    @Interceptors(MyInterceptor.class)
    public String getIt() {
        return "Hi managed bean!";
    }
}