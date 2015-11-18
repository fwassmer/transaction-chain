package org.broken.interceptor;

import org.apache.aries.blueprint.Interceptor;
import org.apache.log4j.Logger;
import org.osgi.service.blueprint.reflect.ComponentMetadata;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestInterceptor implements Interceptor {

	private static final Logger LOGGER = Logger.getLogger(TestInterceptor.class);

	public TestInterceptor() {
		super();
	}

	@Override
	public int getRank() {
		return 0;
	}

	@Override
	public Object preCall(ComponentMetadata cm, Method m, Object... parameters) {
		System.out.println("in preCall with params: " + Arrays.deepToString(parameters));
		return null;
	}

	@Override
	public void postCallWithException(ComponentMetadata cm, Method m, Throwable ex, Object preCallToken) {
		System.out.println("in postCallWithException");
	}
	@Override
	public void postCallWithReturn(ComponentMetadata cm, Method m, Object returnType, Object preCallToken) throws Exception {
		System.out.println("in postCallWithReturn");
	}

}