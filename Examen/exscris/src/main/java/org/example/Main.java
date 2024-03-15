package org.example;

import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Main {
    public static void main(String[] args) {
//        System.out.println("Hello world!");

//        Polygon r1 = new Rectangle(2, 3);
//        Rectangle r2 = new Square(3);
//        System.out.println(r1 instanceof
//                Rectangle);
//        System.out.println(r2 instanceof
//                Rectangle);
//        System.out.println(r1 instanceof
//                Square);
//        System.out.println(r2 instanceof
//                Polygon);
//        System.out.println(r2.getArea());
//        System.out.println(r1.getArea());




       // # c
//        Square[] squares = {new Square(2), new Square(1), new Square(5)};
//        AreaCalculator<Square> calculator1 = new AreaCalculator<>(squares);
//        double[] areasSquares = calculator1.computeAreas();
//        for (double a: areasSquares)
//            System.out.println(a);
//        Circle[] circles = {new Circle(1), new Circle(2)};
//        AreaCalculator<Circle> calculator2 = new AreaCalculator<>(circles);
//        double[] areasCircles = calculator2.computeAreas();
//        for (double a: areasCircles)
//            System.out.println(a);



        // # d
//        ArrayList<Shape> shapes = new ArrayList<>(Arrays.asList(new Rectangle(1, 2), new
//                Square(1), new Square(2), new Circle(1)));
//        shapes.stream().filter(s -> s instanceof Polygon).sorted(new Comparator<Shape>() {
//            @Override
//            public int compare(Shape o1, Shape o2) {
//                if (o1.getArea() > o2.getArea())
//                    return -1;
//                else if (o1.getArea() < o2.getArea())
//                    return 1;
//                else
//                    return 0;
//            }
//        }).forEach(System.out::println);
//

  /////////////////////


//        Complex c1 = new Complex(1, 2);
//        MyComparable c2 = new Complex(1, 3);
//        System.out.println(c1 + " compareTo " + c2 + ": " + c1.compareTo(c2));
//        System.out.println();
//        c1.draw();
//        System.out.println();
//        System.out.println(c1 + " instance of Complex: " + (c1 instanceof Complex));
//        System.out.println(c1 + " instance of MyComparable: " + (c1 instanceof MyComparable));
//        System.out.println(c1 + " instance of GraphicallyRepresentable: " + (c1 instanceof GraphicallyRepresentable));
//        System.out.println(c1 + " instance of ComplexWithId: " + (c1 instanceof ComplexWithId));
//        Complex cWithId = new ComplexWithId(12, -1, 1);
//
//        if (cWithId instanceof ComplexWithId) {
//            ComplexWithId c3 = (ComplexWithId) cWithId;
//            System.out.println(c3.getId());
//        }
//
//        System.out.println(cWithId + " instance of ComplexWithId: " + (cWithId instanceof ComplexWithId));
//        System.out.println(cWithId + " instance of GraphicallyRepresentable: " + (cWithId instanceof GraphicallyRepresentable));


//////////////////////


//        Pair<Integer, String> p1 = new Pair<>(1, "car");
//        Pair<Integer, String> p2 = new Pair<>(2, "vehicle");
//        System.out.println(Utils.equals(p1, p2));
//
//        Pair<String, String> p3 = new Pair<>("gene", "unit of heredity");
//        Pair<String, String> p4 = new Pair<>("gene", "unit of heredity");
//        System.out.println(Utils.equals(p3, p4));




///////////////
        // Using an anonymous class to implement the Greeting interface
//        Greeting myGreeting = new Greeting() {
//            @Override
//            public void greet() {
//                System.out.println("Hello from the anonymous class!");
//            }
//        };
//
//        // Calling the greet method on the anonymous class instance
//        myGreeting.greet();
///////////


/////////////////

        // Creating an instance of the static nested class
//        OuterClass.StaticNestedClass staticNestedObj = new OuterClass.StaticNestedClass();
//        staticNestedObj.display();
//
//        // Creating an instance of the outer class
//        OuterClass outerObj = new OuterClass();
//        // Creating an instance of the inner (non-static) nested class using the outer class instance
//        OuterClass.InnerClass innerObj = outerObj.new InnerClass();
//        innerObj.display();
///////////////////////////



        ArrayList<Book> books = new ArrayList<>();
        books.add(new Book("Heather Morris", "The Tattooist of Auschwitz", 250));
        books.add(new Book("Andre Agassi", "Open", 300));
        books.add(new Book("Aaron Courville, Ian Goodfellow, Yoshua Bengio", "Deep Learning", 700));

        // sorting - only works if Book implements Comparable
        Collections.sort(books);
        for (Book b : books)
            System.out.println(b);

        System.out.println("--------------------------------------");

        // sort by author
        books.sort(new AuthorComparator());
        for (Book b : books)
            System.out.println(b);

        System.out.println("--------------------------------------");

        // sort by number of pages
        books.sort(new PagesComparator());
        for (Book b : books)
            System.out.println(b);
//////////////////////////


        Map<String, Integer> numbers = new HashMap<>();

        // Adding key-value pairs to a HashMap
        numbers.put("One", 1);
        numbers.put("Two", 2);
        numbers.put("Three", 3);

        for (String key : numbers.keySet())
            System.out.println(key + " " + numbers.get(key));

        System.out.println("-----------------------------------------------");

        Iterator it = numbers.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
        }

        System.out.println("-----------------------------------------------");

        SortedSet<String> names = new TreeSet<>();
        names.add("Mihai");
        names.add("Andreea");
        names.add("Bianca");
        names.add("Ionut");

        System.out.println(names);

        names.add("Mihai"); // will not be added (a set cannot have duplicate values)

        System.out.println(names);

        ////////////////////////

        LinkedList<String> l = new LinkedList<>();

        l.add("one");
        l.add("two");
        l.add("three");

        // LinkedList.ListIterator iterator = l.new ListIterator();
//        LinkedList<String>.ListIterator<String> iterator = l.iterator();
//        while (iterator.valid()) {
//            String elem = iterator.element();
//            System.out.println(elem);
//            iterator.next();
//        }
//
//        ///////////
//
//        LinkedList<String> l1 = new LinkedList<>();
//        l1.add(new String("one"));
//        l1.add(new String("two"));
//        l1.add(new String("three"));
//        printElements(l1);
//        System.out.println("-----------------------------------------------");
//
//        LinkedList<Person> l2 = new LinkedList<>();
//        l2.add(new Person("Ana"));
//        l2.add(new Person("Bianca"));
//        l2.add(new Person("Vlad"));
//        printElements(l2);
//        System.out.println("-----------------------------------------------");
//
//        LinkedList<Student> l3 = new LinkedList<>();
//        l3.add(new Student("Ana", 2));
//        l3.add(new Student("Bianca", 1));
//        l3.add(new Student("Vlad", 3));
//        printElementsUpperBounded(l2);
//        printElementsUpperBounded(l3);
//        //printElementsUpperBounded(l1); // ERROR: String does not inherit from Person!
//        System.out.println("-----------------------------------------------");
//
//        printElementsLowerBounded(l3);
//        printElementsLowerBounded(l2);
//        LinkedList<StudentWithScolarship> l4 = new LinkedList<>();
//        l4.add(new StudentWithScolarship("Ana", 2, 700));
//        l4.add(new StudentWithScolarship("Vlad", 3, 700));
//
//        printElementsUpperBounded(l4);

//        printElementsLowerBounded(l4); // ERROR


//
//        Circle2 c1 = new Circle2("my circle1", 1);
//        Circle2 c2 = new Circle2("my circle2", 1);
//        System.out.println(c1 == c1);
//        System.out.println(c2 == c2);
//        System.out.println(c1 == c2);
//        System.out.println(c1.equals(c1));
//        System.out.println(c1.equals(c2));
//
//        System.out.println(c1);
//



//        Rational r1 = new Rational(1, 2);
//        Rational r2 = new Rational(3);
//        System.out.println(r1);
//        System.out.println(r2);
//        System.out.println(r1 == r2);
//        Rational r3 = null; // r3 has no object associated
//
//        r1 = r2; // both r1 and r2 refer the same object
//        System.out.println(r1 == r2);
//        System.out.println(r1);
//        System.out.println(r2);
//
//        Rational r4 = new Rational(3);
//        System.out.println(r1.equals(r4));
//        System.out.println(r1 == r4);


//        Polygon r1 = new Rectangle(2, 3);
//        Rectangle r2 = new Square(3);
//        System.out.println(r1 instanceof
//                Rectangle);
//        System.out.println(r2 instanceof
//                Rectangle);
//        System.out.println(r1 instanceof
//                Square);
//        System.out.println(r2 instanceof
//                Polygon);
//        System.out.println(r2.getArea());


//        ArrayList<Shape> shapes = new ArrayList<>(Arrays.asList(new Rectangle(1, 2), new
//                Square(1), new Square(2), new Circle(1)));
//        shapes.stream().filter(s -> s instanceof Polygon).sorted(new Comparator<Shape>() {
//            @Override
//            public int compare(Shape o1, Shape o2) {
//                if (o1.getArea() > o2.getArea())
//                    return -1;
//                else if (o1.getArea() < o2.getArea())
//                    return 1;
//                else
//                    return 0;
//            }
//        }).forEach(System.out::println);

        //If the area of the first shape (o1.getArea()) is greater than the area of the second shape (o2.getArea()), it returns -1
        // (indicating that the first shape should come before the second).
       // If the area of the first shape is less than the area of the second shape, it returns 1
        // (indicating that the first shape should come after the second).
       // If the areas are equal, it returns 0.
    }

}

