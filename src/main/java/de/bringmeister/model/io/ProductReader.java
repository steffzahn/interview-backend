package de.bringmeister.model.io;

import de.bringmeister.model.ModelException;
import de.bringmeister.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ProductReader {
    private static final Logger LOG = LoggerFactory.getLogger(ProductReader.class);

    private static void trim( StringBuilder sb )
    {
        int i=0;
        while( i<sb.length() )
        {
            char c = sb.charAt(i);
            if( Character.isWhitespace( c ) || (c=='\n') || (c=='\r') )
                i++;
            else
                break;
        }
        if( i>0 )
        {
            sb.delete(0,i);
        }
        i=sb.length()-1;
        while( i>=0 )
        {
            char c = sb.charAt(i);
            if( Character.isWhitespace( c ) || (c=='\n') || (c=='\r') ) {
                i--;
            } else {
                i++;
                break;
            }
        }
        if( i<sb.length() )
        {
            sb.delete(i,sb.length());
        }
    }

    public static synchronized List<Product> readFromResource(String resource) throws ModelException {
        if (resource == null)
            throw new ModelException("ProductReader.readFromResource(): missing mandatory parameter resource");
        LOG.info("ProductReader.readFromResource(): resource=" + resource);
        try {
            InputStream in = ProductReader.class.getResourceAsStream(resource);
            if (in == null)
                throw new ModelException("ProductReader.readFromResource(): resource not found: " + resource);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader eventReader = inputFactory.createXMLEventReader( reader );
            boolean insideProductsList = false;
            boolean insideProduct = false;
            boolean insideName = false;
            boolean insideSku = false;
            boolean insideDescription = false;
            String name=null;
            String sku = null;
            StringBuilder description = new StringBuilder(200);
            List<Product> result = new ArrayList<>();
            while( eventReader.hasNext() )
            {
                XMLEvent ev = eventReader.nextEvent();
                if( ev.isStartElement() )
                {
                    String s = ev.asStartElement().getName().getLocalPart();
                    if( (s==null) || (s.length()==0) )
                        throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                    switch (s) {
                        case "Products":
                            if (insideProductsList)
                                throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                            insideProductsList = true;
                            break;
                        case "Product":
                            if (!insideProductsList || insideProduct)
                                throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                            insideProduct = true;
                            break;
                        case "Name":
                            if (!insideProduct || insideName)
                                throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                            insideName = true;
                            break;
                        case "sku":
                            if (!insideProduct || insideName || insideDescription || insideSku)
                                throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                            insideSku = true;
                            break;
                        case "Description":
                            if (!insideProduct || insideName || insideDescription || insideSku)
                                throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                            insideDescription = true;
                            break;
                        default:
                            LOG.info("DEBUG START " + s);
                            break;
                    }
                } else if( ev.isCharacters() ) {
                    String s = ev.asCharacters().getData();
                    if( insideName )
                    {
                        if( name!=null )
                            throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                        name = s;
                    } else if( insideSku )
                    {
                        if( sku!=null )
                            throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                        sku = s;
                    } else if( insideDescription )
                    {
                        description.append(s);
                    }
                } else if( ev.isEndElement() ) {
                    if( insideName ) {
                        insideName = false;
                    } else if( insideSku ) {
                        insideSku = false;
                    } else if( insideDescription ) {
                        insideDescription = false;
                    } else if( insideProduct ) {
                        if( (name!=null) && (name.length()>0)
                                && (sku!=null) && (sku.length()>0) )
                        {
                            trim(description);
                            result.add( new Product(name,sku,description.toString() ) );
                        } else {
                            throw new ModelException("ProductReader.readFromResource(): product with missing or empty name or sku");
                        }
                        insideProduct = false;
                        name = null;
                        description.setLength(0);
                        sku = null;
                    } else if( insideProductsList ) {
                        insideProductsList = false;
                    } else {
                        throw new ModelException("ProductReader.readFromResource(): malformed XML input read from resource");
                    }
                }
            }
            return result;
        } catch( XMLStreamException e ) {
            throw new ModelException("ProductReader.readFromResource(): failed to read due to JAXBException", e);
        }
    }
}
