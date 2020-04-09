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

package com.yanglinkui.grass.client;

import com.yanglinkui.grass.Executor;
import com.yanglinkui.grass.GrassContext;
import com.yanglinkui.grass.GrassResponse;
import com.yanglinkui.grass.registry.Registry;

import java.util.concurrent.CompletableFuture;

public class DefaultClientExecutorBuilder implements ClientExecutorBuilder {

    private GrassContext context;

    private SpiRouter spiRouter;

    private LoadBalance loadBalance;

    private Registry registry;

    private long timeout;

    public DefaultClientExecutorBuilder(GrassContext context, Registry registry) {
        this.context = context;
        this.registry = registry;
    }

    @Override
    public ClientExecutorBuilder setSpiRouter(SpiRouter spiRouter) {
        this.spiRouter = spiRouter;
        return this;
    }

    @Override
    public ClientExecutorBuilder setLoadBalance(LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
        return this;
    }

    @Override
    public ClientExecutorBuilder setTimeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    @Override
    public Executor<CompletableFuture<GrassResponse>> build() {
        return new ClientExecutor(this.context, this.loadBalance, this.spiRouter, this.timeout, this.registry);
    }
}
