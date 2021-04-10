package com.nusan.nusanapi.model;

import javax.persistence.*;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private Integer phoneNumber;

    @Override
    public String toString(){
        return "Client [id="+id+"name="+name+"lastName="+lastName+"email="+email+"address="+address+"phoneNumber="+phoneNumber+"]";
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

    public String getAddress(){
        return address;
    }

    public Integer getPhoneNumber(){
        return phoneNumber;
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

    public void setEmail(String email){
        this.email=email;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public void setPhoneNumber(Integer phoneNumber){
        this.phoneNumber=phoneNumber;
    }

}