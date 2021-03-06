package com.saucedemo;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class PerformanceExampleTests {

    private SauceSession session;
    private SauceOptions sauceOptions;

    @After
    public void tearDown() {
        session.stop(true);
    }

    @Test
    public void simplePerformanceTest() {
        //This is the easiest way to run a performance test in Sauce
        //Let Sauce do all the work and keep track of the baseline
        //and the regressions
        sauceOptions = new SauceOptions();
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setCapturePerformance(true);

        session = new SauceSession(sauceOptions);
        WebDriver driver = session.start();
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void specificMetricsTests() {
        //If you want, you can assert on specific metrics
        sauceOptions = new SauceOptions();
        sauceOptions.setExtendedDebugging(true);
        sauceOptions.setCapturePerformance(true);
        session = new SauceSession(sauceOptions);

        HashMap<String, Object> metrics = new HashMap<>();
        metrics.put("type", "sauce:performance");
        WebDriver driver = session.start();
        driver.get("https://www.saucedemo.com");

        Map<String, Object> perfMetrics = (Map<String, Object>) ((JavascriptExecutor)driver).executeScript("sauce:log", metrics);
        Integer loadTime = Integer.parseInt(perfMetrics.get("load").toString());
        Assert.assertTrue(loadTime < 1000);
    }
}
