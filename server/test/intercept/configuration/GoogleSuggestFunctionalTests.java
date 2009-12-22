package intercept.configuration;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.util.List;

public class GoogleSuggestFunctionalTests {

    public static void main(String[] args) {
        new GoogleSuggestFunctionalTests().spinGoogle();
    }

    @Test
    public void spinGoogle() {
        WebDriver driver = null;// = new FirefoxDriver();
        try {
            // The Firefox driver supports javascript
            driver = getFirefoxDriver();

            // Go to the Google Suggest home page
            driver.get("http://www.google.com/webhp?complete=1&hl=en");

            // Enter the query string "Cheese"
            WebElement query = driver.findElement(By.name("q"));
            query.sendKeys("Cheese");

            // Sleep until the div we want is visible or 5 seconds is over
            long end = System.currentTimeMillis() + 5000;
            while (System.currentTimeMillis() < end) {
                // Browsers which render content (such as Firefox and IE) return "RenderedWebElements"
                RenderedWebElement resultsDiv = (RenderedWebElement) driver.findElement(By.className("gac_m"));

                // If results have been returned, the results are displayed in a drop down.
                if (resultsDiv.isDisplayed()) {
                    break;
                }
            }

            // And now list the suggestions
            List<WebElement> allSuggestions = driver.findElements(By.xpath("//td[@class='gac_c']"));

            for (WebElement suggestion : allSuggestions) {
                System.out.println(suggestion.getText());
            }
        } finally {
            if (driver != null) {
                driver.quit();
            }
//            driver.close();
        }
    }

    private FirefoxDriver getFirefoxDriver() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.proxy.http", "proxy.tradermedia.co.uk");
        profile.setPreference("network.proxy.http_port", 8080);
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.no_proxies_on", "localhost");
        
        return new FirefoxDriver(profile);
    }
}
