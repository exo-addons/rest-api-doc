package org.exoplatform.addons.restapidoc;

import io.swagger.annotations.Api;
import io.swagger.config.ScannerFactory;
//import io.swagger.jaxrs.Reader;
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

    CustomReader reader = new CustomReader(new Swagger());

    @Override
    public void setScan(boolean shouldScan) {
        Set<Class<?>> classes = classes();

//        String sep = " ################## ";
////        System.out.println(sep + "Entering setScan" + sep);
//        for (Class<?> cls : classes) {
////            System.out.println("Final output class: " + cls.getCanonicalName());
//        }

        if (classes != null) {
////            System.out.println(sep + "host + basePath + info" + sep);
////            if (getInfo() == null) System.out.println("Info null");
////            else System.out.println("info :"+ getInfo());
////            if (getHost() == null) System.out.println("host null");
////            else System.out.println("host :"+ getHost());
////            if (getBasePath() == null) System.out.println("basepath null");
////            else System.out.println("basepath :"+ getBasePath());
////            System.out.println(sep + "End of display" + sep);
            reader.read(classes)
                    .host(getHost())
                    .basePath(getBasePath())
                    .info(getInfo());
//            //System.out.println(sep + "Displaying reader"+sep);
        }
        ScannerFactory.setScanner(this);
//        System.out.println(sep + "Scanner set" + sep);
//        System.out.println(sep + "Scanner ="+this);
    }


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
//        System.out.println("###### URLs in config of reflections:");
//        for (URL url : reflections.getConfiguration().getUrls()) {
//            System.out.println(url.toString());
//        }
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Api.class);
//        for (Class<?> cls : classes) {
//            System.out.println("class found with annotations with @Api annotations: " + cls.getCanonicalName());
//        }
        classes.addAll(reflections.getTypesAnnotatedWith(javax.ws.rs.Path.class));
        Set<Class<?>> output = new HashSet<Class<?>>();
//        for (Class<?> cls : classes) {
//            System.out.println("class found with annotations with @Api + @Path annotations : "+cls.getCanonicalName());
//        }
        for (Class<?> cls : classes) {
//            System.out.println("checking class: "+cls.getCanonicalName());
            if (allowAllPackages) {
                output.add(cls);
            } else {
                for (String pkg : acceptablePackages) {
//                    System.out.println("accepted pkg: "+pkg);
                    if (cls.getPackage().getName().startsWith(pkg)) {
//                        System.out.println("class added: "+cls.getPackage().getName());
                        output.add(cls);
                    }
                }
            }
        }
//        for (Class<?> cls : output) {
//            System.out.println("Final output class: " + cls.getCanonicalName());
//        }
        return output;
    }



}
