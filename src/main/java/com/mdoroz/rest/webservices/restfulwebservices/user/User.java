package com.mdoroz.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("user_name")
    private String name;
    @Past(message = "Birth date should be in the past!")
    @JsonProperty("user_birthDate")
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
