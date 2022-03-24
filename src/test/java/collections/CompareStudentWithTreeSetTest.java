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
import java.util.TreeSet;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class CompareStudentWithTreeSetTest {


    Set<Student> students;
    Set<Student> treeSet;
    static Student student1, student2, student3, student4;

    @BeforeClass
    public static void initClass() {
        student1 = new Student(
                "Artur",
                LocalDate.of(2005, 5, 5),
                "asdf"
        );
        student2 = new Student(
                "John",
                LocalDate.of(2004, 4, 4),
                "asdf"
        );
        student3 = new Student(
                "John",
                LocalDate.of(2003, 3, 3),
                "asdf"
        );
        student4 = new Student(
                "Joe",
                LocalDate.of(2002, 2, 2),
                "asdf"
        );
    }

    @Before
    public void init() {
        students = new StudentSet();
        treeSet = new TreeSet<>();
    }

    @After
    public void clear() {
        students = null;
        treeSet = null;
    }

    @Test
    public void iteratorWorks() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        treeSet.addAll(Arrays.asList(student1, student2, student3, student4));

        Iterator<Student> studentIterator = students.iterator();
        Iterator<Student> treeSetIterator = treeSet.iterator();
        assertEquals(treeSetIterator.next(), studentIterator.next());
        assertEquals(treeSetIterator.next(), studentIterator.next());
        assertEquals(treeSetIterator.next(), studentIterator.next());
        assertEquals(treeSetIterator.next(), studentIterator.next());
    }

    @Test
    public void dateOfBirthAreEquals() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        treeSet.addAll(Arrays.asList(student1, student2, student3, student4));

        Iterator<Student> studentIterator = students.iterator();
        Iterator<Student> treeSetIterator = treeSet.iterator();
        assertEquals(treeSetIterator.next().getDateOfBirth(), studentIterator.next().getDateOfBirth());
        assertEquals(treeSetIterator.next().getDateOfBirth(), studentIterator.next().getDateOfBirth());
        assertEquals(treeSetIterator.next().getDateOfBirth(), studentIterator.next().getDateOfBirth());
        assertEquals(treeSetIterator.next().getDateOfBirth(), studentIterator.next().getDateOfBirth());
    }

    @Test
    public void toObjectArray() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        treeSet.addAll(Arrays.asList(student1, student2, student3, student4));
        assertArrayEquals(students.toArray(), treeSet.toArray());
    }

    @Test
    public void toGenericArray() {
        students.addAll(Arrays.asList(student1, student2, student3, student4));
        treeSet.addAll(Arrays.asList(student1, student2, student3, student4));
        Student[] studentArray = new Student[10];
        assertArrayEquals(students.toArray(studentArray), treeSet.toArray(studentArray));
    }
}
