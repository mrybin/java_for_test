package ru.fortesting.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class NavigationHelper extends HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
      click(By.linkText("groups"));
    }

    public void selectData() {
      click(By.name("selected[]"));
    }

    public void goToHomePage() {
      click(By.linkText("home page"));
    }

    public void goToAddNewPage() {
      click(By.linkText("add new"));
    }

    public void goToHome() {
      click(By.linkText("home"));
    }
}
