package ir.maktab;

import ir.maktab.managers.ProfManager;
import ir.maktab.managers.StudentManager;
import ir.maktab.models.Prof;
import ir.maktab.models.Student;

import java.util.List;
import java.util.Scanner;

/**
 * Created by nader on 11/18/2017.
 */
public class CLI {
    static Scanner in = new Scanner(System.in);

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void print(List<? extends Object> objects) {
        objects.forEach(CLI::print);
    }

    public static String getInput() {
        return in.next();
    }

    static void addProfessor() {
        print("Name:");
        String profName = getInput();
        print("Address:");
        String profAddress = getInput();
        ProfManager.choose();
        int exist =ProfManager.searchByName(profName);
        if (exist==0) {
            Prof professor = new Prof(profName, profAddress);
            ProfManager.add(professor);
        }
        else
            print("name exist! id: "+exist);

    }

    static void addStudent() {
        print("Name:");
        String studentName = getInput();
        print("Dept:");
        String studentDept = getInput();
        print("SupervisorID:");
        int studentId = in.nextInt();
        StudentManager.choose();
        int exist =StudentManager.searchByName(studentName);
        if (exist==0) {
            Student s = new Student(studentName, studentDept, studentId);
            StudentManager.add(s);
        }
        else
            print("name exist! id: "+exist);
    }

    static void deleteProf() {
        print("id:");
        int id = in.nextInt();
        ProfManager.choose();
        ProfManager.delete(id);
    }

    static void deleteStudent() {
        print("id:");
        int stId = in.nextInt();
        StudentManager.choose();
        StudentManager.delete(stId);
    }

    static void deleteStudentByName() {
        print("name:");
        String stName = getInput();
        StudentManager.choose();
        StudentManager.delete(stName);
    }

    static void showStudentSupervisor() {
        print("student name:");
        String name = getInput();
        StudentManager.choose();
        print(StudentManager.showSupervisor(StudentManager.searchByName(name)).getName());
    }

    public static void mainMenu() {
        System.out.println("1- add Professor\n" +
                "2- add Student\n" +
                "3- show Student's Supervisor\n" +
                "4- delete Student\n" +
                "\n0- exit\n");

        makeDecision();
    }

    static void makeDecision() {
        while (true) {
            while (true) {
                System.out.print("Enter Request Number:");
                int req = in.nextInt();
                switch (req) {
                    case 1:
                        addProfessor();
                        break;

                    case 2:
                        addStudent();
                        break;

                    case 3:
                        showStudentSupervisor();
                        break;

                    case 4:
                        deleteStudentByName();
                        break;

                    case 0:
                        System.exit(1);
                        break;

                    default:
                        System.out.println("wrong!");
                        break;
                }
            }
        }
    }

}
