# Stripe Payment Microservice API

This project implements a **Stripe-based payment system** using Spring Boot. The microservice handles user creation, payment processing, and Stripe webhook integration. It is designed for an e-commerce platform, allowing users to securely purchase products with Stripe's payment gateway. The API integrates Stripe for processing payments, managing customer data, and handling webhook events to confirm successful payments.

## Features

- **Create Users**: POST `/v1/auth` to register a user with product and customer details.
- **Handle Webhook Events**: POST `/v1/stripe/webhook` to listen and process Stripe webhook events such as `payment_intent.succeeded`.
- **Checkout**: POST `/v1/stripe/checkout` to create a checkout session for processing payments.

## Technologies Used

- **Spring Boot 3.3.4: Core framework for developing this microservice.**
- **Stripe API (Java SDK): Payment processing integration.**
- **PostgreSQL** for persistent data storage.
- **JSON Web Tokens (JWT)** for authentication and securing endpoints.
- **Lombok** for reducing boilerplate code.
- **Maven** for project management.

## Prerequisites

Ensure you have the following software installed:

- Java 17
- Maven
- PostgreSQL
- Stripe Account (for secret and publishable keys)

Here's a short guide on how to log in to Stripe and set up your project for testing payments:

### Step-by-Step Guide to Log In and Set Up Stripe for the Project