interface Shape {
    public double getArea();
}
interface Polygon extends Shape {
    public double getArea();
}
class Rectangle implements Polygon {
    private int length, width;
    public Rectangle(int length, int width) {
        this.length = length;
        this.width = width;
    }
    public double getArea() {
        return this.length * this.width;
    }
    @Override
    public String toString() {
        return "Rectangle: " + getArea();
    }
}



class Square extends Rectangle {
    public Square(int length) {
        super(length, length);
    }
    @Override
    public String toString() {
        return "Square: " + getArea();
    }
}
class Circle implements Shape
{
    private int radius;
    public Circle(int radius) {
        this.radius = radius;
    }
    public double getArea() { return Math.PI *
            Math.pow(radius, 2); }
    @Override
    public String toString() {
        return "Circle: " + getArea();
    }
}


///////
interface Greeting {
    void greet();
}

////

 class OuterClass {

    private static String outerStaticField = "Outer static field";

    private String outerInstanceField = "Outer instance field";

    // Static nested class
    public static class StaticNestedClass {
        public void display() {
            System.out.println("Accessing static field from StaticNestedClass: " + outerStaticField);
        }
    }

    // Inner (non-static) nested class
    public class InnerClass {
        public void display() {
            System.out.println("Accessing instance field from InnerClass: " + outerInstanceField);
        }
    }
}
    /////
