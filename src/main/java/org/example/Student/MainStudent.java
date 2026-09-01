package org.example.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainStudent {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Ваниева Зина", "Группа 1", 1,
                Arrays.asList(4, 5, 4, 3, 5)));
        students.add(new Student("Тебенёв Максим", "Группа 1", 1,
                Arrays.asList(2, 3, 2, 4, 2)));
        students.add(new Student("Корниенко Ксения", "Группа 2", 1,
                Arrays.asList(5, 5, 5, 4, 5)));
        students.add(new Student("Загоруйко Наталья", "Группа 2", 2,
                Arrays.asList(3, 3, 4, 3, 3)));
        students.add(new Student("Подубный Сергей", "Группа 1", 2,
                Arrays.asList(2, 2, 3, 2, 2)));
        StudentService service = new StudentService();

        System.out.println("Исходный список студентов:");
        for (Student student : students) {
            System.out.println(student);
        }

        service.removeStudentsWithLowAverage(students);
        System.out.println("\nПосле удаления студентов со средним баллом < 3:");
        for (Student student : students) {
            System.out.println(student);
        }

        service.promoteStudents(students);
        System.out.println("\nПосле перевода студентов на следующий курс:");
        for (Student student : students) {
            System.out.println(student);
        }

        Set<Student> studentSet = new HashSet<>(students);
        System.out.println();
        service.printStudents(studentSet, 2);
        service.printStudents(studentSet, 3);
    }
}
