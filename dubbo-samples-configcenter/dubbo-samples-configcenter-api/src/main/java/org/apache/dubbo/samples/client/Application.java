/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.samples.client;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ConfigCenterConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.samples.api.GreetingsService;

public class Application {
    public static void main(String[] args) throws Exception {
        // Enable Config Center.
        ConfigCenterConfig configCenter = new ConfigCenterConfig();
        configCenter.setType("zookeeper");
        configCenter.setAddress("127.0.0.1:2181");
        configCenter.init();

        // If you don't want to use ConfigCenter provided by dubbo, you can set external configuration to Dubbo directly.
        // We created a Map instance manually and put a value into it, but in reality, the external configurations will most likely being generated from other plugins in your system.
        /*Map<String, String> dubboConfigurations = new HashMap<>();
        dubboConfigurations.put("dubbo.registry.address", "zookeeper://127.0.0.1:2181");
        Environment.getInstance().updateExternalConfigurationMap(dubboConfigurations);*/

        ReferenceConfig<GreetingsService> reference = new ReferenceConfig<>();
        reference.setApplication(new ApplicationConfig("first-dubbo-client"));
//        reference.setRegistry(new RegistryConfig("multicast://224.5.6.7:1234"));
        reference.setInterface(GreetingsService.class);
        GreetingsService greetingsService = reference.get();
        String message = greetingsService.sayHi("dubbo");
        System.out.println(message);
    }
}