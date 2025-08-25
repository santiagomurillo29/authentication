package co.com.bancolombia.model.user.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class UserModel {
    private String idUser;
    private String nameUser;
    private String lastNameUser;
    private String documentUser;
    private LocalDate birthdayUser;
    private String addressUser;
    private String phoneUser;
    private String emailUser;
    private BigDecimal baseSalaryUser;

    public UserModel(String idUser, String nameUser, String lastNameUser, String documentUser, LocalDate birthdayUser, String addressUser, String phoneUser, String emailUser, BigDecimal baseSalaryUser) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
        this.documentUser = documentUser;
        this.birthdayUser = birthdayUser;
        this.addressUser = addressUser;
        this.phoneUser = phoneUser;
        this.emailUser = emailUser;
        this.baseSalaryUser = baseSalaryUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getDocumentUser() {
        return documentUser;
    }

    public void setDocumentUser(String documentUser) {
        this.documentUser = documentUser;
    }

    public LocalDate getBirthdayUser() {
        return birthdayUser;
    }

    public void setBirthdayUser(LocalDate birthdayUser) {
        this.birthdayUser = birthdayUser;
    }

    public String getAddressUser() {
        return addressUser;
    }

    public void setAddressUser(String addressUser) {
        this.addressUser = addressUser;
    }

    public String getPhoneUser() {
        return phoneUser;
    }

    public void setPhoneUser(String phoneUser) {
        this.phoneUser = phoneUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public BigDecimal getBaseSalaryUser() {
        return baseSalaryUser;
    }

    public void setBaseSalaryUser(BigDecimal baseSalaryUser) {
        this.baseSalaryUser = baseSalaryUser;
    }
}
