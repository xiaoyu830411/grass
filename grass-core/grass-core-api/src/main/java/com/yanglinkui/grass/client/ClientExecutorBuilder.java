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
import com.yanglinkui.grass.GrassResponse;

import java.util.concurrent.CompletableFuture;

public interface ClientExecutorBuilder {

    public Executor<CompletableFuture<GrassResponse>> build();

    public ClientExecutorBuilder setSpiRouter(SpiRouter spiRouter);

    public ClientExecutorBuilder setLoadBalance(LoadBalance loadBalance);

    public ClientExecutorBuilder setTimeout(long timeout);
}
