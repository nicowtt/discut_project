package com.nicow.microservicemodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComplainUserDto {

    private String id;
    private String name;
    private String firstName;
    private String pseudo;
    private String email;
    private String password;
    private int popularity;
    private Date creationDate;
    private String role;
    private String token;
}
