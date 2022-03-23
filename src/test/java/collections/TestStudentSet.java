package collections;

import com.endava.internship.collections.Student;
import com.endava.internship.collections.StudentSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;


public class TestStudentSet {
    Student student1 = new Student(
            "Artur",
            LocalDate.of(2005, 5, 5),
            "asdf"
    );
    Student student2 = new Student(
            "John",
            LocalDate.of(2004, 4, 4),
            "asdf"
    );
    Student student3 = new Student(
            "John",
            LocalDate.of(2003, 3, 3),
            "asdf"
    );
    Student student4 = new Student(
            "Joe",
            LocalDate.of(2002, 2, 2),
            "asdf"
    );

    Set<Student> students;

    @Before
    public void init() {
        students = new StudentSet();
    }

    @After
    public void clear() {
        students = null;
    }

    @Test
    public void containsWorks() {
        students.addAll(Arrays.asList(student1, student2, student3));
        assertTrue(students.contains(student2));
        assertFalse(students.contains(student4));
    }

    @Test
    public void addWorks() {
        assertTrue(students.add(student1));
        assertTrue(students.add(student2));
        assertTrue(students.add(student3));
    }

    @Test
    public void addAllWorks() {
        assertTrue(students.addAll(Arrays.asList(student1, student2, student3)));
        assertTrue(students.addAll(Arrays.asList(student1, student2, student3, student4)));
        assertFalse(students.addAll(Arrays.asList(student1, student2, student3, student4)));
        assertTrue(students.contains(student2));
    }

    @Test
    public void removeWorks() {
        students.addAll(Arrays.asList(student1, student2, student3));
        assertTrue(students.remove(student2));
        assertFalse(students.contains(student2));
        assertFalse(students.remove(student4));
        assertTrue(students.contains(student1));
        assertTrue(students.contains(student3));
    }

    @Test
    public void sizeWorks() {
        students.addAll(Arrays.asList(student1, student2, student3));
        assertEquals(3, students.size());
        students.add(student4);
        assertEquals(4, students.size());
    }

    @Test
    public void clearWorks() {
        students.addAll(Arrays.asList(student1, student2, student3));
        students.clear();
        assertEquals(0, students.size());
    }

    @Test
    public void toObjectArray() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        assertArrayEquals(students.toArray(), new Object[] {student1, student4, student3, student2});
    }

    @Test
    public void toGenericArray() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        assertArrayEquals(students.toArray(new Object[]{}), new Object[] {student1, student4, student3, student2});
    }

    @Test
    public void iteratorWorks() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        Iterator<Student> iterator = students.iterator();
        assertEquals(student1, iterator.next());
        assertEquals(student4, iterator.next());
        assertEquals(student3, iterator.next());
        assertEquals(student2, iterator.next());
    }



}
