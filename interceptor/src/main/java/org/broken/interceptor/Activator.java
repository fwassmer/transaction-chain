/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.broken.interceptor;

import org.apache.aries.blueprint.NamespaceHandler;
import org.apache.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Dictionary;
import java.util.Hashtable;

public class Activator implements BundleActivator {
    private static final Logger LOGGER = Logger.getLogger(Activator.class);

    @Override
    public void start(BundleContext context) throws Exception {
        LOGGER.info("activating interceptor");
        InterceptorNamespaceHandler handler = new InterceptorNamespaceHandler();
        Dictionary<String, String> props = new Hashtable<>();
        props.put("osgi.service.blueprint.namespace", "org.broken.interceptor");
        context.registerService(NamespaceHandler.class, handler, props);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
    }

}
