package pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.confirm;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.currentFrameUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.codeborne.selenide.SelenideElement;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AlertsWindowsPage {
  private SelenideElement alertsWindowsPage = $("div.home-body > div > div:nth-child(3)"),
      alertsPage = $x("//span/text()[. ='Alerts']/ancestor::li"),
      windowsPage = $x("//span/text()[. ='Browser Windows']/ancestor::li"),
      newTab = $("#tabButton"),
      alertBtn = $("#timerAlertButton");

  public AlertsWindowsPage openAlertsWindowsPage() {
    alertsWindowsPage.click();
    return this;
  }

  public AlertsWindowsPage openAlertsPage() {
    alertsPage.click();
    return this;
  }

  public AlertsWindowsPage openWindowsPage() {
    windowsPage.click();
    return this;
  }

  public AlertsWindowsPage openNewTab() {
    newTab.click();
    return this;
  }

  public AlertsWindowsPage switchWindow(int value) {
    switchTo().window(value);
    return this;
  }

  public AlertsWindowsPage checkCurrentURL() {
    assertEquals("https://demoqa.com/sample", currentFrameUrl());
    return this;
  }

  public AlertsWindowsPage createAlert() {
    alertBtn.click();
    return this;
  }

  public AlertsWindowsPage checkAlertText() {
    assertEquals("This alert appeared after 5 seconds",
        (new WebDriverWait(getWebDriver(), Duration.ofSeconds(10)))
            .until(ExpectedConditions.alertIsPresent()).getText());
    confirm();
    return this;
  }

}
