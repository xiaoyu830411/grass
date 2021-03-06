/*
 * Copyright 2019 Jonas Yang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yanglinkui.grass.protocol.message.rabbitmq;

import com.yanglinkui.grass.protocol.message.api.Consumer;
import com.yanglinkui.grass.protocol.message.api.MessagingFactory;
import com.yanglinkui.grass.protocol.message.api.Producer;

import java.util.Map;
import java.util.concurrent.ExecutorService;

public class RabbitMQFactory implements MessagingFactory {

    @Override
    public Producer createProducer(String group, String addresses, Map<String, String> config) {
        return new RabbitMQProducer(group, addresses, config);
    }

    @Override
    public Consumer createConsumer(String group, String addresses, Map<String, String> config, ExecutorService executorService) {
        return new RabbitMQConsumer(group, addresses, config, executorService);
    }
}
