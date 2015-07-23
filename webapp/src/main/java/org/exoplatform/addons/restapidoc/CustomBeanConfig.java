package org.exoplatform.addons.restapidoc;

import io.swagger.annotations.Api;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.*;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tgigant on 7/22/15.
 */
public class CustomBeanConfig extends BeanConfig {

    Reader reader = new Reader(new Swagger());

    @Override
    public Set<Class<?>> classes() {
        ConfigurationBuilder config = new ConfigurationBuilder();
        Set<String> acceptablePackages = new HashSet<String>();

        boolean allowAllPackages = false;

        if (getResourcePackage()!= null && !"".equals(getResourcePackage())) {
            String[] parts = getResourcePackage().split(",");
            for (String pkg : parts) {
                if (!"".equals(pkg)) {
                    acceptablePackages.add(pkg);
                    config.addUrls(ClasspathHelper.forPackage(pkg));
                }
            }
        } else {
            allowAllPackages = true;
        }

        config.setScanners(new ResourcesScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());

        setInfo(new Info()
                .description(getDescription())
                .title(getTitle())
                .version(getVersion())
                .termsOfService(getTermsOfServiceUrl()));

        if (getContact() != null) {
            this.getInfo().contact(new Contact()
                    .name(getContact()));
        }
        if (getLicense() != null && getLicenseUrl() != null) {
            this.getInfo().license(new License()
                    .name(getLicense())
                    .url(getLicenseUrl()));
        }
        if (getSchemes() != null) {
            for (String scheme : getSchemes()) {
                reader.getSwagger().scheme(Scheme.forValue(scheme));
            }
        }

        reader.getSwagger().setInfo(getInfo());
        final Reflections reflections = new Reflections(config);
        System.out.println("###### URLs in config of reflections:");
        for (URL url : reflections.getConfiguration().getUrls()) {
            System.out.println(url.toString());
        }
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Api.class);
        for (Class<?> cls : classes) {
            System.out.println("classes find with annotations with @Api annotations: " + cls.getCanonicalName());
        }
        classes.addAll(reflections.getTypesAnnotatedWith(javax.ws.rs.Path.class));
        Set<Class<?>> output = new HashSet<Class<?>>();
        for (Class<?> cls : classes) {
            System.out.println("classes find with annotations with @Api + @Path annotations : "+cls.getCanonicalName());
        }
        for (Class<?> cls : classes) {
            System.out.println("checking class: "+cls.getCanonicalName());
            if (allowAllPackages) {
                output.add(cls);
            } else {
                for (String pkg : acceptablePackages) {
                    System.out.println("accept pck: "+pkg);
                    if (cls.getPackage().getName().startsWith(pkg)) {
                        System.out.println("class added: "+cls.getPackage().getName());
                        output.add(cls);
                    }
                }
            }
        }
        for (Class<?> cls : output) {
            System.out.println("Final output class: " + cls.getCanonicalName());
        }
        return output;
    }
}
