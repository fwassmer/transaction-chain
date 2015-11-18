package org.broken.interceptor;

import org.apache.aries.blueprint.ComponentDefinitionRegistry;
import org.apache.aries.blueprint.NamespaceHandler;
import org.apache.aries.blueprint.ParserContext;
import org.apache.aries.blueprint.mutable.MutableBeanMetadata;
import org.apache.aries.blueprint.mutable.MutablePassThroughMetadata;
import org.apache.log4j.Logger;
import org.osgi.service.blueprint.reflect.ComponentMetadata;
import org.osgi.service.blueprint.reflect.Metadata;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.net.URL;
import java.util.Set;

public class InterceptorNamespaceHandler implements NamespaceHandler {

    public static final String ANNOTATION_PARSER_BEAN_NAME = ".test-interceptor";
    private static final Logger LOGGER = Logger.getLogger(InterceptorNamespaceHandler.class);

    private void parseElement(Element elt, ComponentMetadata cm, ParserContext pc)
    {
        LOGGER.debug("parser asked to parse element: " + elt.getNodeName());

        ComponentDefinitionRegistry cdr = pc.getComponentDefinitionRegistry();
        if ("enable".equals(elt.getLocalName())) {
            Node n = elt.getChildNodes().item(0);
            if (n == null || Boolean.parseBoolean(n.getNodeValue())) {
                //We need to register a bean processor to add annotation-based config
                if (!cdr.containsComponentDefinition(ANNOTATION_PARSER_BEAN_NAME)) {
                    LOGGER.debug("Enabling annotation based transactions");
                    MutableBeanMetadata meta = createAnnotationParserBean(pc, cdr);
                    cdr.registerComponentDefinition(meta);
                }
            }
        }
    }

    private MutableBeanMetadata createAnnotationParserBean(ParserContext pc, ComponentDefinitionRegistry cdr) {
        MutableBeanMetadata meta = pc.createMetadata(MutableBeanMetadata.class);
        meta.setId(ANNOTATION_PARSER_BEAN_NAME);
        meta.setRuntimeClass(InterceptorBeanProcessor.class);
        meta.setProcessor(true);
        meta.addArgument(passThrough(pc, cdr), ComponentDefinitionRegistry.class.getName(), 0);
        return meta;
    }

    private MutablePassThroughMetadata passThrough(ParserContext pc, Object o) {
        MutablePassThroughMetadata meta = pc.createMetadata(MutablePassThroughMetadata.class);
        meta.setObject(o);
        return meta;
    }

    public ComponentMetadata decorate(Node node, ComponentMetadata cm, ParserContext pc)
    {
        if (node instanceof Element) {
            Element elt = (Element) node;
            parseElement(elt, cm, pc);
        }
        return cm;
    }

    public Metadata parse(Element elt, ParserContext pc)
    {
        parseElement(elt, pc.getEnclosingComponent(), pc);
        return null;
    }

    public URL getSchemaLocation(String namespaceUri)
    {
        return this.getClass().getResource("/test-interceptor.xsd");
    }

    @SuppressWarnings("rawtypes")
    public Set<Class> getManagedClasses()
    {
        return null;
    }

}