//interface Shape {
//    public double getArea();
//}
//interface Polygon extends Shape {
//    public double getArea();
//}
//class Rectangle implements Polygon {
//    private int length, width;
//    public Rectangle(int length, int width) {
//        this.length = length;
//        this.width = width;
//    }
//    public double getArea() {
//        return this.length * this.width;
//    }
//    @Override
//    public String toString() {
//        return "Rectangle: " + getArea();
//    }
//}
//class Square extends Rectangle {
//    public Square(int length) {
//        super(length, length);
//    }
//    @Override
//    public String toString() {
//        return "Square: " + getArea();
//    }
//}
//class Circle implements Shape
//{
//    private int radius;
//    public Circle(int radius) {
//        this.radius = radius;
//    }
//    public double getArea() { return Math.PI *
//            pow(radius, 2); }
//    @Override
//    public String toString() {
//        return "Circle: " + getArea();
//    }
//}
//class AreaCalculator<T extends Polygon> {
//    private T[] elements;
//    public AreaCalculator(T[] elements) {
//        this.elements = elements;
//    }
//    double[] computeAreas() {
//        double[] res = new double[elements.length];
//        int i = 0;
//        for (T e: elements) {
//            res[i++] = e.getArea();
//        }
//        return res;
//    }
//}




////////////////////////////
interface MyComparable {
    public boolean compareTo(Object o);
}

