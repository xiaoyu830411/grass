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

package com.yanglinkui.grass;

import java.util.concurrent.atomic.AtomicReference;

public class GrassContextManager {

    static AtomicReference<GrassContext> contextHolder = new AtomicReference<>();

    public static GrassContext getContext() {
        return contextHolder.get();
    }

    static void setContext(GrassContext context) {
        contextHolder.compareAndSet(null, context);
    }
}
