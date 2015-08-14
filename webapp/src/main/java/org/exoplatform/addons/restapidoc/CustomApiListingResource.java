package org.exoplatform.addons.restapidoc;

import io.swagger.config.Scanner;
import io.swagger.config.ScannerFactory;
import io.swagger.config.SwaggerConfig;
//import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.JaxrsScanner;
import io.swagger.jaxrs.config.ReaderConfigUtils;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Swagger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;
import java.util.*;

/**
 * Created by tgigant on 7/23/15.
 */
@Path("/")
public class CustomApiListingResource extends ApiListingResource {
    static boolean initialized = false;
    Logger LOGGER = LoggerFactory.getLogger(ApiListingResource.class);
    @Context
    ServletContext context;

    @Override
    protected synchronized Swagger scan(Application app, ServletConfig sc) {
        Swagger swagger = null;
        Scanner scanner = ScannerFactory.getScanner();
        LOGGER.debug("using scanner " + scanner);
        //String sep = " ###################### ";

//        System.out.println(sep + "Entering scan in CustomApiListingResource" + sep);
//        System.out.println(sep + "Scanner ="+scanner);

        if (scanner != null) {
            SwaggerSerializers.setPrettyPrint(scanner.getPrettyPrint());

//            System.out.println(sep + "CustomApiListingResource : swagger initialization" + sep);
//            System.out.println(sep + "before initialization");
//            System.out.println("Swagger : " + swagger);

            swagger = (Swagger) context.getAttribute("swagger");

//            System.out.println(sep + "after initialization");
//            System.out.println("Swagger : " + swagger);

            Set<Class<?>> classes;
            if (scanner instanceof JaxrsScanner) {
                JaxrsScanner jaxrsScanner = (JaxrsScanner) scanner;
                classes = jaxrsScanner.classesFromContext(app, sc);
            } else {
                classes = scanner.classes();
//                System.out.println(sep + "Classes scanned" + sep);
//                for (Class<?> cls : classes) {
//                    System.out.println(cls.getCanonicalName());
//                }
//                System.out.println(sep + "End classes scanned" + sep);
            }
            if (classes != null) {
                CustomReader reader = new CustomReader(swagger, ReaderConfigUtils.getReaderConfig(context));

                // Display
//                System.out.println(sep + "Classes to be read" + sep);
//                for (Class<?> cls : classes) {
//                    System.out.println(cls.getCanonicalName());
//                }
//                System.out.println(sep+"Before reading classes"+sep);
//                System.out.println("Swagger : "+swagger.getTags());
//                System.out.println(sep+"Reading classes"+sep);
                // end Display

                swagger = reader.read(classes);

                // Display
//                System.out.println(sep+"After reading classes"+sep);
//                System.out.println("Swagger : "+swagger.getTags());
                // end Display

                if (scanner instanceof SwaggerConfig) {
                    swagger = ((SwaggerConfig) scanner).configure(swagger);

                } else {
                    SwaggerConfig configurator = (SwaggerConfig) context.getAttribute("reader");
                    if (configurator != null) {
                        LOGGER.debug("configuring swagger with " + configurator);
                        configurator.configure(swagger);
                    } else {
                        LOGGER.debug("no configurator");
                    }
                }
                context.setAttribute("swagger", swagger);
            }
        }
        initialized = true;
        return swagger;
    }


}