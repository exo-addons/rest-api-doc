package org.exoplatform.addons.restapidoc;

//import io.swagger.jaxrs.config.BeanConfig;
//import io.swagger.models.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Created by tgigant on 7/14/15.
 */

public class Bootstrap extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    public void init(ServletConfig config) throws ServletException {
		//super.init(config);


		/*Info info = new Info()
				.title("Calendar Rest API")
				.description("Here is a documentation about (and a way to test) eXo Platform Calendar REST API")
				.termsOfService("http://localhost:8080/terms/")
				.contact(new Contact()
						.email("tgigant@exoplatform.org"))
				.license(new License()
						.name("Apache 2.0")
						.url("http://www.apache.org/licenses/LICENSE-2.0.html"));*/
        String[] schemes = new String[] {"http","https"};

		CustomBeanConfig beanConfig = new CustomBeanConfig();
		beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(schemes);
		beanConfig.setTitle("Calendar Rest API");
		beanConfig.setDescription("Here is a documentation about (and a way to test) eXo Platform Calendar REST API");
		//beanConfig.setContact("<a href='mailto:tgigant@exoplatform.com'>Thibault Gigant</a>");
		beanConfig.setBasePath("/rest-api-doc-webapp");
		beanConfig.setLicense("Apache 2.0");
		beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
        //beanConfig.setResourcePackage("org.exoplatform.calendar.ws,org.exoplatform.social.rest.api");
		beanConfig.setResourcePackage("org.exoplatform");
		//beanConfig.setHost("http://localhost:8080");
        beanConfig.setScan(true);


		ServletContext context = config.getServletContext();
		/*Swagger swagger = new Swagger();//.info(info);
		swagger.tag(new Tag()
		.name("rest-api-doc-webapp")
				.description("Operations about Calendar on eXo Platform")
		.externalDocs(new ExternalDocs("Find out more about the service", "http://exoplatform.org")));
        //System.out.println("Context Path : " + context.getContextPath());
		context.setAttribute("swagger", swagger);*/
		context.setAttribute("swagger", beanConfig.getSwagger());
    }
}