```java

import java.util.*;

// Employee class
class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

// Iterator interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Aggregate interface
interface Aggregate<T> {
    Iterator<T> createIterator();
}

// Concrete Iterator
class EmployeeIterator implements Iterator<Employee> {
    private int currentIndex = 0;
    private List<Employee> employees;

    public EmployeeIterator(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < employees.size();
    }

    @Override
    public Employee next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return employees.get(currentIndex++);
    }
}

// Concrete Aggregate
class Company implements Aggregate<Employee> {
    private List<Employee> employees;

    public Company(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Iterator<Employee> createIterator() {
        return new EmployeeIterator(employees);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 50000));
        employees.add(new Employee("Bob", 60000));
        employees.add(new Employee("Charlie", 70000));

        Company company = new Company(employees);
        Iterator<Employee> iterator = company.createIterator();

        double totalSalary = 0;
        while (iterator.hasNext()) {
            totalSalary += iterator.next().getSalary();
        }

        System.out.println("Total salary: " + totalSalary);
    }
}
```

without company:

```java

import java.util.*;

// Employee class
class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }
}

// Iterator interface
interface Iterator<T> {
    boolean hasNext();
    T next();
}

// Concrete Iterator
class EmployeeIterator implements Iterator<Employee> {
    private int currentIndex = 0;
    private List<Employee> employees;

    public EmployeeIterator(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < employees.size();
    }

    @Override
    public Employee next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return employees.get(currentIndex++);
    }
}

// Utility class to provide iteration functionality
class EmployeeIterable {
    private List<Employee> employees;

    public EmployeeIterable(List<Employee> employees) {
        this.employees = employees;
    }

    public Iterator<Employee> iterator() {
        return new EmployeeIterator(employees);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Alice", 50000));
        employees.add(new Employee("Bob", 60000));
        employees.add(new Employee("Charlie", 70000));

        EmployeeIterable employeeIterable = new EmployeeIterable(employees);
        Iterator<Employee> iterator = employeeIterable.iterator();

        double totalSalary = 0;
        while (iterator.hasNext()) {
            totalSalary += iterator.next().getSalary();
        }

        System.out.println("Total salary: " + totalSalary);
    }
}
```
