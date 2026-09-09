package org.example.Student;

import java.util.List;
import java.util.Set;
import java.util.Iterator;

public class StudentService {

    public void removeStudentsWithLowAverage (List <Student> students) {
        Iterator <Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getAverageGrade() < 3.0) {
                iterator.remove();
            }
        }
    }

    public void promoteStudents(List<Student> students) {
        for (Student student : students) {
            if (student.getAverageGrade() >= 3.0) {
                student.setCourse(student.getCourse() + 1);
            }
        }
    }

    public void printStudents(Set<Student> students, int course) {
        System.out.println("Студенты на " + course + " курсе:");
        boolean found = false;
        for (Student student : students) {
            if (student.getCourse() == course) {
                System.out.println(student.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Студентов на " + course + " курсе не найдено.");
        }
    }
}
