import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.sqrt;
class Student{
    String name;
    Integer age;

    Student(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return "Name: " + name + ", Age: " + age;
    }

    public String getName(){
        return name;
    }

    public Integer getAge(){
        return age;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(Integer age){
        this.age = age;
    }
    // getters, setters and toString
}
class Pair {
    private int a;
    private int b;
    public Pair(int a, int b){
        this.a = a;
        this.b = b;
    }
    public int getA(){
        return a;
    }
    public int getB(){
        return b;
    }
}
//INTERFACES
interface MyInterface {
    static void method1(){
        System.out.println("Method 1");
    }
    default void method2(){
        System.out.println("Method 2");
    }
    void method3();
}
interface MyInterface2 {
    static void method1(){
        System.out.println("Method 1");
    }
    default void method2(){
        System.out.println("Method 2");
    }
    void method3();
}
class MyClass implements MyInterface, MyInterface2 {
        @Override
        public void method2() {
            MyInterface.super.method2();
        }

        @Override
    public void method3() {
        System.out.println("Method 3 from MyClass");
    }
}
class MyOtherClass implements MyInterface {
    @Override
    public void method3() {
        System.out.println("Method 3 from MyOtherClass");
    }
}
//FUNCTIONAL INTERFACES

@FunctionalInterface
interface MyFunctionalInterface {
    int sum(int a, int b);
}

//FUNCTIONS

//LAMBDA EXPRESSIONS

//STREAMS


public class Main {
    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        MyOtherClass myOtherClass = new MyOtherClass();
        //INTERFACES
        MyInterface.method1();
        myClass.method2();
        myClass.method2();
        myClass.method3();
        myOtherClass.method3();
        System.out.println("--------------------");

        //FUNCTIONAL INTERFACES
        MyFunctionalInterface sumExample = (a, b) -> a + b;
        System.out.println(sumExample.sum(1, 2));

        System.out.println("--------------------");
        //CONSUMER
        BiConsumer<Integer, Integer> sum = (a, b) -> System.out.println(a + b);
        Stream<Pair> pairs = Stream.of(new Pair(1, 2), new Pair(3, 4), new Pair(5, 6));
        pairs.forEach(pair -> sum.accept(pair.getA(), pair.getB()));

        System.out.println("--------------------");
        //SUPPLIER
        Supplier<Double> random = () -> Math.random()%100;
        DoubleSupplier random2 = () -> Math.random()%100;
        System.out.println(random.get());
        System.out.println(random2.getAsDouble());
        System.out.println("--------------------");

        //STREAMS
        Stream.of(1, 2, 3, 4, 5).filter(n -> n%2 == 0).forEach(x -> System.out.print(x + " "));
        System.out.println();
        Stream.of(1, 2, 3, 4, 5).map(n -> n * n).forEach(x -> System.out.print(x + " "));
        System.out.println();
//        Stream<Student> studentStream = Stream.of(new Student("Alice", 21), new Student("Bob", 22), new Student("Gigel", 20));
//        studentStream.map(st -> new Student(st.getName().toLowerCase(), st.age))
//                .forEach(System.out::println);
        Stream<Student> studentStream2 = Stream.of(new Student("Alice", 21), new Student("Bob", 22), new Student("Gigel", 20));
        studentStream2.peek(st -> st.setName(st.getName().toLowerCase()))
                .forEach(System.out::println);

        String[] myArray = { "this", "is", "a", "sentence" };
        String result = Arrays.stream(myArray)
                .reduce("", (a,b) -> a + b);
        System.out.println(result);

        List<List<Integer>> listOfLists =
                List.of(List.of(1, 2, 3, 4), List.of(10, 9, 8, 7), List.of(5, 6));
        List<Integer> singleList = listOfLists.stream().flatMap(List::stream).toList();
        System.out.println(singleList);

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Map<String, List<Integer>> parity =
                numbers.stream().collect(Collectors.groupingBy(i -> i % 2 != 0 ? "Odd" : "Even"));
        for (String paritate : parity.keySet()) {
            System.out.println(paritate + " " + parity.get(paritate));
        }
    }
}