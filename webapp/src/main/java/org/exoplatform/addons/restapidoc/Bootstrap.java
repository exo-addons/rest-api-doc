package org.exoplatform.addons.restapidoc;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Contact;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

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
		
		

		BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.0");
        beanConfig.setBasePath("http://localhost:8080/rest/private/v1/calendar/");
        beanConfig.setResourcePackage("io.swagger.resources");
        beanConfig.setScan(true);

		Info info = new Info()
		.title("eXo REST API DOC")
		.description("Here is a documentation (and a way to test) eXo Platform REST API")
		.termsOfService("http://localhost:8080/terms/")
		.contact(new Contact()
		.email("tgigant@exoplatform.org"))
		.license(new License()
		.name("Apache 2.0")
		.url("http://www.apache.org/licenses/LICENSE-2.0.html"));

		ServletContext context = config.getServletContext();
		Swagger swagger = new Swagger().info(info);
		swagger.tag(new Tag()
		.name("rest-doc-api")
		.description("Operations about Calendar on eXo Platform")
		.externalDocs(new ExternalDocs("Find out more about the service", "http://exoplatform.org")));

		context.setAttribute("swagger", swagger);
    }
}