/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package coe528.project;

/**
 *
 * @author vrdev
 */
public interface Bank {
void login(String username, String password); 
void logout(); 
void deposit(double amount); 
void withdraw(double amount); 
double getBalance(); 
void onlinePurchase(double amount); 
} 
