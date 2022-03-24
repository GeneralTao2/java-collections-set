package com.endava.internship.collections;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Objects;

/**
 * The class that defines the element that will be contained by your collection
 */
@Data
@AllArgsConstructor
public class Student implements Comparable<Student> //TODO consider implementing any interfaces necessary for your collection
{
    private String name;
    private LocalDate dateOfBirth;
    private String details;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return name.equals(student.name) && dateOfBirth.equals(student.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dateOfBirth);
    }

    @Override
    public int compareTo(Student o) {
        return Comparator.comparing(Student::getName)
                .thenComparing(Student::getDateOfBirth)
                .compare(this, o);
    }

    /*
    TODO consider overriding any methods for this object to function properly within a collection:
        1. A student is considered unique by a combination of their name and dateOfBirth
        2. Student names are sorted alphabetically, if two students have the same name, then the older one is
        placed before the younger student in an ordered student list.
    */
}
