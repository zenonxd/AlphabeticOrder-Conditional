import util.Employees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the file path: ");
        String path = sc.next();

        List<Employees> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine();
            while (line!=null) {
                String[] fields = line.split(",");
                String name = fields[0];
                String email = fields[1];
                Double salary = Double.parseDouble(fields[2]);
                list.add(new Employees(name, email, salary));

                line = br.readLine();
            }
            System.out.println("Enter the salary: ");
            int value = sc.nextInt();

            List<String> employeesGreaterValue = list.stream()
                    .filter(e -> e.getSalary() > value)
                    .map(e -> e.getEmail())
                    .sorted()
                    .collect(Collectors.toList());

            employeesGreaterValue.forEach(System.out::println);

            System.out.println("Sum of salary of people whose name starts with 'M': ");

            Double employeesLetterM = list.stream()
                    .filter(e -> e.getName().startsWith("M"))
                    .map(Employees::getSalary)
                    .reduce(0.0, Double::sum);

            System.out.println(employeesLetterM);

        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        sc.close();
    }
}