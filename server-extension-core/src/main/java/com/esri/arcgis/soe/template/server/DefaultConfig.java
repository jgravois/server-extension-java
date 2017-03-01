/*
 * Copyright (c) 2017 Esri
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.​
 */

package com.esri.arcgis.soe.template.server;

import com.esri.arcgis.soe.template.rest.support.jackson.ObjectMapperFactory;
import com.esri.arcgis.soe.template.server.internal.RestDelegateMappingRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.inject.Inject;
import javax.inject.Singleton;

@Configuration
@ComponentScan("com.esri.arcgis.soe.template")
public class DefaultConfig {

    @Inject
    private Environment env;
    private final ObjectMapper objectMapper;

    public DefaultConfig() {
        objectMapper = new ObjectMapperFactory().create();
    }

    @Bean
    @Singleton
    public ObjectMapper objectMapper() {
        return objectMapper;
    }

    @Bean
    @Singleton
    public RestDelegateMappings delegateMappings() {
        RestDelegateMappingRegistry registry = new RestDelegateMappingRegistry(
                objectMapper);
        registry.configureDefaultArgumentResolvers();
        registry.configureDefaultReturnValueHandlers();
        return registry;
    }
}
