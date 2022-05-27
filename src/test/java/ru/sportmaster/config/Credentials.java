package ru.sportmaster.config;

import org.aeonbits.owner.ConfigFactory;

public class Credentials {
    public static ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

    public static boolean isWebMobile() {
        return config.browserMobileView() != null;
    }

    public static boolean isMobile() {
        return config.type().equals("mobile");
    }
    public static boolean isApiTest() {
        return config.type().equals("api");
    }

    public static boolean isRemoteWebDriver() {
        return config.client().equals("remoteBrowser");
    }

    public static boolean isVideoOn() {
        return config.videoStorage() != null;
    }

    public static boolean isBrowserStack() {
        return config.client().equals("browserstack");
    }
}
