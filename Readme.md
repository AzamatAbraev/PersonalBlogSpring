# Spring Boot Web Application

## Overview
This is a **Spring Boot + Thymeleaf** web application that includes:
- A **home page** that serves as the main entry point.
- **Article management** (CRUD operations) using Thymeleaf.

## Features
- View, create, edit, and delete articles.
- Responsive and professional UI.

## Getting Started
### Prerequisites
Ensure you have the following installed:
- **Java 17+**
- **Maven**
- **Spring Boot 3.x**

### Installation & Running the Application
1. **Clone the repository**:
   ```sh
   git clone https://github.com/AzamatAbraev/PersonalBlogSpring
   cd your-project
   ```
2. **Build and run the application**:
   ```sh
   mvn spring-boot:run
   ```
3. Open your browser and go to:
   ```
   http://localhost:8080/home
   ```

## Routing
| Route           | Description                  |
|----------------|------------------------------|
| `/home`        | Main page displaying articles |
| `/create`      | Create a new article         |
| `/edit/{id}`   | Edit an existing article     |
| `/delete/{id}` | Delete an article           |

## Styling
The application uses a **minimalistic and responsive design**. CSS files are located in `src/main/resources/static/styles.css`.

## License
This project is open-source and available under the [MIT License](LICENSE).

