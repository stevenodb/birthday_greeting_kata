package com.cegeka.birthday.infrastructure;

import com.cegeka.birthday.domain.Employee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

public class EmployeeRepoImpl implements com.cegeka.birthday.domain.EmployeeRepo {
    private final String fileName;

    public EmployeeRepoImpl(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Collection<Employee> findAll() {
        Collection<Employee> result = new ArrayList<>();

        BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(fileName)));
        String str = "";
        try {
            str = in.readLine();
            while ((str = in.readLine()) != null) {
                String[] employeeData = str.split(", ");
                Employee employee = new Employee(employeeData[1], employeeData[0], employeeData[2], employeeData[3]);
                result.add(employee);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return result;
    }
}