interface GraphicallyRepresentable {
    public void draw();
}

class Complex implements MyComparable, GraphicallyRepresentable {
    private double real, imaginary;

    Complex(double r, double i) {
        this.real = r;
        this.imaginary = i;
    }

    private double modulus() {

        return sqrt(pow(this.real, 2) + pow(this.imaginary, 2));
    }

    @Override
    public boolean compareTo(Object c) {
//        if (c.getClass() != Complex.class)
//            return false;
        if (!(c instanceof Complex))
            return false;
        Complex number = (Complex) c;
        return this.modulus() < number.modulus();
    }

    @Override
    public void draw() {

        System.out.println("Drawing: Ox: " + this.real + ", Oy: " + this.imaginary);
    }

    @Override
    public String toString() {
        return "Complex{" +
                "real=" + real +
                ", imaginary=" + imaginary +
                '}';
    }
}

class ComplexWithId extends Complex {
    private int id;

    public ComplexWithId(double re, double im, int id) {
        super(re, im);
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ComplexWithId{" +
                "id=" + id +
                "} " + super.toString();
    }
}
////////////////////////////


class Pair<K, V> {
    private K key;
    private V value;

    Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

class Utils {
    public static <K, V> boolean equals(Pair<K, V> p1, Pair<K, V> p2) {
        return p1.getKey().equals(p2.getKey()) &&
                p1.getValue().equals(p2.getValue());
    }
}

////////

class Book implements Comparable<Book> {
    private final String authors;
    private final String title;
    private final int numberOfPages;

    public Book(String authors, String title, int numberOfPages) {
        this.authors = authors;
        this.title = title;
        this.numberOfPages = numberOfPages;
    }

    @Override
    public int compareTo(Book o) {
        return this.title.compareTo(o.title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "authors='" + authors + '\'' +
                ", title='" + title + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }

    public String getAuthors() {
        return authors;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }
}

class AuthorComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getAuthors().compareTo(o2.getAuthors());
    }
}

class PagesComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getNumberOfPages() < o2.getNumberOfPages())
            return -1;
        else if (o1.getNumberOfPages() > o2.getNumberOfPages())
            return 1;
        else
            return 0;
    }
}

////////

class Person {
    private String name;

    Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + this.name + '\'' +
                '}';
    }
}
class Student extends Person {
    private int yearOfStudy;

    Student(String name, int yearOfStudy) {
        super(name);
        this.yearOfStudy = yearOfStudy;
    }

    @Override
    public String toString() {
        return "Student{" +
                "yearOfStudy=" + yearOfStudy +
                "} " + super.toString();
    }
}

class StudentWithScolarship extends Student {
    private int amount;

