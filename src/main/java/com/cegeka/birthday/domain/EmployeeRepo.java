package com.cegeka.birthday.domain;

import java.util.Collection;

public interface EmployeeRepo {
    Collection<Employee> findAll();
}
