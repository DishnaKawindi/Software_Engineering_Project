# vue_coffeeshop cs3275_coffeeshop

## Project setup

```bash
npm install
```

### Compiles and hot-reloads for development

```bash
npm run serve
```

### Compiles and minifies for production

```bash
npm run build
```

### Lints and fixes files

```bash
npm run lint
```

### Customize configuration

See [Configuration Reference](https://cli.vuejs.org/config/).

### Miscellaneous

#### Issue with CORS on server side

- To fix the issue with CORS on the server side, Please add the following code to the server side code at `SecurityConfig.java` file.

```java

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(java.util.List.of("http://localhost:8081"));
        configuration.setAllowedMethods(java.util.List.of("GET","POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(java.util.List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
```

As you can see, the `setAllowedOrigins` method is used to set the allowed origins. In this case, the allowed origin is `http://localhost:8081`. You can change this to your own origin.

#### Issue with Security on Client side

To make job easier, I have hardcode the username and password in the constructor of `AdminService.js` file. You can change this to your own username and password.

```javascript
    constructor() {
        this.username = "admin";
        this.password = "admin";
    }
```

#### Issue with the POSTMAN and documentations

Some of the endpoints aren't available in the POSTMAN collection. Such as DELETE and PUT endpoints. You can add them manually to the POSTMAN collection.

In addtion, the Typo in the `customizations['Milk Type']` object might cause some issues and not following the standard naming convention. You can change this to `customizations['MilkType']` to follow the standard naming convention or predefined naming convention.
