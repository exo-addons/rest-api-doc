package org.exoplatform.addons.restapidoc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.jersey.listing.ApiListingResourceJSON;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;




@ApplicationPath("/api")
@Api(value = "/api", description = "api de test")
public class SampleApplication extends Application {
	@ApiOperation(
			value = "Returns a set of classes",
			notes = "This is a test, does it work?")
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();

        //resources.add(FirstResource.class);
        //resources.add(SecondResource.class);
        //...

        //resources.add(org.exoplatform.calendar.ws.CalendarRestApi.class);
        resources.add(JacksonJsonProvider.class);
        resources.add(ApiListingResourceJSON.class);

        return resources;
    }
}