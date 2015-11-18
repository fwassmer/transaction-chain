package org.broken.interceptor;

import org.apache.aries.blueprint.BeanProcessor;
import org.apache.aries.blueprint.ComponentDefinitionRegistry;
import org.apache.aries.blueprint.Interceptor;
import org.apache.log4j.Logger;
import org.osgi.service.blueprint.reflect.BeanMetadata;

import java.util.List;

public class InterceptorBeanProcessor implements BeanProcessor {

	private static final Logger LOGGER = Logger.getLogger(BeanProcessor.class);

	public static final String AUTH_PROCESSOR_BEAN_NAME = "org.broken.authorization";
	private ComponentDefinitionRegistry cdr;

	public InterceptorBeanProcessor() {}

	public InterceptorBeanProcessor(ComponentDefinitionRegistry cdr) {
		this.cdr = cdr;
	}

	public void setCdr(ComponentDefinitionRegistry cdr) {
		this.cdr = cdr;
	}

	@Override
	public void afterDestroy(Object arg0, String arg1) {}

	@Override
	public Object afterInit(Object bean, String beanName, BeanCreator beanCreator, BeanMetadata beanData) {
		return bean;
	}

	@Override
	public void beforeDestroy(Object arg0, String arg1) {}

	@Override
	public Object beforeInit(Object bean, String beanName, BeanCreator beanCreator, BeanMetadata beanData) {
		List<Interceptor> interceptors = cdr.getInterceptors(beanData);
		LOGGER.info("bean: " + beanName + " already has following interceptors: ");
		for (Interceptor interceptor : interceptors) {
			LOGGER.info(interceptor.getClass().getCanonicalName());
		}
		cdr.registerInterceptorWithComponent(beanData, new TestInterceptor());
		return bean;
	}

}
