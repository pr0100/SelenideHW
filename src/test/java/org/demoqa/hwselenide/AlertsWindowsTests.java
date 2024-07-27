package org.demoqa.hwselenide;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.currentFrameUrl;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AlertsWindowsTests {

  protected static final Logger LOGGER = LogManager.getLogger();

  @BeforeEach
  void setUp() {
    open("https://demoqa.com/alertsWindows");
    getWebDriver().manage().window().maximize();
  }

  @Test
  @DisplayName("Открытие и переход на новую вкладку")
  void successfulOpenNewTab() {;
    $(byXpath("//span/text()[. ='Browser Windows']/ancestor::li")).click();
    $("#tabButton").click();
    switchTo().window(1);
    assertEquals("https://demoqa.com/sample", currentFrameUrl());
    LOGGER.info("Switch current tab to new");
  }


  @Test
  @DisplayName("Взаимодействие с алертом")
  void successfulAlertDisplay() {
    $(byXpath("//span/text()[. ='Alerts']/ancestor::li")).click();
    $("#timerAlertButton").click();
    assertEquals("This alert appeared after 5 seconds",
        (new WebDriverWait(getWebDriver(), Duration.ofSeconds(10)))
        .until(ExpectedConditions.alertIsPresent()).getText());
    confirm();
    LOGGER.info("Alert displayed");
  }
}

