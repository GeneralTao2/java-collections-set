package com.endava.internship.collections;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Consumer;


public class StudentSet implements Set<Student> {

    @Getter
    @Setter
    private static class StudentNode {
        private Student student;
        private StudentNode greater;
        private StudentNode lesser;

        public StudentNode(Student student) {
            this.student = student;
        }

    }

    public class StudentSetIterator implements Iterator<Student> {
        private final Stack<StudentNode> stack;

        public StudentSetIterator(StudentNode node) {
            stack = new Stack<>();
            StudentNode currentNode = node;

            while (currentNode != null) {
                stack.push(currentNode);
                currentNode = currentNode.getLesser();
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Student next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Student retVal = stack.peek().getStudent();
            StudentNode tmp = stack.peek().getGreater();
            stack.pop();

            while (tmp != null) {
                stack.push(tmp);
                tmp = tmp.getLesser();
            }
            return retVal;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super Student> action) {
            Iterator.super.forEachRemaining(action);
        }
    }

    private StudentNode tree;

    public boolean recursiveContains(StudentNode node, Student student) {
        if (node == null) {
            return false;
        }
        if (student.equals(node.getStudent())) {
            return true;
        }
        return student.compareTo(node.getStudent()) > 0
                ? recursiveContains(node.getGreater(), student)
                : recursiveContains(node.getLesser(), student);
    }

    public int recursiveCount(StudentNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + recursiveCount(node.getGreater()) + recursiveCount(node.getLesser());
    }

    public StudentNode recursiveAdd(StudentNode node, Student student) {
        if (node == null) {
            return new StudentNode(student);
        }

        if (student.compareTo(node.getStudent()) < 0) {
            node.setLesser(recursiveAdd(node.getLesser(), student));
        } else if (student.compareTo(node.getStudent()) > 0) {
            node.setGreater(recursiveAdd(node.getGreater(), student));
        } else {
            return node;
        }

        return node;
    }

    private Student findSmallestStudent(StudentNode node) {
        return node.getLesser() == null ? node.getStudent() : findSmallestStudent(node.getGreater());
    }

    public StudentNode recursiveRemove(StudentNode node, Student student) {
        if (node == null) {
            return null;
        }
        if (student.equals(node.getStudent())) {
            if (node.getLesser() == null && node.getGreater() == null) {
                return null;
            }
            if (node.getLesser() == null) {
                return node.getGreater();
            }
            if (node.getGreater() == null) {
                return node.getLesser();
            }
            Student smallestStudent = findSmallestStudent(node.getGreater());
            node.setStudent(smallestStudent);
            node.setGreater(recursiveRemove(node.getGreater(), smallestStudent));
            return node;
        }
        if (student.compareTo(node.getStudent()) < 0) {
            node.setLesser(recursiveRemove(node.getLesser(), student));
        }
        node.setGreater(recursiveRemove(node.getGreater(), student));
        return node;
    }

    @Override
    public int size() {
        return recursiveCount(tree);
    }

    @Override
    public boolean isEmpty() {
        return tree.getStudent() == null;
    }

    @Override
    public boolean contains(Object o) {
        Student student = (Student) o;
        return recursiveContains(tree, student);
    }

    @Override
    public Iterator<Student> iterator() {
        return new StudentSetIterator(tree);
    }

    @Override
    public Object[] toArray() {
        //TODO: Does not provide certain sequence
        List<Student> list = new ArrayList<>();
        for (Student student : this) {
            list.add(student);
        }
        return list.toArray();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] ts) {
        List<T> list = new ArrayList<>();
        for (Student student : this) {
            list.add((T)student);
        }
        return list.toArray(ts);
    }

    @Override
    public boolean add(Student student) {
        if (student == null) {
            throw new NullPointerException();
        }
        if (contains(student)) {
            return false;
        } else {
            tree = recursiveAdd(tree, student);
            return true;
        }
    }

    @Override
    public boolean remove(Object o) {
        Student student = (Student) o;
        if (contains(student)) {
            tree = recursiveRemove(tree, student);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        tree = null;
    }

    @Override
    public boolean addAll(Collection<? extends Student> collection) {
        Iterator<? extends Student> iterator = collection.iterator();
        boolean changed = false;
        while (iterator.hasNext()) {
            if (add(iterator.next())) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        //Ignore this for homework
        throw new UnsupportedOperationException();
    }
}
