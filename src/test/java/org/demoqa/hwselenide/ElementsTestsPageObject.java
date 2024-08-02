package org.demoqa.hwselenide;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.codeborne.selenide.Condition;
import java.time.Duration;
import java.util.stream.Stream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Keys;
import pages.ElementsPages;

public class ElementsTestsPageObject extends TestBase {

  protected static final Logger LOGGER = LogManager.getLogger();
  ElementsPages elementsPages = new ElementsPages();

  @Test
  @DisplayName("Успешное нажатие элемента radio button")
  void successfulRadioBtnClick() {
    elementsPages.openElementsPage()
        .openRadioPage()
        .buttonLikeSite()
        .checkRadBtnClick();
    LOGGER.info("Radio button successfully verified");
  }

  static Stream<String[]> provideData() {
    return Stream.of(
        new String[]{"Strelkov Stas", "test@mail.ru", "new street, 111, 123"},
        new String[]{"Nikita", "test2@m.com", "Old Town Road, 4"}
    );
  }

  @ParameterizedTest
  @MethodSource("provideData")
  @DisplayName("Заполнение текстовых полей")
  void fillingTextBox(String inputName, String inputMail, String inputAddress) {
    elementsPages.openElementsPage()
        .openTextPage()
        .setFullName(inputName)
        .setEmail(inputMail)
        .setAddress(inputAddress)
        .submitTextBox()
        .checkFullName(inputName)
        .checkMail(inputMail)
        .checkAddress(inputAddress);
    LOGGER.info("Text boxes successfully verified");
  }
}
