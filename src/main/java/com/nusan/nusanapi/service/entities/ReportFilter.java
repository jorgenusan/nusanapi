package com.nusan.nusanapi.service.entities;

import java.sql.Date;

public class ReportFilter {

    private String state;
    private String priority;
    private Date startDate;
    private Date endDate;
    private Date appointmentDate;
    private String clientDni;
    private String employeeId;
    private String clientId;

    @Override
    public String toString() {
        return "ReportFilter{" +
                "state='" + state + '\'' +
                ", priority='" + priority + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", appointmentDate=" + appointmentDate +
                ", clientDni='" + clientDni + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", clientId='" + clientId + '\'' +
                '}';
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getClientDni() {
        return clientDni;
    }

    public void setClientDni(String clientDni) {
        this.clientDni = clientDni;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
