package com.example.maxcrm.MaxCrm.Dao.HIS;

import java.util.List;

public class ApiInnerData {
    public List<AppointmentDao> getUnconfirmed_appointments() {
        return unconfirmed_appointments;
    }

    public void setUnconfirmed_appointments(List<AppointmentDao> unconfirmed_appointments) {
        this.unconfirmed_appointments = unconfirmed_appointments;
    }

    public List<AppointmentDao> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentDao> appointments) {
        this.appointments = appointments;
    }

    private List<AppointmentDao> unconfirmed_appointments;
    private List<AppointmentDao> appointments;


}
