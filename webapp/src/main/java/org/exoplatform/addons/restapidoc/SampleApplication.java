package org.exoplatform.addons.restapidoc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
@Api(value = "/api", description = "api de test")
public class SampleApplication extends Application {
	@ApiOperation(
			value = "Returns a set of classes",
			notes = "Actually there's just one class : io.swagger.jersey.listing.ApiListingResourceJSON")
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();

        //resources.add(FirstResource.class);
        //resources.add(SecondResource.class);
        //...

        resources.add(io.swagger.jersey.listing.ApiListingResourceJSON.class);

        return resources;
    }
}