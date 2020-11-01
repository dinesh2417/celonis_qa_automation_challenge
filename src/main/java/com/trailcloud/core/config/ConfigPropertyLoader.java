package com.trailcloud.core.config;

import groovyjarjarpicocli.CommandLine;
import lombok.extern.slf4j.Slf4j;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;

import java.io.File;
import java.nio.file.Paths;

/**
 * Class to read property file
 */
@Slf4j
public class ConfigPropertyLoader {

    private static String systemEnv = System.getProperty("test.env");
    private String userEnv = loadDefaultApplicationProperties().getProperty("env", String.class);
    private static String path = "src/main/resources";
    private static File file = new File(path);
    private static String absolutePath = file.getAbsolutePath();
    public static String propValue;

    public static ConfigPropertyLoader getInstance() {
        return new ConfigPropertyLoader();
    }


    public ConfigurationProvider loadProperties(String propertyFileName) {
        ConfigFilesProvider configFilesProvider = () -> Paths.get(propertyFileName);
        Environment environment = new ImmutableEnvironment(absolutePath);
        ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);
        return new ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .withEnvironment(environment)
                .build();
    }

    public ConfigurationProvider loadDefaultApplicationProperties() {

        return loadProperties("application.properties");
    }

    public ConfigurationProvider loadEnvApplicationProperties() {
        try {
            String propertyFileName;
            if (systemEnv != null) {
                propertyFileName = "application-" + systemEnv + ".properties";
            } else {
                propertyFileName = "application-" + userEnv + ".properties";
            }
            ConfigFilesProvider configFilesProvider = () -> Paths.get(propertyFileName);
            Environment environment = new ImmutableEnvironment(absolutePath);
            ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);
            return new ConfigurationProviderBuilder()
                    .withConfigurationSource(source)
                    .withEnvironment(environment)
                    .build();
        } catch (Exception e) {
            log.error("Environment given in not valid");
            throw new CommandLine.InitializationException("Unexpected environment given");
        }
    }

    public String getApplicationPropertyValue(String propName) {
        ConfigurationProvider envProvider = loadEnvApplicationProperties();
        ConfigurationProvider defaultProvider = loadDefaultApplicationProperties();
        try {
            if (envProvider.allConfigurationAsProperties().containsKey(propName)) {
                propValue = envProvider.getProperty(propName, String.class);
            } else {
                propValue = defaultProvider.getProperty(propName, String.class);
            }
            return propValue;
        } catch (Exception e) {
            log.error("Given property name not found in the property files");
        }
        return null;
    }

    public ConfigurationProvider loadPropertiesOf(String fileName) {

        return loadProperties(fileName);
    }

    public String getPropertyValue(String fileName, String propName) {
        ConfigurationProvider configurationProvider = loadPropertiesOf(fileName + ".properties");
        propValue = configurationProvider.getProperty(propName, String.class);
        return propValue;
    }
}
