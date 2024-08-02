package org.demoqa.hwselenide;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.WebTablesPage;

public class WebTablesPageObject extends TestBase {

  WebTablesPage webTablesPage = new WebTablesPage();
  protected static final Logger LOGGER = LogManager.getLogger();

  @Test
  @DisplayName("Добавление новой строки в таблицу при помощи формы")
  void successfulAddNewRecord() {
    webTablesPage.openElementsPage()
        .openRadioPage()
        .addNewRecord()
        .setFirstName("Stanislav")
        .setLastName("Strelkov")
        .setMail("testEmail@mail.ru")
        .setAge("24")
        .setSalary("90000")
        .setDepartment("QA")
        .submitBtn()
        .checkNewRecord("Stanislav");

    LOGGER.info("New record added");
  }

  @Test
  @DisplayName("Форма регистрации осталась открытой из-за незаполнения обязательных полей")
  void registrationFormNotClose() {
    webTablesPage.openElementsPage()
        .openRadioPage()
        .addNewRecord()
        .setFirstName("Stas")
        .submitBtn()
        .checkRegistrFormOpen();

    LOGGER.info("Registration Form still open");
  }

  @Test
  @DisplayName("Закрытие формы регистрации нажатием кнопки Esc")
  void registrationFormCloseByEsc() {
    webTablesPage.openElementsPage()
        .openRadioPage()
        .addNewRecord()
        .sendKeyEscape()
        .checkRegistrFormClose();

    LOGGER.info("Registration Form closed");
  }

  @Test
  @DisplayName("Изменение поля возраст у записи c Last Name Gentry")
  void successfulEditAgeInRecord() {
    webTablesPage.openElementsPage()
        .openRadioPage()
        .openEditForm("Gentry")
        .clearAge()
        .setAge("35")
        .submitBtn()
        .checkEditAgeInRecord("Gentry", "35");
    LOGGER.info("Age edited");
  }
}
