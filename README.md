
<h1 align="center">Fawry Internship Challenge: E-Commerce System</h1>

<p align="center">
A console-based e-commerce system designed to handle various product types, a shopping cart, and a checkout process, as per the Fawry Rise Journey Full Stack Internship Challenge.


<!-- PROJECT BADGES -->

📖 About The Project
This project is a solution to the Fawry Full Stack Development Internship Challenge. It's a console-based e-commerce application written in Java that simulates a real-world shopping experience. The system is designed with an object-oriented approach to handle different kinds of products, manage inventory, and process customer checkouts with detailed validation.

The core of the project focuses on polymorphism and interfaces to manage product variations, such as:

Expirable vs. Non-Expirable Products: Items like food have expiration dates, while electronics do not.

Shippable vs. Non-Shippable Products: Physical goods like TVs require shipping and have a weight, whereas digital items like scratch cards do not.

The system culminates in a checkout process that validates the cart, checks the customer's balance, verifies stock and product validity, and prints a detailed receipt and shipment notice to the console.

<br>

✨ Key Features
Product Modeling: Defines products with names, prices, and quantities.

Product Inheritance: Uses abstract classes and interfaces to model:

Expirable products (e.g., Cheese, Biscuits).

Shippable products with weight (e.g., Cheese, TV).

Shopping Cart: Allows customers to add products, with quantity validated against available stock.

Smart Checkout:

Calculates order subtotal, shipping fees, and total amount.

Updates customer's balance after purchase.

Provides robust error handling for empty carts, insufficient funds, expired products, or out-of-stock items.

Shipping Service Integration: Collects all shippable items and sends them to a simulated ShippingService, demonstrating the use of interfaces (Shippable with getName() and getWeight()).

Clear Console Output: Prints formatted shipment notices and checkout receipts as specified in the challenge requirements.

<br>

🛠️ Built With
This project was built using the recommended technologies for a robust Java application.

Tech Stack







🚀 Getting Started
Follow these instructions to get the project running on your local machine.

✅ Prerequisites
Java Development Kit (JDK): Version 17 or later.

Apache Maven: Version 3.8 or later.

IntelliJ IDEA: Community or Ultimate Edition.

⚙️ Installation & Execution
Clone the repository:

git clone https://github.com/Mo3gz/Fawry-Internship-Challenge.git

Open the project in IntelliJ IDEA:

Launch IntelliJ IDEA.

Select File > Open... and navigate to the cloned repository's root folder.

IntelliJ will automatically detect the pom.xml file and set up the project. Allow it a moment to download the Maven dependencies.

Compile the project and run tests:
You can build the project using the Maven tool window in IntelliJ:

Go to View > Tool Windows > Maven.

Under Lifecycle, double-click clean and then install.

Run the main application:

In the Project Explorer, navigate to src/main/java.

Find the Main.java file

Right-click on the Main.java file and select Run 'Main.main()'.

The application will run, and the output will be displayed in the Run console at the bottom of the IDE.

Usage
The Main.java file contains the example scenario requested in the challenge document. Running it will produce the following console output, demonstrating a successful checkout and shipment process.

// Example code from Main.java
// Setup products, customer, and cart
...
cart.add(cheese, 2);
cart.add(tv, 3); // This will likely fail if stock is low, as per a good test case
cart.add(biscuits, 1);
cart.add(scratchCard, 1);

checkout(customer, cart);

Console Output Example
** Shipment notice **
2x Cheese             400.0g
1x Biscuits           700.0g
Total package weight: 1.1kg

** Checkout receipt **
2x Cheese             200.0
1x Biscuits           150.0
1x Scratch Card       50.0
-----------------------------
Subtotal:             400.0
Shipping:             30.0
-----------------------------
Total Amount Paid:    430.0
Remaining Balance:    [Calculated Balance]


👨‍💻 Author
Ayman Ashraf

GitHub: @Mo3gz

LinkedIn: [[LinkedIn Profile URL]](https://www.linkedin.com/in/mo3gz/)

🙏 Acknowledgements
Fawry for providing this engaging and practical internship challenge.

The open-source community for providing the tools and libraries that made this project possible.
