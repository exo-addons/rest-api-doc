package org.exoplatform.addons.restapidoc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonJsonProvider extends JacksonJaxbJsonProvider {
    private static ObjectMapper commonMapper = null;

    public JacksonJsonProvider() {
        String sep = "\n##########################################################################\n";
        if(commonMapper == null){
            ObjectMapper mapper = new ObjectMapper();

            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            commonMapper = mapper;
        }
        System.out.println(sep+"JacksonJsonProvider"+sep+commonMapper.toString()+sep);
        super.setMapper(commonMapper);
    }


}