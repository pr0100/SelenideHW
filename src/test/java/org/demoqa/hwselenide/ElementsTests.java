package org.demoqa.hwselenide;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.stream.Stream;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ElementsTests {

  protected static final Logger LOGGER = LogManager.getLogger();


  @BeforeEach
  void setUp() {
    open("https://demoqa.com/elements");
    getWebDriver().manage().window().maximize();
  }

  @Test
  @DisplayName("Успешное нажатие элемента radio button")
  void successfulRadioBtnClick() {
    $("#item-2").shouldBe(enabled).click();
    executeJavaScript("arguments[0].click();", $("#yesRadio"));
    $("span.text-success").shouldHave(text("Yes"));
    LOGGER.info("Radio button successfully verified");
  }

  static Stream<String[]> provideData() {
    return Stream.of(
        new String[]{"Strelkov Stas", "test@mail.ru", "new street, 111, 123"},
        new String[]{"Nikita", "test2@m.com", ""}
    );
  }

  @Disabled
  @ParameterizedTest
  @MethodSource("provideData")
  @DisplayName("Заполнение текстовых полей")
  void fillingTextBox(String inputName, String inputMail, String inputAddress) {
    $("#item-0").click();

    $("#userName").setValue(inputName);
    $("#userEmail").setValue(inputMail);
    $("#currentAddress").setValue(inputAddress);

    $("#submit").shouldBe(clickable).click();

    $("#name").shouldHave(text("Name:" + inputName));
    $("#email").shouldHave(text("Email:" + inputMail));


    if (!$$("p#currentAddress").isEmpty()) {
      $("p#currentAddress").shouldHave(text("Current Address :" + inputAddress));
    }
    LOGGER.info("Text boxes successfully verified");
  }

}
