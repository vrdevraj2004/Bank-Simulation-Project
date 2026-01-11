/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528.project;

/**
 *
 * @author vrdev
 */
import java.io.Serializable; 
public class Customer extends Person implements Bank, Serializable { private static final long serialVersionUID = 1L; 
private String level; 
private final double balance; 
public Customer(String username, String password, String level) { super(username, password, "customer"); 
this.level = level; 
this.balance = 100.0; // Initial balance of $100 
} 
// Implement Bank interface methods 
@Override 
public void login(String username, String password) { 
// Customer login logic 
} 
@Override 
public void logout() { 
// Customer logout logic 
} 
@Override 
public void deposit(double amount) {
// Deposit logic 
} 
@Override 
public void withdraw(double amount) { 
// Withdraw logic 
} 
@Override 
public double getBalance() { 
return balance; 
} 
@Override 
public void onlinePurchase(double amount) { 
// Online purchase logic 
} 
public String getLevel() { 
return level; 
} 
public void setLevel(String level) { 
this.level = level; 
} 
} 
