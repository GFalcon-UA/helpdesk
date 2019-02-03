/*
 *  MIT License
 * -----------
 *
 * Copyright (c) 2016-2019 Oleksii V. KHALIKOV, PE (gfalcon.com.ua)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package ua.com.gfalcon.helpdesk.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;



public class MainListener implements ServletContextListener
//        , ServletContextAttributeListener
//        , HttpSessionListener
//        , HttpSessionAttributeListener
//        , HttpSessionActivationListener
//        , HttpSessionBindingListener
//        , ServletRequestListener
//        , ServletRequestAttributeListener
{

    private final static Logger log = LogManager.getLogger(MainListener.class);

    ///////////////////////////////////
    // ServletContextListener
    ///////////////////////////////////


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info("Context created");
    }


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.info("Context destroyed");
    }

    ///////////////////////////////////
    // ServletContextAttributeListener
    ///////////////////////////////////

//    @Override
//    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
//
//    }
//
//    @Override
//    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
//
//    }
//
//    @Override
//    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
//
//    }

    ///////////////////////////////////
    // HttpSessionListener
    ///////////////////////////////////

//    @Override
//    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//
//    }
//
//    @Override
//    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
//
//    }

    ///////////////////////////////////
    // HttpSessionActivationListener
    ///////////////////////////////////

//    @Override
//    public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
//
//    }
//
//    @Override
//    public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
//
//    }

    ///////////////////////////////////
    // HttpSessionAttributeListener
    ///////////////////////////////////

//    @Override
//    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
//
//    }
//
//    @Override
//    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
//
//    }
//
//    @Override
//    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
//
//    }

    ///////////////////////////////////
    // HttpSessionBindingListener
    ///////////////////////////////////

//    @Override
//    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
//
//    }
//
//    @Override
//    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
//
//    }

    ///////////////////////////////////
    // ServletRequestAttributeListener
    ///////////////////////////////////

//    @Override
//    public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//
//    }
//
//    @Override
//    public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//
//    }
//
//    @Override
//    public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
//
//    }

    ///////////////////////////////////
    // ServletRequestListener
    ///////////////////////////////////

//    @Override
//    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
//
//
//    }
//
//    @Override
//    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
//
//    }
}
