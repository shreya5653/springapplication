////package com.myfirstproject.springapplication;
////
////import org.springframework.context.annotation.Bean;
////import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
////import org.springframework.security.web.SecurityFilterChain;
////
////@Configuration
////public class SecurityConfig {
////    @Bean
////    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(csrf -> csrf.disable()) // Disable CSRF using lambda
////                .authorizeHttpRequests(auth -> auth
////                        .requestMatchers("/workers/**").permitAll() // Allow access to farmer endpoints
////                        .anyRequest().authenticated() // Require authentication for all other endpoints
////                );
////
////        return http.build();
////    }
////}
//<!DOCTYPE html>
//<html xmlns:th="http://www.thymeleaf.org">
//<head>
//<title>Edit Farmer</title>
//</head>
//<body>
//<h1>Edit Farmer</h1>
//<form th:action="@{/farmers/{id}(id=${farmer.id})}" th:object="${farmer}" method="post">
//<label for="fname">Name:</label>
//<input type="text" id="fname" th:field="*{fname}" required>
//
//<label for="username">Username:</label>
//<input type="text" id="username" th:field="*{username}" required>
//
//<label for="password">Password:</label>
//<input type="password" id="password" th:field="*{password}" required>
//
//<button type="submit">Update Farmer</button>
//</form>
//<a th:href="@{/farmers}">Back to List</a>
//</body>
//</html>
//
//
//
//FARMER EDIT
//
//<!DOCTYPE html>
//<html xmlns:th="http://www.thymeleaf.org">
//<head>
//<title>Farmers List</title>
//</head>
//<body>
//<h1>Farmers</h1>
//<a th:href="@{/farmers/new}">Add New Farmer</a>
//<table>
//<thead>
//<tr>
//<th>ID</th>
//<th>Name</th>
//<th>Username</th>
//<th>Actions</th>
//</tr>
//</thead>
//<tbody>
//<tr th:each="farmer : ${farmers}">
//<td th:text="${farmer.id}"></td>
//<td th:text="${farmer.fname}"></td>
//<td th:text="${farmer.username}"></td>
//<td>
//<a th:href="@{/farmers/{id}/edit(id=${farmer.id})}">Edit</a>
//<a th:href="@{/farmers/{id}/delete(id=${farmer.id})}">Delete</a>
//</td>
//</tr>
//</tbody>
//</table>
//</body>
//</html>
//  FARMER LIST
//
//<!DOCTYPE html>
//<html xmlns:th="http://www.thymeleaf.org">
//<head>
//<title>Add New Farmer</title>
//</head>
//<body>
//<h1>Add New Farmer</h1>
//<form th:action="@{/farmers}" th:object="${farmer}" method="post">
//<label for="fname">Name:</label>
//<input type="text" id="fname" th:field="*{fname}" required>
//
//<label for="username">Username:</label>
//<input type="text" id="username" th:field="*{username}" required>
//
//<label for="password">Password:</label>
//<input type="password" id="password" th:field="*{password}" required>
//
//<button type="submit">Add Farmer</button>
//</form>
//<a th:href="@{/farmers}">Back to List</a>
//</body>
//</html>
// FARMER NEW
