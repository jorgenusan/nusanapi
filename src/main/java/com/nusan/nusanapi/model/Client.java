package com.nusan.nusanapi.model;

import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idClient")
    private Long id;

    @Column(name="name_cli")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dni")
    private String dni;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name="city")
    private String city;

    @Override
    public String toString(){
        return "Client [id="+id+"name="+name+"lastName="+lastName+"email="+email+
                "dni="+dni+"phoneNumber="+phoneNumber+"city="+city+"address="+address+"]";
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getDni() {
        return dni;
    }

    public Integer getPhoneNumber(){
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }

    public String getAddress(){
        return address;
    }

    public void setId(Long id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public void setPhoneNumber(Integer phoneNumber){
        this.phoneNumber=phoneNumber;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address){
        this.address=address;
    }

}