1. **Sign Up or Log In to Stripe:**
    - Go to [Stripe's website](https://stripe.com/).
    - If you don't have an account, click on **Sign Up** to create one.
    - If you already have an account, click on **Sign In** and enter your credentials.
2. **Create a New Stripe Account for Test Mode (Optional):**
    - After logging in, you can create a new account under your Stripe dashboard specifically for testing.
    - Go to the **Dashboard** → **Accounts** → **New Account** and name it appropriately for your test environment.
3. **Activate Test Mode:**
    - In your Stripe Dashboard, toggle the **"View Test Data"** button in the left menu. This enables Test Mode, where you can simulate payments.
    - You’ll now see “Test mode” written on the top of the page and can use Stripe’s test credit cards for transactions.
4. **Obtain Your API Keys:**
    - In Test Mode, navigate to the **Developers** section on the sidebar.
    - Click **API keys** and copy the **Publishable key** and **Secret key** under the **Standard keys** section.
    - These keys are required to connect your Spring Boot app to Stripe.
5. **Set Up the `application.yaml`:**
    - Add the Stripe API keys to the `application.yaml` in your Spring Boot project:

        ```yaml
        
        stripe:
          apiKey: your_secret_key_here
          publishableKey: your_publishable_key_here
        ```


    ⚠️ **Important:** Never commit the `application.yaml` 
            containing sensitive API keys to a public repository. Ensure it is included in your `.gitignore` file.

6. **Test Payments:**
    - Use Stripe’s provided [test credit card numbers](https://stripe.com/docs/testing#international-cards) to simulate transactions in the application.
    - Example test card number: `4242 4242 4242 4242` (for Visa).
7. **Trigger Events for Testing:**
    - In Test Mode, use the `stripe trigger payment_intent.succeed` command to simulate the successful completion of a payment intent. This will help you test your Stripe webhook integration.

By following these steps, users will be able to log in to Stripe and configure their environment for testing payments with your Spring Boot project.

Here’s a guide for how users can install and set up Stripe on their machine for your Spring Boot project:

### Step-by-Step Guide to Install and Set Up Stripe

### 1. **Install Java and Maven**

- Make sure you have **Java 17** installed on your machine. You can download it from [Oracle’s website](https://www.oracle.com/java/technologies/javase-downloads.html).
- You’ll also need **Maven** to build the Spring Boot project. You can download Maven from [Apache Maven’s official website](https://maven.apache.org/download.cgi) if it's not installed.

### 2. **Clone the Project**

- Clone the project from your GitHub repository using the following command in your terminal or Git Bash:

    ```bash
    git clone <https://github.com/juansebstt/Stripe-payments-api>
    cd stripe-payment
    ```


### 3. **Install Dependencies**

- Inside the project directory, run the following command to download all required dependencies (defined in the `pom.xml`):

    ```bash
    mvn clean install
    ```


### 4. **Set Up Stripe Account**

- Follow the steps mentioned in the **Login to Stripe** section of the README to create a Stripe account and get your **API keys**.

### 5. **Configure `application.yaml`**

- You’ll need to create an `application.yaml` file to store your Stripe secret and publishable keys securely. Here’s a sample configuration:

    ```yaml
    stripe:
      apiKey: your_secret_key_here
      publishableKey: your_publishable_key_here
    ```

- Place this file in the `src/main/resources` directory. Make sure you **don’t commit** this file to a public repository, as it contains sensitive data.

### 6. **Run the Application**

- Once you’ve set everything up, run the Spring Boot application using Maven:

    ```bash
    mvn spring-boot:run
    ```

- The application will start up, and you can test the Stripe payment integration using the provided API endpoints.

### 7. **Install the Stripe CLI (Optional, for Local Testing)**

- If you want to test Stripe’s webhook events locally, you’ll need to install the **Stripe CLI** on your machine. Follow these steps:
    - For macOS, you can install it using Homebrew:

        ```bash
        install stripe/stripe-cli/stripe
        ```

    - For Windows or Linux, refer to [Stripe CLI installation instructions](https://stripe.com/docs/stripe-cli#install).
- Once installed, log in using your Stripe credentials:

    ```bash
    stripe login
    ```

- You can then trigger test events like `payment_intent.succeed` with the following command:

    ```bash
    stripe trigger payment_intent.succeed
    ```


### 8. **Test Payments**

- You can now make test payments through the API and simulate various events using Stripe’s test cards and the Stripe CLI.

By following these steps, users will have Stripe set up on their machine and will be ready to run the Spring Boot project.

## Setup

### Step 1: Configure the `application.yaml`

Since the `application.yaml` file contains sensitive information like your Stripe API keys, it is not included in the repository. Before running the application, you will need to create this file and set up the required environment variables.

Here is an example of what your `application.yaml` should contain:

```yaml
stripe:
  secret-key: sk_test_yourSecretKey
  public-key: pk_test_yourPublicKey
  webhook-secret: whsec_yourWebhookSecret

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/stripe_payment
    username: postgres
    password: yourPassword
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

### Step 2: Install dependencies

Run the following command to download all required dependencies:

```bash
mvn clean install
```

### Step 3: Running the application

Once everything is set up, you can start the application by running:

```bash
mvn spring-boot:run
```

The application will be available on `http://localhost:8080`.

## Project Structure

The project follows a modular structure for better scalability and maintenance. Key components include:

- **DTOs**: Located in `common/dto`, responsible for managing requests and responses (e.g., `CheckoutRequest`, `CheckoutResponse`, `UserRequest`).
- **Entities**: Located in `common/entities`, handling the `Payment` and `UserModel` entities.
- **Controllers**: Located in `controllers.impl`, managing HTTP requests and responses (e.g., `AuthController`, `StripeController`).
- **Services**: Business logic implemented in `services.impl`, including `AuthServiceImpl` and `StripeServiceImpl`.
- **Repositories**: Located in `repositories`, interacting with the database (e.g., `PaymentRepository`, `UserRepository`).
- **Strategies**: Defined in `strategy.impl`, following a strategy pattern to handle different payment events (e.g., `StripeStrategyCheckoutComplete`, `StripeStrategyPaymentSucceeded`).

## Endpoints

- **User Registration**:

  **POST** `/v1/auth`

  Register a new user along with a product and customer in the Stripe system.

- **Checkout**:

  **POST** `/v1/stripe/checkout`

  Initiates the payment process by creating a checkout session in Stripe.

- **Handle Webhooks**:

  **POST** `/v1/stripe/webhook`

  Receives and processes Stripe webhook events, specifically handling `payment_intent.succeeded` to finalize payments.


### **Set Up Webhooks with Stripe CLI**

- To forward Stripe webhook events to your local Spring Boot application, use the following command:

    ```bash
    stripe listen --forward-to localhost:8080/v1/stripe/webhook
    ```

- This command will forward webhook events like `payment_intent.succeeded` to your local application’s `/v1/stripe/webhook` endpoint. Keep this terminal running while you’re testing.

## Stripe Webhooks

This service listens to Stripe webhooks to handle different payment events. Make sure to configure your webhook URL in your Stripe dashboard as:

```bash
https://your-domain/v1/stripe/webhook
```

You need to secure the webhook endpoint by verifying the Stripe signature using the `webhook-secret`.

## Testing Webhooks

For testing Stripe webhook events locally, you can use Stripe's CLI tool to trigger events and simulate successful payments.

To trigger a `payment_intent.succeeded` event for testing, run the following command:

```bash
stripe trigger payment_intent.succeeded
```

This will send a simulated `payment_intent.succeeded` event to your `/v1/stripe/webhook` endpoint, which can be used to validate the handling of successful payment intents in your application.

## Dependencies

Here are the main dependencies used in this project (see `pom.xml` for details):

- **Spring Boot** (Data JPA, Web, REST)
- **Stripe Java SDK** (`com.stripe:stripe-java`)
- **JWT** (`io.jsonwebtoken`)
- **PostgreSQL JDBC Driver**
- **Lombok**

## Security Notes

The Stripe API keys and webhook secret must be kept secure and should never be hardcoded or committed to version control. Use environment variables or secure storage solutions like AWS Secrets Manager, Vault, or similar to manage these keys.

## Project Diagram
![Alt text](src/images/Stripe-diagram.png)


## Created by

**Juan Sebastian Ibarra**