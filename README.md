---

# ATM Simulation in Java

This repository contains a fully functional ATM simulation implemented in Java. The simulation supports basic ATM functionalities, including account management, balance checks, deposits, withdrawals, and mini statements.

## Features

- **Check Balance**: View the current balance of the account.
- **Deposit**: Add funds to the account.
- **Withdraw**: Remove funds from the account, subject to available balance.
- **Mini Statement**: View recent transactions for the account.
- **Multiple Account Types**: Support for Savings and Current accounts.

## Demo Accounts

To test the ATM simulation, you can use the following demo account details:

### Savings Account
- **Account Number**: `123456`
- **PIN**: `1234`

### Current Account
- **Account Number**: `654321`
- **PIN**: `5678`

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure that JDK 8 or higher is installed on your machine.
- **Command Line Interface**: Access to a terminal or command prompt.

### Setup

1. **Clone the Repository**

   ```sh
   git clone https://github.com/Bathulahepsi/Brainwave_Matrix_Intern
   ```

2. **Navigate to the Project Directory**

   ```sh
   cd atm-simulation
   ```

3. **Compile the Code**

   ```sh
   javac ATM.java
   ```

4. **Run the Program**

   ```sh
   java ATM
   ```

## Usage

Upon running the ATM simulation, follow these steps:

1. **Authenticate**:
   - Enter the account number and PIN from the demo accounts.

2. **Use the ATM Menu**:
   - **Check Balance**: Option 1
   - **Deposit**: Option 2
   - **Withdraw**: Option 3
   - **Mini Statement**: Option 4
   - **Exit**: Option 5

3. **Follow Prompts**:
   - The program will guide you through various actions based on the menu selections.

## Code Overview

### ATM.java

This is the main class responsible for:

- **Authentication**: Verifying account numbers and PINs.
- **Menu Operations**: Handling user choices for various operations.
- **Account Management**: Performing deposits, withdrawals, and generating mini statements.

### Account.java

An abstract class representing a generic bank account, with specific implementations for:

- **SavingsAccount**: A type of account with basic operations.
- **CurrentAccount**: Another type of account with its own specifics.

### Hashing

PINs are hashed using the SHA-256 algorithm for secure verification.

## Example

Here’s how to perform a basic operation using the ATM:

1. **Authenticate**:
   - **Account Number**: `123456`
   - **PIN**: `1234`

2. **Select an Operation**:
   - For example, choose **Option 1** to check your balance.

## Contributing

Feel free to submit issues or pull requests if you have suggestions or improvements for the ATM simulation.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
