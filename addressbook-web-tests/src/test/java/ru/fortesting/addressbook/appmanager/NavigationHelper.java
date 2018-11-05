package ru.fortesting.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd=wd;
    }

    public void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    public void selectData() {
      wd.findElement(By.name("selected[]")).click();
    }

    public void goToHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void goToAddNewPage() {
      wd.findElement(By.linkText("add new")).click();
    }

    public void goToHome() {
      wd.findElement(By.linkText("home")).click();
    }
}
