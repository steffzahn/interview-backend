package de.bringmeister.model.json;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonBase {

    private static final Logger LOG = LoggerFactory.getLogger(JsonBase.class);

    @JsonAnySetter
    public void setAny( String name, Object value )
    {
        LOG.error( "JsonBase.setAny(): unknown attribute encountered during JSON decode: "+name);
    }
}
