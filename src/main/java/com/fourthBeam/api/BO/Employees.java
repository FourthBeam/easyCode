package com.fourthBeam.api.BO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Employees {

    private int id;

    private String firstName;

    private String lastName;

    private int age;
}
