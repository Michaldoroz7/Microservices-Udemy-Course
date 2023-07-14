package com.mdoroz.rest.webservices.restfulwebservices.user;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class User {


    private Integer id;
    @Size(min =2, message = "name should have atleast 2 characters")
    private String name;
    @Past(message = "Birth date should be in the past!")
    private LocalDate birthDate;


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
