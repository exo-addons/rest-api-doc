package org.exoplatform.addons.restapidoc;

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
        String[] schemes = new String[] {"http","https"};

		CustomBeanConfig beanConfig = new CustomBeanConfig();
		beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(schemes);
		beanConfig.setTitle("Calendar Rest API");
		beanConfig.setDescription("Here is a documentation about (and a way to test) eXo Platform Calendar REST API");
		beanConfig.setContact("Thibault Gigant");
		beanConfig.setBasePath("/rest/private");
		beanConfig.setLicense("Apache 2.0");
		beanConfig.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");
		beanConfig.setResourcePackage("org.exoplatform");
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