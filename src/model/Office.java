package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Office {
    private ArrayList<Employee> employ;
    private Scanner scanner;

    public Office() {
        super();
        this.employ = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void addEmployee(Employee newEmployee) {
        employ.add(newEmployee);
    }

    public void viewData() {
        // sort nama karyawan
        Collections.sort(employ, Comparator.comparing(Employee::getName));
        // output sebagai 'tabel'
        System.out.println("No | Kode Karyawan | Nama Karyawan | Jenis Kelamin  | Jabatan    | Gaji");
        int counter = 1;
        for (Employee employeeToDisplay : employ) {
        					//jaraknya
            System.out.printf("%-3d| %-15s| %-17s| %-15s| %-12s| %.2f%n", counter++,
                    employeeToDisplay.getId(),
                    employeeToDisplay.getName(),
                    employeeToDisplay.getGender(),
                    employeeToDisplay.getPosition(),
                    employeeToDisplay.getSalary());
        }
    }

    public void updateData() {
        // Display data
        viewData();
        //berapa karyawan yg mau diupdate
        System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
        int employeeIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (employeeIndex < 1 || employeeIndex > employ.size()) {
            System.out.println("Nomor urutan tidak valid.");
            return;
        }
        //input data karyawan terbaru
        System.out.print("Input nama karyawan [>= 3] (Input 0 to keep existing): ");
        String name = scanner.nextLine();
        if (name.equals("0")) {
            name = employ.get(employeeIndex - 1).getName();
        }

        System.out.print("Input jenis kelamin [Laki-laki | Perempuan] "
        		+ "(Case Sensitive) (Input 0 to keep existing): ");
        String gender = scanner.nextLine();
        if (gender.equals("0")) {
            gender = employ.get(employeeIndex - 1).getGender();
        }

        System.out.print("Input jabatan [Manager | Supervisor | Admin] "
        		+ "(Case Sensitive) (Input 0 to keep existing): ");
        String position = scanner.nextLine();
        if (position.equals("0")) {
            position = employ.get(employeeIndex - 1).getPosition();
        } else {
            double salary;
            if (position.equals("0")) {
                salary = employ.get(employeeIndex - 1).getSalary();
            } else {
                salary = calculateSalary(position);
            }
        }

        // Update data karyawan
        Employee employeeToUpdate = employ.get(employeeIndex - 1);
        employeeToUpdate.setName(name.length() >= 3 ? name : employeeToUpdate.getName());
        employeeToUpdate.setGender(gender);
        employeeToUpdate.setPosition(position);
        System.out.println("Berhasil mengupdate karyawan dengan id " + employeeToUpdate.getId());
        System.out.println("ENTER to return");
        scanner.nextLine();
    }

    // Gaji sesuai dengan jabatan
    private double calculateSalary(String position) {
        switch (position.toLowerCase()) {
            case "manager":
                return 8000000;
            case "supervisor":
                return 6000000;
            case "admin":
                return 4000000;
            default:
                System.out.println("Invalid position specified.");
                return 0;
        }
    }
    
    public void deleteEmployee() {
        // Display data
        viewData();
        //Berapa karyawan yg mau dihapus
        System.out.print("Input nomor karyawan yang ingin dihapus: ");
        int employeeIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (employeeIndex < 1 || employeeIndex > employ.size()) {
            System.out.println("Nomor urutan tidak valid.");
            return;
        }
        // menghapus karyawan
        Employee employeeToDelete = employ.get(employeeIndex - 1);
        String deletedEmployeeId = employeeToDelete.getId();

        employ.remove(employeeIndex - 1);

        System.out.println("Karyawan dengan kode " + deletedEmployeeId + " berhasil dihapus");
    }

}



