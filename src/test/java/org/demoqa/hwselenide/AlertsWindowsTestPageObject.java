package org.demoqa.hwselenide;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pages.AlertsWindowsPage;

public class AlertsWindowsTestPageObject extends TestBase {

  AlertsWindowsPage alertsWindowsPage = new AlertsWindowsPage();
  protected static final Logger LOGGER = LogManager.getLogger();

  @Test
  @DisplayName("Открытие и переход на новую вкладку")
  void successfulOpenNewTab() {;
    alertsWindowsPage.openAlertsWindowsPage()
        .openWindowsPage()
        .openNewTab()
        .switchWindow(1)
        .checkCurrentURL();
    LOGGER.info("Switch current tab to new");
  }

  @Test
  @DisplayName("Взаимодействие с алертом")
  void successfulAlertDisplay() {
    alertsWindowsPage.openAlertsWindowsPage()
        .openAlertsPage()
        .createAlert()
        .checkAlertText();
    LOGGER.info("Alert displayed");
  }

}
