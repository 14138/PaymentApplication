Stripe Payment Integration with Java and Spring Boot

This repository provides a simple implementation of Stripe payments in a Java ,Spring Boot and thymeleaf application. The project is built using Gradle and demonstrates the integration of the Stripe Java library.

Prerequisites

Before starting, make sure you have a Stripe Secret Key. Additionally, Stripe offers a Test mode for developers to simulate transactions without real money.

Getting Started

Clone the repository: git clone https://github.com/14138/PaymentApplication.git

Build application

Login Page: http://localhost:8080/login
Registration Page: http://localhost:8080/register
Product Page: http://localhost:8080/index

Set stripe.api.secretKey and stripe.api.publicKey either in your IDE Run Configuration Env or directly in the application.properties.
Get keys from Stripe dashboard directly. https://dashboard.stripe.com/test/dashboard

Controller Class

The main controller class is PaymentController and AuthController which handles the endpoints. Check the source code for more details. Once we are done wiht payment all the payment history we can check in the stripe dashboard .

Conclusion

Stripe offers a robust payment processing solution, and this project provides a straightforward integration into Java and Spring Boot applications. Feel free to explore the full code on GitHub.
