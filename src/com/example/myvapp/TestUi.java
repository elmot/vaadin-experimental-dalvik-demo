/*
 * Copyright 2000-2014 Vaadin Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.myvapp;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * Provides various helper methods for connectors. Meant for internal use.
 *
 * @author Vaadin Ltd
 */
public class TestUi extends UI {
    @Override
    protected void init(VaadinRequest request) {
        setContent(new VerticalLayout(new Button("Click me", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("Clicked");
            }
        })));
    }

    @VaadinServletConfiguration(ui = TestUi.class, productionMode = false)
    public static class TestServlet extends VaadinServlet {

    }
}
