package ru.hoff.config;

import org.aeonbits.owner.ConfigFactory;

public class Credentials {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    public static boolean isWebMobile() {
        return !config.browserMobileView().equals("");
    }

    public static boolean isRemoteWebDriver() {
        return !config.remoteDriverUrl().equals("");
    }

    public static boolean isVideoOn() {
        return !config.videoStorage().equals("");
    }

    public static boolean isBrowserStack() {
        return System.getProperty("device").equals("browserstack");
    }
}
