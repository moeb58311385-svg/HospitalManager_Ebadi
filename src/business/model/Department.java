package business.model;

import java.util.ArrayList;

public class Department {

    private int id;
    private String naamBakhsh;
    private int zarfiat;
    private ArrayList<Patient> bimaran;
    //private ArrayList<Patient> bimar;
    private ArrayList<Doctor> pezeshkan;

    public Department(int id, String naamBakhsh, int zarfiat) {
        this.id = id;
        this.naamBakhsh = naamBakhsh;
        this.zarfiat = zarfiat;
        this.bimaran = new ArrayList<>();
        this.pezeshkan = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNaamBakhsh() {
        return naamBakhsh;
    }

    public int getZarfiat() {
        return zarfiat;
    }

    public ArrayList<Patient> getBimaran() {
        return bimaran;
    }

    public ArrayList<Doctor> getPezeshkan() {
        return pezeshkan;
    }

    public boolean zarfiatDarad() {
        return bimaran.size() < zarfiat;
    }

    public boolean addBimar(Patient patient) {
        if (patient == null) {
            return false;
        }

        if (!zarfiatDarad()) {
            return false;
        }

        bimaran.add(patient);
        patient.setBastari(true);
        return true;
    }

    public boolean tarkhisBimar(Patient patient) {
        if (patient == null) {
            return false;
        }

        boolean natije = bimaran.remove(patient);

        if (natije) {
            patient.setBastari(false);
            return true;
        }

        return false;
    }

    public boolean hameTarkhisShodand() {
        return bimaran.isEmpty();
    }

    public void addDoctor(Doctor doctor) {
        if (doctor != null) {
            pezeshkan.add(doctor);
        }
    }

    @Override
    public String toString() {
        return naamBakhsh + " | " + bimaran.size() + " / " + zarfiat;
    }
}