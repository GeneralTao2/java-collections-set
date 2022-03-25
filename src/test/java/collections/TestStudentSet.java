package collections;

import com.endava.internship.collections.Student;
import com.endava.internship.collections.StudentSet;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;


public class TestStudentSet {
    Set<Student> students;

    static Student student1, student2, student3, student4;

    @BeforeClass
    public static void initClass() {
        student1 = new Student(
                "Artur",
                LocalDate.of(2005, 5, 5),
                "student1"
        );
        student2 = new Student(
                "John",
                LocalDate.of(2004, 4, 4),
                "student2"
        );
        student3 = new Student(
                "John",
                LocalDate.of(2003, 3, 3),
                "student3"
        );
        student4 = new Student(
                "Joe",
                LocalDate.of(2002, 2, 2),
                "student4"
        );
    }

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
    //TODO: How tos suppress Result of 'students.size()' is always '0' warning?
    //@SuppressWarnings("SameParameterValue")
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

    @Test
    public void toStringWorks() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        String output = "StudentSet{tree=StudentSet.StudentNode(student=Student(name=Artur, dateOfBirth=2005-05-05, details=student1), greater=StudentSet.StudentNode(student=Student(name=John, dateOfBirth=2004-04-04, details=student2), greater=null, lesser=StudentSet.StudentNode(student=Student(name=John, dateOfBirth=2003-03-03, details=student3), greater=null, lesser=StudentSet.StudentNode(student=Student(name=Joe, dateOfBirth=2002-02-02, details=student4), greater=null, lesser=null))), lesser=null)}";
        assertEquals(output, students.toString());
    }

    @Test
    public void iteratorRemoveWorks() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        Iterator<Student> iterator = students.iterator();
        iterator.next();
        iterator.remove();
        iterator.next();
        iterator.remove();
        assertEquals(student3, iterator.next());
        assertEquals(student2, iterator.next());
        assertFalse(students.contains(student1));
        assertFalse(students.contains(student4));

    }

    @Test
    public void containsAllWorks() {
        students.addAll(Arrays.asList(student1, student2, student4));
        assertTrue(students.containsAll(Arrays.asList(student1, student2, student4)));
        assertFalse(students.containsAll(Arrays.asList(student1, student3, student4)));
        assertFalse(students.containsAll(Arrays.asList(student1, student2, student4, student3)));
    }

    @Test
    public void retainsAllWorks() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        assertTrue(students.retainAll(Arrays.asList(student1, student2)));
        assertFalse(students.retainAll(Arrays.asList(student1, student2)));
        assertArrayEquals(Arrays.asList(student1, student2).toArray(), students.toArray());
        assertTrue(students.retainAll(Arrays.asList(student3, student4)));
        assertEquals(0, students.size());
    }

    @Test
    public void removeAllWorks() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        assertTrue(students.removeAll(Arrays.asList(student1, student2)));
        assertFalse(students.removeAll(Arrays.asList(student1, student2)));
        assertArrayEquals(Arrays.asList(student4, student3).toArray(), students.toArray());
        assertTrue(students.removeAll(Arrays.asList(student3, student4)));
        assertEquals(0, students.size());
    }



}
