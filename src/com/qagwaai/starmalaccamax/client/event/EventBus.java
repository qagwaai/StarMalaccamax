/**
 * Copyright 2010 Gwt-Platform
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.qagwaai.starmalaccamax.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HandlerRegistration;

/**
 * An interface providing minimal access to an {@link EventHandler} manager.
 * 
 * Rather than being attached to a single object, an EventBus provides a central
 * pathway to send events across the whole application.
 * 
 * @author David pgirardson
 */
public interface EventBus {

    /**
     * 
     * @param <H>
     *            The event handler type
     * @param type
     *            the event type
     * @param handler
     *            the handler
     * @return a registration
     */
    <H extends EventHandler> HandlerRegistration addHandler(Type<H> type, H handler);

    /**
     * 
     * @param event
     *            fire the event
     */
    void fireEvent(GwtEvent<?> event);

    /**
     * 
     * @param <H>
     *            the handler type
     * @param type
     *            the event type
     * @param index
     *            an index into the store
     * @return the event handler for this type
     */
    <H extends EventHandler> H getHandler(Type<H> type, int index);

    /**
     * 
     * @param type
     *            the event type
     * @return the number of handlers for this type
     */
    int getHandlerCount(Type<?> type);

    /**
     * 
     * @param e
     *            the event type
     * @return true if the event has a handler
     */
    boolean isEventHandled(Type<?> e);
}
