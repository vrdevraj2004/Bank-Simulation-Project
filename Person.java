/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author vrdev
 */
public abstract class Person { 
protected String username; 
protected String password; 
protected String role; 
public Person(String username, String password, String role) { this.username = username; 
this.password = password; 
this.role = role; 
} 
public String getUsername() { 
return username; 
} 
public String getPassword() { 
return password; 
} 
public String getRole() { 
return role; 
} 
} 

