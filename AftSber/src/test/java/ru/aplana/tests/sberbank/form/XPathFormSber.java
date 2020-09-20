package ru.aplana.tests.sberbank.form;


public class XPathFormSber {
    private String lastNameXPath = "//input[@name = \"odc-personal__lastName\"]";
    private String firstNameXPath = "//input[@id = \"odc-personal__firstName\"]";
    private String middleNameXPath = "//input[@id = \"odc-personal__middleName\"]";
    private String nameCardXPath = "//input[@id = \"odc-personal__cardName\"]";
    private String dateBirthdayXPath = "//input[@id = \"odc-personal__birthDate\"]";
    private String emailXPath = "//input[@id = \"odc-personal__email\"]";
    private String phoneNumberXPath = "//input[@id = \"odc-personal__phone\"]";
    private String seriesPassportMessageFieldXPath = "//input[@id = \"odc-personal__series\"]/../div";
    private String numberPassportMessageFieldXPath = "//input[@id = \"odc-personal__number\"]/../div";

    public String getSeriesPassportMessageFieldXPath() {
        return seriesPassportMessageFieldXPath;
    }

    public String getNumberPassportMessageFieldXPath() {
        return numberPassportMessageFieldXPath;
    }

    public String getDateIssuePassportMessageFieldXPath() {
        return dateIssuePassportMessageFieldXPath;
    }

    public String getTickIAgreeMessageFieldXPath() {
        return tickIAgreeMessageFieldXPath;
    }

    private String dateIssuePassportMessageFieldXPath = "//label[@for = \"odc-personal__issueDate\"]/../div[@class = \"odcui-error__text\"]";
    private String tickIAgreeMessageFieldXPath = "//button[@class='odc-personal__agree-button']/../div";


    public String getLastNameXPath() {
        return lastNameXPath;
    }

    public String getFirstNameXPath() {
        return firstNameXPath;
    }

    public String getMiddleNameXPath() {
        return middleNameXPath;
    }

    public String getNameCardXPath() { return nameCardXPath;
    }

    public String getDateBirthdayXPath() {
        return dateBirthdayXPath;
    }

    public String getEmailXPath() {
        return emailXPath;
    }

    public String getPhoneNumberXPath() {
        return phoneNumberXPath;
    }
}

