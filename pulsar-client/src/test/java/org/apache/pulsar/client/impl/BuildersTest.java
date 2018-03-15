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
package org.apache.pulsar.client.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.pulsar.client.api.PulsarClient;
import org.testng.annotations.Test;

public class BuildersTest {

    @Test
    public void clientBuilderTest() {
        ClientBuilderImpl clientBuilder = (ClientBuilderImpl) PulsarClient.builder().enableTls(true).ioThreads(10)
                .maxNumberOfRejectedRequestPerConnection(200).serviceUrl("pulsar://service:6650");

        assertEquals(clientBuilder.conf.isUseTls(), true);
        assertEquals(clientBuilder.conf.getServiceUrl(), "pulsar://service:6650");

        ClientBuilderImpl b2 = (ClientBuilderImpl) clientBuilder.clone();
        assertTrue(b2 != clientBuilder);

        b2.serviceUrl("pulsar://other-broker:6650");

        assertEquals(clientBuilder.conf.getServiceUrl(), "pulsar://service:6650");
        assertEquals(b2.conf.getServiceUrl(), "pulsar://other-broker:6650");
    }

}