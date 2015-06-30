package com.wmv.poc.simplefreemarker;

import freemarker.template.Configuration;

/**
 * @author wvergara, created on 6/29/15.
 */
public final class FreeMarkerConfiguration {

    private static FreeMarkerConfiguration instance = new FreeMarkerConfiguration();
    private static Configuration configuration;

    private static final String TEMPLATE_RESOURCES_ROOT = "/";


    public static FreeMarkerConfiguration getInstance() {
        return instance;
    }

    static{
        configuration.setClassForTemplateLoading(getInstance().getClass(), "/");
    }

    private FreeMarkerConfiguration() {
        configuration = new Configuration();

    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
