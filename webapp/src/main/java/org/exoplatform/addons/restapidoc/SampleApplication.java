package org.exoplatform.addons.restapidoc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
//import io.swagger.jersey.listing.ApiListingResourceJSON;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;




@ApplicationPath("/api")
@Api(value = "/api")
public class SampleApplication extends Application {
	@ApiOperation(
			value = "Returns a set of classes")
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();

        //resources.add(FirstResource.class);
        //resources.add(SecondResource.class);
        //...

        resources.add(CustomApiListingResource.class);
        resources.add(SwaggerSerializers.class);

        return resources;
    }
}