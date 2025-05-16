//package DAOPackage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import EntityPackage.Doctor;
//import EntityPackage.InvalidAppointmentException;
//import EntityPackage.Patient;
//import EntityPackage.Appointment;
//
//import java.util.*;
//
//public class AppointmentSystem {
//    private List<Patient> patients = new ArrayList<Patient>();
//    private List<Doctor> doctors = new ArrayList<Doctor>();
//    private List<Appointment> appointments = new ArrayList<Appointment>();
//    private int patientCounter = 1000;
//    private int doctorCounter = 1000;
//
//    public void registerPatient(String name) {
//    	try {
//			String id = "P" + patientCounter++;
//			patients.add(new Patient(id, name));
//			System.out.println("Patient registered with ID: " + id);
//		} catch (IllegalArgumentException e) {//likely because the name doesn't match the expected format), an error message is printed.
//			System.out.println("Error: " + e.getMessage());
//		}
//    }
//
//    public void registerDoctor(String name) {
//    	try {
//        String id = "D" + doctorCounter++;
//        doctors.add(new Doctor(id, name));
//        System.out.println("Doctor registered with ID: " + id);
//    	}catch(IllegalArgumentException e) {//likely because the name doesn't match the expected format), an error message is printed.
//			System.out.println("Error: " + e.getMessage());
//		}
//    }
//
//    public Appointment bookAppointment(Patient patient) throws InvalidAppointmentException {
//        for (Doctor doctor : doctors) {
//            if (doctor.isAvailable()) {
//                Appointment appointment = new Appointment(patient, doctor);
//                appointments.add(appointment);
//                System.out.println("Appointment booked.");
//                return appointment;
//            }
//        }
//        throw new InvalidAppointmentException("No available doctor found.");
//    }
//
//    public void completeAppointment(int index) throws InvalidAppointmentException {
//        if (index < 0 || index >= appointments.size()) {
//            throw new InvalidAppointmentException("Invalid appointment index.");
//        }
//        Appointment appointment = appointments.get(index);
//        if ("Completed".equals(appointment.getStatus())) {
//            throw new InvalidAppointmentException("Appointment already completed.");
//        }
//        appointment.completeAppointment();
//        System.out.println("Appointment marked completed.");
//    }
//
//    public void showAllDoctors() {
//        for (Doctor d : doctors) {
//            d.showProfile();
//        }
//    }
//
//    public List<Doctor> getDoctors() {
//		return doctors;
//	}
//
//	public void setDoctors(List<Doctor> doctors) {
//		this.doctors = doctors;
//	}
//
//	public void showAllAppointments() {
//        for (int i = 0; i < appointments.size(); i++) {
//            System.out.println(i + ": " + appointments.get(i).appointmentDetails());
//        }
//    }
//
//    public Patient findPatientById(String id) {
//        for (Patient p : patients) {
//            if (p.getId().equals(id)) {
//                return p;
//            }
//        }
//        return null;
//    }
//}
package DAOPackage;

import java.util.ArrayList;
import java.util.List;
import EntityPackage.Doctor;
import EntityPackage.InvalidAppointmentException;
import EntityPackage.Patient;
import EntityPackage.Appointment;

public class AppointmentSystem {
    public List<Patient> patients = new ArrayList<Patient>();
     public List<Doctor> doctors = new ArrayList<Doctor>();
     public List<Appointment> appointments = new ArrayList<Appointment>();
     int patientCounter = 1000;
     int doctorCounter = 1000;

    public void registerPatient(String name) {
        try {
            String id = "P" + patientCounter++;
            patients.add(new Patient(id, name));
            System.out.println("Patient registered with ID: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void registerDoctor(String name) {
        try {
            String id = "D" + doctorCounter++;
            doctors.add(new Doctor(id, name));
            System.out.println("Doctor registered with ID: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Appointment bookAppointment(Patient patient) throws InvalidAppointmentException {
        for (Doctor doctor : doctors) {
            if (doctor.isAvailable()) {
                Appointment appointment = new Appointment(patient, doctor);
                appointments.add(appointment);
                System.out.println("Appointment booked.");
                return appointment;
            }
        }
        throw new InvalidAppointmentException("No available doctor found.");
    }

    public void completeAppointment(int index) throws InvalidAppointmentException {
        if (index < 0 || index >= appointments.size()) {
            throw new InvalidAppointmentException("Invalid appointment index.");
        }
        Appointment appointment = appointments.get(index);
        if ("Completed".equals(appointment.getStatus())) {
            throw new InvalidAppointmentException("Appointment already completed.");
        }
        appointment.completeAppointment();
        System.out.println("Appointment marked completed.");
    }

    public void showAllDoctors() {
        for (Doctor d : doctors) {
            d.showProfile();
        }
    }
    

//    public List<Doctor> getDoctorList() {
//        return doctors;
//    }

    public void showAllAppointments() {
        for (int i = 0; i < appointments.size(); i++) {
            System.out.println(i + ": " + appointments.get(i).appointmentDetails());
        }
    }

    public void showAppointmentsByDoctorId(String doctorId) {
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            Appointment a = appointments.get(i);
            if (a.doctor.getId().equals(doctorId)) {
                System.out.println(i + ": " + a.appointmentDetails());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for Doctor ID: " + doctorId);
        }
    }

    public void showAppointmentsByPatientId(String patientId) {
        boolean found = false;
        for (int i = 0; i < appointments.size(); i++) {
            Appointment a = appointments.get(i);
            if (a.patient.getId().equals(patientId)) {
                System.out.println(i + ": " + a.appointmentDetails());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments found for Patient ID: " + patientId);
        }
    }

    public Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
}  