    StudentWithScolarship(String name, int yearOfStudy, int amount) {
        super(name, yearOfStudy);
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "StudentWithScolarship{" +
                "amount=" + amount +
                "} " + super.toString();
    }
}
//
//public class Wildcards {
//    public static void printElements(LinkedList<?> list)
//    // when we want to be able to use any type
//    {
//        LinkedList<?>.ListIterator<?> iterator = list.iterator();
//        while (iterator.valid()) {
//            System.out.println(iterator.element());
//            iterator.next();
//        }
//    }
//
//    public static void printElementsUpperBounded(
//            LinkedList<? extends Person> list) // it can accept all object who have IS-A relationship with Person
//    {
//        LinkedList<?>.ListIterator<?> iterator = list.iterator();
//        while (iterator.valid()) {
//            System.out.println(iterator.element());
//            iterator.next();
//        }
//    }
//
//    public static void printElementsLowerBounded(
//            LinkedList<? super Student> list) // it can accept all objects that are above Student in the type hierarchy
//    {
//        LinkedList<?>.ListIterator<?> iterator = list.iterator();
//        while (iterator.valid()) {
//            System.out.println(iterator.element());
//            iterator.next();
//        }
//    }
//    ////
//    class LinkedList<T> {
//        private Node<T> first;
//
//        LinkedList() {
//            this.first = null;
//        }
//
//        public void add(T obj) {
//            Node n = new Node(obj, null);
//
//            if (this.first == null)
//                this.first = n;
//            else {
//                n.setNext(this.first);
//                this.first = n;
//            }
//        }
//
//        ListIterator<T> iterator() {
//            return new ListIterator();
//        }
//    }
//        private static class Node<T> {
//            private final T data;
//            private Node<T> next;
//
//            Node(T data, Node next) {
//                this.data = data;
//                this.next = next;
//            }
//
//            T getData() {
//                return data;
//            }
//
//            Node getNext() {
//                return next;
//            }
//
//            void setNext(Node next) {
//                this.next = next;
//            }
//        }
//
//        class ListIterator<T> {
//            private Node current;
//
//            ListIterator() {
//                this.current = first;
//            }
//
//            boolean valid() {
//                return this.current != null;
//            }
//
//            T element() {
//                return (T) this.current.getData();
//            }
//
//            void next() {
//                this.current = this.current.getNext();
//            }
//        }
//    }




    ////////
      abstract class Shape2 {

        protected String name;

        public Shape2(String name) {
            this.name = name;
        }

        public abstract double getArea();
    }

class Circle2 extends Shape2 {

    private double radius2;

    public Circle2(String name) {
        super(name);
    }

    public Circle2(String name, double radius) {
        this(name);
        this.radius2 = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius2 * radius2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle2 circle = (Circle2) o;
        return Double.compare(radius2, circle.radius2) == 0;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius2 +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius2);
    }
}


class MathUtils {
    public static double add(double n1, double n2) {
        return n1 + n2;
    }

    public static int gcd(int n1, int n2) {
        if (n2 == 0)
            return n1;
        return gcd(n2, n1 % n2);
    }
}

 class Rational {
    private int numerator;
    private int denominator;

    static double PRIMEGAME_FIRST = 17.0 / 91; // first number in John H. Conway's prime producing machine
    // 14 numbers which can produce an infinity of primes

    public Rational() {
        this.numerator = 0;
        this.denominator = 1;
    }

    public Rational(int num) {
        this(num, 1);
        // this(1, 0); // ERROR
    }

    public Rational(int num, int den) {
        this.numerator = num;
        this.denominator = den;
    }

    @Override
    public String toString() {
        return "Rational{" +
                "numerator=" + numerator +
                ", denominator=" + denominator +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rational rational = (Rational) o;
        return numerator == rational.numerator &&
                denominator == rational.denominator;
    }

//    public Rational add(Rational r)  {
//        this(this.numerator + r.numerator,
//                this.denominator + r.denominator); // ERROR
//        return this;
//    }

    public Rational add(Rational r) {
        Rational result = new Rational(this.numerator * r.denominator + this.denominator * r.numerator, this.denominator * r.denominator);
        int gcd = MathUtils.gcd(result.numerator, result.denominator);
        result.numerator /= gcd;
        result.denominator /= gcd;
        return result;
    }
}