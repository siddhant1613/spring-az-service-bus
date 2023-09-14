package com.azservice.bus.entity;

//Java Program to Illustrate EmailDetails Class

//Importing required classes
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Annotations
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//Class
public class EmailDetails {

// Class data members
private String recipient;
private String msgBody;
private String subject;
private String attachment;
}