package com.lashou.service.sms.biz.message.config.impl;


import com.lashou.service.sms.biz.message.config.Configuration;
import com.lashou.service.sms.biz.message.sms.exception.InvalidArgumentException;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Created by sher on 2/29/16.
 */
public class ConfigurationManager {

    private Logger logger = Logger.getLogger(ConfigurationManager.class);

    private Configuration configuration;
    private final String fileName = "properties/inner.config";


    //TODO how to scan classes file to load config
    public Configuration loadConfig(String fileName) throws IOException, InvalidArgumentException {
        ConfigurationBuildFactory factory = new ConfigurationBuildFactory();
        Configuration configuration = factory.buildPropertiesConfig(fileName);
        return configuration;
    }

    public Configuration getConfiguration(){
        try {
            this.configuration = loadConfig(fileName);
            configuration.loadContainer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        }
        return configuration;
    }


}
