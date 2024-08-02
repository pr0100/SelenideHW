package org.demoqa.hwselenide;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;

public class TestBase
{
  @BeforeEach
  void setUp() {
    Configuration.pageLoadStrategy = "eager";
    open("https://demoqa.com/");
    getWebDriver().manage().window().maximize();
  }
}
