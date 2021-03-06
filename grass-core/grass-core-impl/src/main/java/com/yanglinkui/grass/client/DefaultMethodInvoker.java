/**
 * Copyright 2012-2019 The Feign Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.yanglinkui.grass.client;

import com.yanglinkui.grass.Invoker;
import com.yanglinkui.grass.InvokerAttributes;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class DefaultMethodInvoker implements Invoker {

    private final MethodHandle unboundHandle;

    private MethodHandle handle;

    public DefaultMethodInvoker(Method defaultMethod) {
        try {
            Class<?> declaringClass = defaultMethod.getDeclaringClass();
            Field field = Lookup.class.getDeclaredField("IMPL_LOOKUP");
            field.setAccessible(true);
            Lookup lookup = (Lookup) field.get(null);

            this.unboundHandle = lookup.unreflectSpecial(defaultMethod, declaringClass);
        } catch (NoSuchFieldException ex) {
            throw new IllegalStateException(ex);
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void bindTo(Object proxy) {
        if (handle != null) {
            throw new IllegalStateException(
                    "Attempted to rebind a default method handler that was already bound");
        }
        handle = unboundHandle.bindTo(proxy);
    }

    @Override
    public Object invoke(Object[] argv) throws Throwable {
        if (handle == null) {
            throw new IllegalStateException(
                    "Default method handler invoked before proxy has been bound.");
        }
        return handle.invokeWithArguments(argv);
    }

    @Override
    public InvokerAttributes getAttributes() {
        return null;
    }
}
