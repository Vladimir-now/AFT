package ru.aplana.tests.sberbank.form;
//    * Фамилию, Имя, Отчетво, Имя и фамилия на карте, Дату рождения, E-mail, Мобильный телефон

public class Form {

    private String lastName;
    private String firstName;
    private String middleName;
    private String nameCard;
    private String dateBirthday;
    private String email;
    private String phoneNumber;

    public Form(String lastName, String firstName, String middleName, String nameCard, String dateBirthday, String email, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.nameCard = nameCard;
        this.dateBirthday = dateBirthday;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getNameCard() {
        return nameCard;
    }

    public String  getDateBirthday() {
        return dateBirthday;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
