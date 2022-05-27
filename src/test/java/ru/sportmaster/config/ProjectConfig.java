package ru.sportmaster.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${client}.properties",
})
public interface ProjectConfig extends Config {

    String client();
    String type();
    @DefaultValue("https://www.sportmaster.ru")
    String baseUrl();
    @DefaultValue("chrome")
    String browser();
    @DefaultValue("101.0")
    String browserVersion();
    @DefaultValue("1920x1080")
    String browserSize();
    String browserMobileView();
    String remoteDriverUrl();
    String videoStorage();

    @Key("deviceName")
    @DefaultValue("Pixel_4")
    String deviceName();
    String platformName();
    @Key("platformVersion")
    @DefaultValue("12.0")
    String platformVersion();
    String user();
    String key();
    String app();
}
