/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the 
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.helloworld;
import com.codahale.metrics.*;
import com.codahale.metrics.jvm.FileDescriptorRatioGauge;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.codahale.metrics.servlets.MetricsServlet.ContextListener;

/**
 * A simple Coda Hale Metrics Implementation
 * 
 * @author Greg Hoelzer
 * 
 */
public class MyMetricsContextListener extends ContextListener {
//    public static final MetricRegistry REGISTRY = new MetricRegistry();
    public static MetricRegistry REGISTRY = null;
    {
    	MetricRegistry metricsRegistry = new MetricRegistry();
        
    	metricsRegistry.register("jvm.gc", new GarbageCollectorMetricSet());
    	metricsRegistry.register("jvm.memory", new MemoryUsageGaugeSet());
    	metricsRegistry.register("jvm.thread-states", new ThreadStatesGaugeSet());
    	metricsRegistry.register("jvm.fd-usage", new FileDescriptorRatioGauge());
    	
    	REGISTRY = metricsRegistry;
    }

    @Override
    protected MetricRegistry getMetricRegistry() {
        return REGISTRY;
    }
}
