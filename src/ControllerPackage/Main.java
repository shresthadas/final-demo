//package ControllerPackage;
//
//import java.util.Scanner;
//import DAOPackage.AppointmentSystem;
//import EntityPackage.Doctor;
//import EntityPackage.InvalidAppointmentException;
//import EntityPackage.Patient;
//
//public class Main {
//    public static void main(String[] args) {
//        AppointmentSystem system = new AppointmentSystem();
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            try {
//                System.out.println("\n1. Register Patient\n2. Register Doctor\n3. Book Appointment\n4. Show All Doctors\n5. Show All Appointments\n6. Complete Appointment\n7. Exit");
//                System.out.print("Enter choice: ");
//
//                int choice;
//                try {
//                    choice = Integer.parseInt(sc.nextLine());
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Please enter a number from 1 to 7.");
//                    continue;
//                }
//
//                switch (choice) {
//                    case 1:
//                        System.out.print("Enter Patient Name: ");
//                        String pname = sc.nextLine();
//                        system.registerPatient(pname);
//                        break;
//
//                    case 2:
//                        System.out.print("Enter Doctor Name: ");
//                        String dname = sc.nextLine();
//                        system.registerDoctor(dname);
//                        break;
//
//                    case 3:
//                        System.out.print("Enter Numeric Patient ID (digits only, without 'P'): ");
//                        String bid = sc.nextLine();
//                        if (!bid.matches("\\d+")) {
//                            System.out.println("Invalid ID: Must contain only numbers.");
//                            break;
//                        }
//                        Patient p = system.findPatientById("P" + bid);
//                        if (p != null) {
//                            system.bookAppointment(p);
//                        } else {
//                            System.out.println("Patient not found.");
//                        }
//                        break;
//
//                    case 4:
//                        system.showAllDoctors();
//                        break;
//
//                    case 5:
//                        system.showAllAppointments();
//                        break;
//
//                    case 6:
//                        system.showAllAppointments();
//                        System.out.print("Enter appointment index to complete: ");
//                        String indexInput = sc.nextLine();
//                        try {
//                            int idx = Integer.parseInt(indexInput);
//                            system.completeAppointment(idx);
//                        } catch (NumberFormatException e) {
//                            System.out.println("Invalid index. Must be a number.");
//                        }
//                        break;
//
//                    case 7:
//                        System.out.println("Exiting...");
//                        return;
//
//                    default:
//                        System.out.println("Invalid choice. Please enter a number from 1 to 7.");
//                }
//            } catch (IllegalArgumentException e) {
//                System.out.println("Validation Error: " + e.getMessage());
//            } catch (InvalidAppointmentException e) {
//                System.out.println("Error: " + e.getMessage());
//            } catch (Exception e) {
//                System.out.println("Unexpected error: " + e.getMessage());
//            }
//        }
//    }
//}
package ControllerPackage;

import java.util.Scanner;
import DAOPackage.AppointmentSystem;
import EntityPackage.Admin;
import EntityPackage.Doctor;
import EntityPackage.InvalidAppointmentException;
import EntityPackage.Patient;

public class Main {
    public static void main(String[] args)  {
        AppointmentSystem system = new AppointmentSystem();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Are you a:");
            System.out.println("1. Doctor\n2. Patient");
            System.out.print("Choose option (1 or 2): ");
            String roleChoice = sc.nextLine();

            if (roleChoice.equals("1")) {
                // Doctor menu
                while (true) {
                    System.out.println("\nDoctor Menu:");
                    System.out.println("1. Register a Doctor\n2. Show All Appointments\n3. Complete Appointment\n4. Remove a Doctor\n5. Exit");
                    System.out.print("Enter choice: ");
                    String doctorChoice = sc.nextLine();

                    switch (doctorChoice) {
                        case "1":
                            System.out.print("Enter Doctor Name: ");
                            String dname = sc.nextLine();
                            system.registerDoctor(dname);
                            break;

                        case "2":
                            System.out.print("Enter your Doctor ID (e.g., D1001): ");
                            String docId = sc.nextLine();
                            system.showAppointmentsByDoctorId(docId);
                            break;

                        case "3":
                            system.showAllAppointments();
                            System.out.print("Enter appointment index to complete: ");
                            try {
                                int idx = Integer.parseInt(sc.nextLine());
                                system.completeAppointment(idx);
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid index. Must be a number.");
                            }
                            break;

                        case "4":
                            System.out.print("Enter Doctor ID to remove (e.g., D1001): ");
                            String doctorId = sc.nextLine();
                            Admin admin = new Admin("A1", "Admin");
                            admin.removeDoctor(system.doctors, doctorId);
                            break;

                        case "5":
                            System.out.println("Returning to main role selection...");
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter a number from 1 to 5.");
                    }

                    if (doctorChoice.equals("5")) break;
                }

            } else if (roleChoice.equals("2")) {
                // Patient menu
                while (true) {
                    System.out.println("\nPatient Menu:");
                    System.out.println("1. Register a Patient\n2. Book Appointment\n3. Show Appointments\n4. Exit");
                    System.out.print("Enter choice: ");
                    String patientChoice = sc.nextLine();

                    switch (patientChoice) {
                        case "1":
                            System.out.print("Enter Patient Name: ");
                            String pname = sc.nextLine();
                            system.registerPatient(pname);
                            break;

                        case "2":
                            System.out.print("Enter Numeric Patient ID (digits only, without 'P'): ");
                            String bid = sc.nextLine();
                            if (!bid.matches("\\d+")) {
                                System.out.println("Invalid ID: Must contain only numbers.");
                                break;
                            }
                            Patient p = system.findPatientById("P" + bid);
                            if (p != null) {
                                try {
                                    system.bookAppointment(p);
                                } catch (InvalidAppointmentException e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            } else {
                                System.out.println("Patient not found.");
                            }
                            break;

                        case "3":
                            System.out.print("Enter your Patient ID (e.g., P1001): ");
                            String patId = sc.nextLine();
                            system.showAppointmentsByPatientId(patId);
                            break;

                        case "4":
                            System.out.println("Returning to main role selection...");
                            break;

                        default:
                            System.out.println("Invalid choice. Please enter a number from 1 to 4.");
                    }

                    if (patientChoice.equals("4")) break;
                }

            } else {
                System.out.println("Invalid role choice. Please enter 1 or 2.");
            }
        }
    }
}

