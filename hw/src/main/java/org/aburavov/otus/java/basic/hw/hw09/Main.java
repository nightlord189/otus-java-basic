package org.aburavov.otus.java.basic.hw.hw09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW09");

        System.out.println("getSerialList");
        List<Integer> integers = getSerialList(1, 10);
        System.out.println(integers);

        System.out.println("getSumMoreThanFiveOfList");
        System.out.println(getSumMoreThanFiveOfList(integers));

        System.out.println("rewriteArrayValues");
        rewriteArrayValues(integers, 1);
        System.out.println(integers);

        System.out.println("increaseArrayValues");
        increaseArrayValues(integers, 1);
        System.out.println(integers);

        List<Employee> employees = Arrays.asList(
                new Employee("John", 25),
                new Employee("Ivan", 45),
                new Employee("Laura", 29),
                new Employee("Paolo", 37),
                new Employee("Piotr", 67)
        );

        System.out.println("getEmployeeNames");
        System.out.println(getEmployeeNames(employees));

        int minAge = 50;
        System.out.println("getEmployeesOlderThan");
        System.out.println("older than " + minAge + ": " + getEmployeesOlderThan(employees, minAge));

        int minAverageAge = 35;
        System.out.println("isAverageAgeLargerThan");
        System.out.println("average age larger than " + minAverageAge + ": " + isAverageAgeLargerThan(employees, minAverageAge));

        System.out.println("getYoungestEmployee");
        System.out.println(getYoungestEmployee(employees));
    }

    public static List<Integer> getSerialList(int min, int max) {
        List<Integer> result = new ArrayList<>(max - min + 1);
        for (int i = min; i <= max; i++) {
            result.add(i);
        }
        return result;
    }

    public static int getSumMoreThanFiveOfList(List<Integer> list) {
        int result = 0;
        for (int val : list) {
            if (val > 5) {
                result += val;
            }
        }
        return result;
    }

    public static void rewriteArrayValues(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, value);
        }
    }

    public static void increaseArrayValues(List<Integer> list, int increment) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + increment);
        }
    }

    public static List<String> getEmployeeNames(List<Employee> employees) {
        return employees.stream().map(x -> x.getName()).toList();
    }

    public static List<Employee> getEmployeesOlderThan(List<Employee> employees, int minAge) {
        return employees.stream().filter(x -> x.getAge() >= minAge).toList();
    }

    public static boolean isAverageAgeLargerThan(List<Employee> employees, int minAverageAge) {
        int averageAge = getAverageAge(employees);
        return averageAge > minAverageAge;
    }

    private static int getAverageAge(List<Employee> employees) {
        int sum = employees.stream().mapToInt(x -> x.getAge()).sum();
        return sum / employees.size();
    }

    public static Employee getYoungestEmployee(List<Employee> employees) {
        Employee result = null;
        if (employees != null) {
            int minAge = Integer.MAX_VALUE;
            for (Employee employee : employees) {
                if (employee.getAge() < minAge) {
                    result = employee;
                    minAge = employee.getAge();
                }
            }
        }
        return result;
    }
}
