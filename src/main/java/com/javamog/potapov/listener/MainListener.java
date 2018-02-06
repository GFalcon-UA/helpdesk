package com.javamog.potapov.listener;

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
