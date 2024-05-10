Division
The Division project is a program for performing integer division with the output of the fractional result in ASCII art.

How to Use
Install the Java Development Kit (JDK) on your computer.
Clone the repository using the command:
bash
Copy code
git clone https://github.com/your/repository.git
Navigate to the project directory:
bash
Copy code
cd Division
Compile Main.java:
css
Copy code
javac Main.java
java Main
Enter the dividend and divisor in the constructor of the Main class in the main method:
java
Copy code
Division divider = new Division(78945, 4);
divider.performDivision();
The program will output the division result in ASCII art.
Features
The program handles cases with a zero divisor.
The division result is outputted in ASCII art.
Logic for performing integer division is implemented.
Dependencies
The project has no external dependencies and only uses the standard Java library.
