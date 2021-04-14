package com.nusan.nusanapi.model;


import javax.persistence.Entity;
import javax.persistence.*;
import java.util.Objects;

@Entity(name = "employees")
public class Employees {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_employee")
    private Integer idEmployee;

    @Column(name = "name_emp")
    private String nameEmp;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dni")
    private String dni;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "status_emp")
    private Integer statusEmp;

    @Column(name = "password_emp")
    private String passwordEmp;

    @Override
    public String toString() {
        return "Employees{" +
                "idEmployee=" + idEmployee +
                ", nameEmp='" + nameEmp + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", statusEmp=" + statusEmp +
                ", passwordEmp='" + passwordEmp + '\'' +
                '}';
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getNameEmp() {
        return nameEmp;
    }

    public void setNameEmp(String nameEmp) {
        this.nameEmp = nameEmp;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatusEmp() {
        return statusEmp;
    }

    public void setStatusEmp(Integer statusEmp) {
        this.statusEmp = statusEmp;
    }

    public String getPasswordEmp() {
        return passwordEmp;
    }

    public void setPasswordEmp(String passwordEmp) {
        this.passwordEmp = passwordEmp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return idEmployee.equals(employees.idEmployee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee);
    }
}
