
package com.nusan.nusanapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_report")
    private Long id;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name="start_date")
    private Date startDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "ending_date")
    private Date endingDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_apointment")
    private Date dateApointment;

    @Column(name = "priority")
    private String priority;

    @Column(name = "state")
    private String state;

    @Column(name = "machine")
    private String machine;

    @Column(name = "brand")
    private String brand;

    @Column(name = "observations")
    private String observations;

    @Column(name = "payment")
    private Integer payment;

    @Column(name = "payment_method")
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cli_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emp_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employees employees;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endingDate=" + endingDate +
                ", dateApointment=" + dateApointment +
                ", priority='" + priority + '\'' +
                ", state='" + state + '\'' +
                ", machine='" + machine + '\'' +
                ", brand='" + brand + '\'' +
                ", observations='" + observations + '\'' +
                ", payment=" + payment +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", client=" + client +
                ", employees=" + employees +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public Date getDateApointment() {
        return dateApointment;
    }

    public void setDateApointment(Date dateApointment) {
        this.dateApointment = dateApointment;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employees getEmployees() {
        return employees;
    }

    public void setEmployees(Employees employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return id.equals(report.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}