package com.appCent.toDoApp.model;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
public class UserDTO {

    private String name;

    private String email;

}
