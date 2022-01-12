package com.takeaway.menusrv.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 3134592336804106224L;

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String address;
    private String phone;


}
