package com.saucedemo;

import com.saucelabs.saucebindings.SauceOptions;
import com.saucelabs.saucebindings.SauceSession;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

@DisplayName("A user of Sauce Demo login page")
public class JUnit5AdvancedTests {
    protected WebDriver driver;
    public Boolean result = false;
    private SauceSession sauceSession;

    @BeforeEach
    public void setup(TestInfo testInfo) {
        var sauceOptions = new SauceOptions();
        sauceOptions.setName(testInfo.getDisplayName());
        sauceSession = new SauceSession(sauceOptions);
        driver = sauceSession.start();
    }
    @AfterEach
    public void teardown() {
        sauceSession.stop(result);
    }
    /**
     * @Tag is a JUnit 5 annotation that defines test method tags that aid in reporting and filtering tests.
     * For more information visit the docs: https://junit.org/junit5/docs/5.0.2/api/org/junit/jupiter/api/Tag.html
     */
    @Tag("w3c-chrome-tests")
    /**
     * @DisplayName is a JUnit 5 annotation that defines test case name.
     *    For more information visit the docs: https://junit.org/junit5/docs/5.0.2/api/org/junit/jupiter/api/DisplayName.html
     */
    @DisplayName("should be able to open the page")
    @Test
    public void JUnit5w3cChromeTest() throws AssertionError {
        driver.navigate().to("https://www.saucedemo.com");
        String getTitle = driver.getTitle();
        Assertions.assertEquals(getTitle, "Swag Labs");
        result = true;
    }
}
