
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 //* Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template

package coe528.project;

//@author vrdev

import javafx.application.Application; 
import javafx.geometry.Insets; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.GridPane; 
import javafx.scene.layout.VBox; 
import javafx.stage.Stage; 
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List; 
import java.util.Scanner; 
public class BankMain extends Application { 
private Stage primaryStage; 
private Label statusLabel; 
private ListView<String> accountListView; 
private List<String> accountList; 
@Override 
public void start(Stage primaryStage) { 
this.primaryStage = primaryStage; 
primaryStage.setTitle("Bank Application"); 
// Initialize account list 
accountList = new ArrayList<>(); 
// Create layout 
GridPane grid = new GridPane(); 
grid.setPadding(new Insets(20, 20, 20, 20)); 
grid.setVgap(10); 
grid.setHgap(10); 
// Username label and field 
Label usernameLabel = new Label("Username:"); 
GridPane.setConstraints(usernameLabel, 0, 0); 
TextField usernameField = new TextField(); 
GridPane.setConstraints(usernameField, 1, 0); 
// Password label and field
Label passwordLabel = new Label("Password:"); 
GridPane.setConstraints(passwordLabel, 0, 1); 
PasswordField passwordField = new PasswordField(); 
GridPane.setConstraints(passwordField, 1, 1); 
// Role label and combo box 
Label roleLabel = new Label("Role:"); 
GridPane.setConstraints(roleLabel, 0, 2); 
ComboBox<String> roleComboBox = new ComboBox<>(); 
roleComboBox.getItems().addAll("Manager", "Customer"); 
roleComboBox.setValue("Manager"); // Default value 
GridPane.setConstraints(roleComboBox, 1, 2); 
// Login button 
Button loginButton = new Button("Login"); 
GridPane.setConstraints(loginButton, 1, 3); 
loginButton.setOnAction(e -> handleLogin(usernameField.getText(), passwordField.getText(), roleComboBox.getValue())); 
// Status label 
statusLabel = new Label(""); 
GridPane.setConstraints(statusLabel, 0, 4, 2, 1); 
// Add components to layout 
grid.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, roleLabel, roleComboBox, loginButton, statusLabel); 
// Create scene 
Scene scene = new Scene(grid, 300, 200); 
primaryStage.setScene(scene); 
primaryStage.show(); 
} 
private void handleLogin(String username, String password, String role) { 
// Perform authentication logic here 
// For simplicity, let's just display a message 
if (role.equals("Manager")) { 
if (username.equals("manager") && password.equals("manager")) { 
showManagerOptions(); 
} else { 
statusLabel.setText("Invalid username or password for Manager."); 
} 
} else if (role.equals("Customer")) { 
if (authenticateCustomer(username, password)) { 
showCustomerOptions(username); 
} else { 
statusLabel.setText("Invalid username or password for Customer."); 
} 
} 
} 
private boolean authenticateCustomer(String username, String password) { 
// Authenticate customer logic here 
// For simplicity, let's assume customer information is stored in files 
// Check if the username and password match any of the stored customer accounts 
// Return true if authenticated, false otherwise 
String filename = username + ".txt"; 
File file = new File(filename); 
if (!file.exists()) { 
return false; // User file doesn't exist, authentication failed 
} 
// Read the file and check if the password matches 
try { 
    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("Password:")) {
                String storedPassword = line.substring("Password: ".length());
                return password.equals(storedPassword.trim());
            }
        }   } 
} catch (IOException e) { 
} 
return false; // Failed to authenticate 
} 
private void showManagerOptions() { 
primaryStage.setTitle("Manager Options"); 
// Create layout 
VBox vbox = new VBox(10); 
vbox.setPadding(new Insets(20, 20, 20, 20)); 
// Read all text files in the project directory and add their usernames to the account list File directory = new File("."); 
File[] files;
    files = directory.listFiles((dir, name) -> name.endsWith(".txt"));
accountList.clear(); // Clear existing list 
if (files != null) { 
for (File file : files) { 
String username = file.getName().replace(".txt", ""); 
accountList.add(username); 
} 
} 
// Account list view 
accountListView = new ListView<>(); 
accountListView.getItems().addAll(accountList); 
vbox.getChildren().add(accountListView); 
// Add customer button 
Button addCustomerButton = new Button("Add Customer"); 
addCustomerButton.setOnAction(e -> addCustomer()); 
vbox.getChildren().add(addCustomerButton); 
// Delete customer button 
Button deleteCustomerButton = new Button("Delete Customer"); 
deleteCustomerButton.setOnAction(e -> deleteCustomer()); 
vbox.getChildren().add(deleteCustomerButton); 
// Logout button 
Button logoutButton = new Button("Logout"); 
logoutButton.setOnAction(e -> { 
primaryStage.setTitle("Bank Application"); 
primaryStage.setScene(new Scene(new GridPane(), 300, 200)); 
}); 
vbox.getChildren().add(logoutButton); 
// Create scene 
Scene scene = new Scene(vbox, 300, 300); 
primaryStage.setScene(scene); 
} 
private void addCustomer() { 
primaryStage.setTitle("Add Customer"); 
// Create layout 
GridPane grid = new GridPane(); 
grid.setPadding(new Insets(20, 20, 20, 20)); 
grid.setVgap(10); 
grid.setHgap(10); 
// Username label and field 
Label usernameLabel = new Label("Username:"); 
GridPane.setConstraints(usernameLabel, 0, 0); 
TextField usernameField = new TextField();
GridPane.setConstraints(usernameField, 1, 0); 
// Password label and field 
Label passwordLabel = new Label("Password:"); 
GridPane.setConstraints(passwordLabel, 0, 1); 
PasswordField passwordField = new PasswordField(); 
GridPane.setConstraints(passwordField, 1, 1); 
// First name label and field 
Label firstNameLabel = new Label("First Name:"); 
GridPane.setConstraints(firstNameLabel, 0, 2); 
TextField firstNameField = new TextField(); 
GridPane.setConstraints(firstNameField, 1, 2); 
// Last name label and field 
Label lastNameLabel = new Label("Last Name:"); 
GridPane.setConstraints(lastNameLabel, 0, 3); 
TextField lastNameField = new TextField(); 
GridPane.setConstraints(lastNameField, 1, 3); 
// Deposit label and field 
Label depositLabel = new Label("Initial Deposit:"); 
GridPane.setConstraints(depositLabel, 0, 4); 
TextField depositField = new TextField(); 
GridPane.setConstraints(depositField, 1, 4); 
// Error message label 
Label errorLabel = new Label(); 
errorLabel.setStyle("-fx-text-fill: red;"); 
GridPane.setConstraints(errorLabel, 1, 5); 
// Submit button 
Button submitButton = new Button("Submit"); 
GridPane.setConstraints(submitButton, 1, 6); 
submitButton.setOnAction(e -> { 
String username = usernameField.getText(); 
if (isUsernameTaken(username)) { 
errorLabel.setText("Username is already taken."); 
} else { 
double depositAmount = Double.parseDouble(depositField.getText()); 
if (depositAmount < 100) { 
errorLabel.setText("Deposit too low. Minimum deposit is $100."); 
} else { 
createCustomer(username, passwordField.getText(), firstNameField.getText(), lastNameField.getText(), depositField.getText()); } 
} 
}); 
// Add components to layout 
grid.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, firstNameLabel, firstNameField, lastNameLabel, lastNameField, depositLabel, depositField, submitButton, errorLabel); 
// Create scene 
Scene scene = new Scene(grid, 400, 400); 
primaryStage.setScene(scene); 
} 
private boolean isUsernameTaken(String username) { 
String filename = username + ".txt"; 
File file = new File(filename); 
return file.exists(); 
} 
private void createCustomer(String username, String password, String firstName, String lastName, String initialDeposit) { statusLabel.setText("Account created successfully."); 
double depositAmount = Double.parseDouble(initialDeposit); 
String accountStatus = ""; 
if (depositAmount < 10000) { 
accountStatus = "Silver";
} else if (depositAmount >= 10000 && depositAmount <= 20000) { 
accountStatus = "Gold"; 
} else { 
accountStatus = "Platinum"; 
} 
String filename = username + ".txt"; 
try { 
FileWriter writer = new FileWriter(filename); 
writer.write("Username: " + username + "\n"); 
writer.write("Password: " + password + "\n"); 
writer.write("First Name: " + firstName + "\n"); 
writer.write("Last Name: " + lastName + "\n"); 
writer.write("Initial Deposit: $" + initialDeposit + "\n"); 
writer.write("Total Balance: $" + initialDeposit + "\n"); 
writer.write("Account Status: " + accountStatus + "\n"); 
writer.close(); 
} catch (IOException e) { 
e.printStackTrace(); 
} 
String accountInfo = firstName + " " + lastName; 
accountList.add(accountInfo); 
accountListView.getItems().clear(); 
accountListView.getItems().addAll(accountList); 
showManagerOptions(); 
} 
private void deleteCustomer() { 
primaryStage.setTitle("Delete Customer"); 
VBox vbox = new VBox(10); 
vbox.setPadding(new Insets(20, 20, 20, 20)); 
ListView<String> customerListView = new ListView<>(); 
customerListView.getItems().addAll(accountList); 
customerListView.setPrefHeight(150); 
Button deleteButton = new Button("Delete"); 
deleteButton.setOnAction(e -> { 
String selectedCustomer = customerListView.getSelectionModel().getSelectedItem(); if (selectedCustomer != null) { 
String filename = selectedCustomer + ".txt"; 
File file = new File(filename); 
if (file.exists()) { 
file.delete(); 
accountList.remove(selectedCustomer); 
accountListView.getItems().clear(); 
accountListView.getItems().addAll(accountList); 
statusLabel.setText("Account successfully deleted."); 
} else { 
statusLabel.setText("Account file not found."); 
} 
} else { 
statusLabel.setText("Please select an account to delete."); 
} 
}); 
Button backButton = new Button("Back to Manager Options"); 
backButton.setOnAction(e -> showManagerOptions()); 
vbox.getChildren().addAll(customerListView, deleteButton, backButton, statusLabel); 
Scene scene = new Scene(vbox, 300, 300); 
primaryStage.setScene(scene); 
} 
private void showCustomerOptions(String username) { 
primaryStage.setTitle("Customer Options");
// Create layout 
VBox vbox = new VBox(10); 
vbox.setPadding(new Insets(20, 20, 20, 20)); 
// Display balance 
double totalBalance = getTotalBalance(username); 
Label balanceLabel = new Label("Total Balance: $" + totalBalance); 
vbox.getChildren().add(balanceLabel); 
// Display status 
String status = getStatus(username); 
Label statusLabel = new Label("Status: " + status); 
vbox.getChildren().add(statusLabel); 
// Create buttons 
Button depositButton = new Button("Deposit"); 
depositButton.setOnAction(e -> depositMoney(username)); 
Button withdrawButton = new Button("Withdraw"); 
withdrawButton.setOnAction(e -> withdrawMoney(username)); 
// Add online purchase button 
Button purchaseButton = new Button("Online Purchase"); 
purchaseButton.setOnAction(e -> showOnlinePurchaseScreen(username)); 
Button backButton = new Button("Back"); 
backButton.setOnAction(e -> showManagerOptions()); 
// Add buttons to the layout 
vbox.getChildren().addAll(depositButton, withdrawButton, purchaseButton, backButton); 
Scene scene = new Scene(vbox, 300, 300); 
primaryStage.setScene(scene); 
} 
private void depositMoney(String username) { 
primaryStage.setTitle("Deposit Money"); 
GridPane grid = new GridPane(); 
grid.setPadding(new Insets(20, 20, 20, 20)); 
grid.setVgap(10); 
grid.setHgap(10); 
Label amountLabel = new Label("Amount:"); 
GridPane.setConstraints(amountLabel, 0, 0); 
TextField amountField = new TextField(); 
GridPane.setConstraints(amountField, 1, 0); 
Label errorLabel = new Label(); 
errorLabel.setStyle("-fx-text-fill: red;"); 
GridPane.setConstraints(errorLabel, 1, 1); 
Button depositButton = new Button("Deposit"); 
GridPane.setConstraints(depositButton, 1, 2); 
depositButton.setOnAction(e -> { 
try { 
double amount = Double.parseDouble(amountField.getText()); 
if (amount <= 0) { 
errorLabel.setText("Amount must be positive."); 
} else { 
updateBalance(username, amount); 
errorLabel.setText("Deposit successful."); 
showCustomerOptions(username); 
} 
} catch (NumberFormatException ex) { 
errorLabel.setText("Invalid amount."); 
} 
}); 
Button backButton = new Button("Back");
GridPane.setConstraints(backButton, 0, 2); 
backButton.setOnAction(e -> showCustomerOptions(username)); 
grid.getChildren().addAll(amountLabel, amountField, errorLabel, depositButton, backButton); 
Scene scene = new Scene(grid, 300, 300); 
primaryStage.setScene(scene); 
} 
private void withdrawMoney(String username) { 
primaryStage.setTitle("Withdraw Money"); 
GridPane grid = new GridPane(); 
grid.setPadding(new Insets(20, 20, 20, 20)); 
grid.setVgap(10); 
grid.setHgap(10); 
Label amountLabel = new Label("Amount:"); 
GridPane.setConstraints(amountLabel, 0, 0); 
TextField amountField = new TextField(); 
GridPane.setConstraints(amountField, 1, 0); 
Label errorLabel = new Label(); 
errorLabel.setStyle("-fx-text-fill: red;"); 
GridPane.setConstraints(errorLabel, 1, 1); 
Button withdrawButton = new Button("Withdraw"); 
GridPane.setConstraints(withdrawButton, 1, 2); 
withdrawButton.setOnAction(e -> { 
try { 
double amount = Double.parseDouble(amountField.getText()); 
double totalBalance = getTotalBalance(username); 
if (amount <= 0) { 
errorLabel.setText("Amount must be positive."); 
} else if (amount > totalBalance) { 
errorLabel.setText("Insufficient balance."); 
} else { 
updateBalance(username, -amount); 
errorLabel.setText("Withdrawal successful."); 
showCustomerOptions(username); 
} 
} catch (NumberFormatException ex) { 
errorLabel.setText("Invalid amount."); 
} 
}); 
Button backButton = new Button("Back"); 
GridPane.setConstraints(backButton, 0, 2); 
backButton.setOnAction(e -> showCustomerOptions(username)); 
grid.getChildren().addAll(amountLabel, amountField, errorLabel, withdrawButton, backButton); 
Scene scene = new Scene(grid, 300, 300); 
primaryStage.setScene(scene); 
} 
private double getTotalBalance(String username) { 
String filename = username + ".txt"; 
double totalBalance = 0; 
try { 
File file = new File(filename); 
Scanner scanner = new Scanner(file); 
while (scanner.hasNextLine()) { 
String line = scanner.nextLine(); 
if (line.startsWith("Total Balance:")) { 
totalBalance = Double.parseDouble(line.substring("Total Balance: $".length())); break; 
} 
} 
scanner.close();
} catch (IOException e) { 
e.printStackTrace(); 
} 
return totalBalance; 
} 
private String getStatus(String username) { 
String filename = username + ".txt"; 
String status = ""; 
try { 
File file = new File(filename); 
Scanner scanner = new Scanner(file); 
while (scanner.hasNextLine()) { 
String line = scanner.nextLine(); 
if (line.startsWith("Account Status:")) { 
status = line.substring("Account Status: ".length()); 
break; 
} 
} 
scanner.close(); 
} catch (IOException e) { 
e.printStackTrace(); 
} 
return status; 
} 
private void updateStatus(String username, double newBalance) { 
String filename = username + ".txt"; 
String newStatus = ""; 
if (newBalance < 10000) { 
newStatus = "Silver"; 
} else if (newBalance >= 10000 && newBalance <= 20000) { 
newStatus = "Gold"; 
} else { 
newStatus = "Platinum"; 
} 
try { 
File file = new File(filename); 
Scanner scanner = new Scanner(file); 
StringBuilder content = new StringBuilder(); 
while (scanner.hasNextLine()) { 
String line = scanner.nextLine(); 
if (line.startsWith("Account Status:")) { 
line = "Account Status: " + newStatus; 
} 
content.append(line).append("\n"); 
} 
scanner.close(); 
FileWriter writer = new FileWriter(file); 
writer.write(content.toString()); 
writer.close(); 
} catch (IOException e) { 
e.printStackTrace(); 
} 
} 
private void updateBalance(String username, double amount) { 
String filename = username + ".txt"; 
try { 
File file = new File(filename); 
Scanner scanner = new Scanner(file); 
StringBuilder content = new StringBuilder(); 
double newBalance = 0; 
while (scanner.hasNextLine()) { 
String line = scanner.nextLine(); 
if (line.startsWith("Total Balance:")) { 
double currentBalance = Double.parseDouble(line.substring("Total Balance: $".length())); newBalance = currentBalance + amount; 
line = "Total Balance: $" + newBalance; 
}
content.append(line).append("\n"); 
} 
scanner.close(); 
FileWriter writer = new FileWriter(file); 
writer.write(content.toString()); 
writer.close(); 
// Update status 
updateStatus(username, newBalance); 
} catch (IOException e) { 
e.printStackTrace(); 
} 
} 
private void showOnlinePurchaseScreen(String username) { 
primaryStage.setTitle("Online Purchase"); 
// Create layout 
VBox vbox = new VBox(10); 
vbox.setPadding(new Insets(20, 20, 20, 20)); 
// Create checkboxes for items 
CheckBox shoesCheckBox = new CheckBox("Shoes - $1000"); 
CheckBox headphonesCheckBox = new CheckBox("Headphones - $50"); 
CheckBox laptopCheckBox = new CheckBox("Laptop - $2000"); 
CheckBox iphoneCheckBox = new CheckBox("iPhone 15 - $1980"); 
CheckBox bagCheckBox = new CheckBox("Bag - $30"); 
// Add checkboxes to the layout 
vbox.getChildren().addAll(shoesCheckBox, headphonesCheckBox, laptopCheckBox, iphoneCheckBox, bagCheckBox); 
// Add button for purchase 
Button purchaseButton = new Button("Purchase"); 
purchaseButton.setOnAction(e -> { 
double totalCartAmount = calculateTotalCartAmount(shoesCheckBox.isSelected(), headphonesCheckBox.isSelected(), laptopCheckBox.isSelected(), iphoneCheckBox.isSelected(), bagCheckBox.isSelected()); 
double fee = calculateFee(getStatus(username)); 
double finalAmount = totalCartAmount + fee; 
if (totalCartAmount < 50) { 
// Display error message 
Label errorLabel = new Label("Total should be over $50."); 
errorLabel.setStyle("-fx-text-fill: red;"); 
vbox.getChildren().add(errorLabel); 
} else if (getTotalBalance(username) >= finalAmount) { 
confirmPurchase(username, finalAmount, fee, shoesCheckBox, headphonesCheckBox, laptopCheckBox, iphoneCheckBox, bagCheckBox); } else { 
// Display insufficient balance message 
Label errorLabel = new Label("Insufficient balance for this transaction."); 
errorLabel.setStyle("-fx-text-fill: red;"); 
vbox.getChildren().add(errorLabel); 
} 
}); 
// Add button to go back to customer options 
Button backButton = new Button("Back"); 
backButton.setOnAction(e -> showCustomerOptions(username)); 
// Add buttons to the layout 
vbox.getChildren().addAll(purchaseButton, backButton); 
Scene scene = new Scene(vbox, 300, 300); 
primaryStage.setScene(scene); 
} 
private void confirmPurchase(String username, double finalAmount, double fee, CheckBox shoesCheckBox, CheckBox headphonesCheckBox, CheckBox laptopCheckBox, CheckBox iphoneCheckBox, CheckBox bagCheckBox) { 
primaryStage.setTitle("Confirm Purchase");
// Create layout 
VBox vbox = new VBox(10); 
vbox.setPadding(new Insets(20, 20, 20, 20)); 
// Display purchase details 
Label purchaseLabel = new Label("Purchase Details:"); 
Label totalAmountLabel = new Label("Total Amount: $" + finalAmount); 
Label feeLabel = new Label("Fee: $" + fee); 
vbox.getChildren().addAll(purchaseLabel, totalAmountLabel, feeLabel); 
// Confirmation button 
Button confirmButton = new Button("Confirm"); 
confirmButton.setOnAction(e -> { 
// Deduct amount from balance 
updateBalance(username, -finalAmount); 
// Update status if necessary 
String status = getStatus(username); 
if (status.equals("Silver") && getTotalBalance(username) >= 10000) { 
updateStatus(username, getTotalBalance(username)); 
} 
// Show transaction complete message 
Label transactionCompleteLabel = new Label("Transaction Complete"); 
vbox.getChildren().add(transactionCompleteLabel); 
// Show back button 
Button backButton = new Button("Back"); 
backButton.setOnAction(event -> showCustomerOptions(username)); 
vbox.getChildren().add(backButton); 
}); 
// Add button to the layout 
vbox.getChildren().add(confirmButton); 
Scene scene = new Scene(vbox, 300, 300); 
primaryStage.setScene(scene); 
} 
private double calculateTotalCartAmount(boolean shoesSelected, boolean headphonesSelected, boolean laptopSelected, boolean iphoneSelected, boolean bagSelected) { 
double totalAmount = 0; 
if (shoesSelected) totalAmount += 1000; 
if (headphonesSelected) totalAmount += 50; 
if (laptopSelected) totalAmount += 2000; 
if (iphoneSelected) totalAmount += 1980; 
if (bagSelected) totalAmount += 30; 
return totalAmount; 
} 
private double calculateFee(String status) { 
switch (status) { 
case "Gold": 
return 10; 
case "Silver": 
return 20; 
default: 
return 0; // Platinum 
} 
} 
public static void main(String[] args) { 
launch(args); 
} 
}
