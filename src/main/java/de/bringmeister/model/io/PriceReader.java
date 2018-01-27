package de.bringmeister.model.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.bringmeister.model.ModelException;
import de.bringmeister.model.Price;
import de.bringmeister.model.json.JsonPrice;
import de.bringmeister.model.json.JsonPriceList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PriceReader {
    private static final Logger LOG = LoggerFactory.getLogger(PriceReader.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static synchronized List<Price> readFromResource(String resource) throws ModelException {
        if (resource == null)
            throw new ModelException("PriceReader.readFromResource(): missing mandatory parameter resource");

        try {
            LOG.info("PriceReader.readFromResource(): resource=" + resource);
            InputStream in = PriceReader.class.getResourceAsStream(resource);
            if (in == null)
                throw new ModelException("PriceReader.readFromResource(): resource not found: " + resource);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder(1000);
            sb.append("{\"list\": ");
            for (; ; ) {
                String line;
                line = reader.readLine();
                if (line == null)
                    break;
                sb.append(line).append('\n');
            }
            sb.append("}\n");
            JsonPriceList jpl = (JsonPriceList)objectMapper.readValue(sb.toString(), JsonPriceList.class);
            List<Price> result = new ArrayList<>();
            for( JsonPrice jp : jpl.getList() )
            {
                result.add(new Price(jp));
            }
            return result;
        } catch (IOException e) {
            throw new ModelException("PriceReader.readFromResource(): failed to read due to IOException", e);
        }
    }
}
