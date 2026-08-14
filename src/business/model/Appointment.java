package business.model;

import java.time.LocalDate;

public class Appointment {

    private int id;
    private Patient bimar;
    private Doctor doctor;
    private LocalDate tarikh;
    //private Doc doctor;
    //private Date tarikh;
    private int saat;
    private boolean anjamShode;
    
    //برای ویزیت تعریف کردم
    public Appointment(int id, Patient bimar, Doctor doctor, LocalDate tarikh, int saat) {
        this.id = id;
        this.bimar = bimar;
        this.doctor = doctor;
        this.tarikh = tarikh;
        this.saat = saat;
        this.anjamShode = false;
    }

    public int getId() {
        return id;
    }

    
    
    public Patient getBimar() {
        return bimar;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    
    
    public LocalDate getTarikh() {
        return tarikh;
    }

    public int getSaat() {
        return saat;
    }

    public boolean isAnjamShode() {
        return anjamShode;
    }

    public void anjamVizit() {
        this.anjamShode = true;
    }
}