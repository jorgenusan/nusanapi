
package com.nusan.nusanapi.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_report")
    private Integer idReport;

    @Column(name="start_date")
    private Date startDate;

    @Column(name = "ending_date")
    private Date endingDate;

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

    @Column(name = "id_emp")
    private Integer idEmp;

    @Column(name = "id_cli")
    private Integer idCli;

    @Override
    public String toString() {
        return "Report{" +
                "idReport=" + idReport +
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
                ", idEmp=" + idEmp +
                ", idCli=" + idCli +
                '}';
    }

    public Integer getId() {
        return idReport;
    }

    public void setId(Integer idReport) {
        this.idReport = idReport;
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

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public Integer getIdCli() {
        return idCli;
    }

    public void setIdCli(Integer idCli) {
        this.idCli = idCli;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return idReport.equals(report.idReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReport);
    }
}