# OrbKart - E-commerce Backend

OrbKart is a robust backend for a modern e-commerce platform. This system features secure JWT & Cookie based authentication (role based also), admin/seller management, Stripe payment integration, image uploads, Custom eception handling ,pagination, filteration and fully-documented REST APIs. Ideal for scalable, production-grade online stores.

---

## Features

- **Product CRUD**: REST APIs for product management, including secure image uploads.
- **Security**: JWT and cookie-based authentication, custom roles for admin, seller, user.
- **API Docs**: Interactive Swagger/OpenAPI documentation for all routes.
- **Payment Integration**: Stripe gateway integration for seamless payments.
- **Role-Based Backend**: Separate APIs for admin, sellers, and user.
- **Pagination & Filtering**: Efficient endpoints to view and manage data.
- **Custom Exceptions**: Consistent error handling across requests.
- **DTOs**: Data Transfer Objects for clean request/response bodies.
- **Logging**: Centralized, formatted logs for all actions.
- **Commit Style**: Every commit is formatted and descriptive for easy onboarding.

---

## Getting Started
git clone (https://github.com/Aryanyadav99/OrbKart_Backend.git)

cd orbkart-backend

Install dependencies and configure your environment.

See the .env.example file.

:- .env only contains the stripe Secret Key strt with sk see in your stripe account

Run the backend:

bash
./mvnw spring-boot:run

## Screenshots

![Product API Overview](images/product-api.png)
![Admin Dashboard](images/admin-dashboard.png)
![Order Flow](images/order-flow.png)

---

## API Documentation

Access the interactive docs at `/swagger-ui/` once the server is running.
- Full endpoint listing
- Example requests/responses
- Role-based access descriptions

---
## Screenshots

![Product API Overview](images/product-api.png)
![Admin Dashboard](images/admin-dashboard.png)
![Order Flow](images/order-flow.png)

---

## Project Structure

<pre> Ecommerce/
  ├── Configuration/
  │    ├── AppConfig.java
  │    ├── AppConstants.java
  │    ├── CorsConfig.java
  │    ├── SwaggerConfig.java
  │    ├── Webconfig.java
  │    └── test.java
  ├── Controller/
  │    ├── AddressController.java
  │    ├── AuthController.java
  │    ├── CartController.java
  │    ├── CategoryController.java
  │    ├── OrderController.java
  │    └── ProductController.java
  ├── Exception/
  │    ├── APIException.java
  │    ├── GlobalExceptionHandler.java
  │    └── ResourceNotFoundException.java
  ├── Model/
  │    ├── Address.java
  │    ├── AppRole.java
  │    ├── Cart.java
  │    ├── CartItem.java
  │    ├── Category.java
  │    ├── Order.java
  │    ├── OrderItem.java
  │    ├── Payment.java
  │    ├── Product.java
  │    ├── Role.java
  │    └── User.java
  ├── Payload/
  │    ├── APIResponse.java
  │    ├── AddressDTO.java
  │    ├── CartDTO.java
  │    ├── CartItemDTO.java
  │    ├── CategoryDTO.java
  │    ├── CategoryResponse.java
  │    ├── OrderDTO.java
  │    ├── OrderItemDTO.java
  │    ├── OrderRequestDTO.java
  │    ├── OrderResponse.java
  │    ├── OrderStatusUpdateDTO.java
  │    ├── PaymentDTO.java
  │    ├── ProductDTO.java
  │    ├── ProductResponse.java
  │    └── StripePaymentDto.java
  ├── Repositories/
  │    ├── AddressRepository.java
  │    ├── CartItemRepository.java
  │    ├── CartRepository.java
  │    ├── CategoryRepo.java
  │    ├── OrderItemRepo.java
  │    ├── OrderRepo.java
  │    ├── PaymentRepo.java
  │    ├── ProductRepo.java
  │    ├── RoleRepository.java
  │    └── UserRepository.java
  ├── Security/
  │    ├── JWT/
  │    │    ├── AuthEntryPointJwt.java
  │    │    ├── AuthTokenFilter.java
  │    │    └── JwtUtils.java
  │    ├── Request/
  │    │    ├── LoginRequest.java
  │    │    └── SignupRequest.java
  │    ├── Response/
  │    │    ├── MessageResponse.java
  │    │    └── UserInfoResponse.java
  │    ├── Services/
  │    │    ├── UserDetailsImpl.java
  │    │    └── UserDetailsServiceImpl.java
  │    └── WebSecurityConfig.java
  ├── Service/
  │    ├── ServiceImpl/
  │    │    ├── AddressServiceImpl.java
  │    │    ├── AuthServiceImpl.java
  │    │    ├── CartServiceImpl.java
  │    │    ├── CategoryServiceImpl.java
  │    │    ├── OrderServiceImpl.java
  │    │    ├── ProductServiceImpl.java
  │    │    └── StripeServiceImpl.java
  │    ├── AddressService.java
  │    ├── AuthService.java
  │    ├── CartService.java
  │    ├── CategoryService.java
  │    ├── OrderService.java
  │    ├── ProductService.java
  │    └── StripeService.java
  ├── Util/
  │    └── AuthUtil.java
  └── EcommerceBackendApplication.java </pre>

## Clean Commits for every steps 

All commits follow a clear, descriptive format:
Recent Commits - Product Module
feat(product): Implemented product image section allowing image upload and update
View Commit

- fix(product): Shifted from data layer to presentation layer by using DTOs for product data

- feat(product): Implemented delete product endpoint with validation

- feat(product): Added product update endpoint including fields update

- feat(product): Enabled search product by keyword functionality

- feat(product): Added Get Product by Category API

- feat(product): Implemented basic product APIs including GET and POST endpoints

---

## Contribution

Pull requests are welcome. Please keep commit formatting consistent and document API changes in Swagger.

---

## Future feature to be added  ..
- demand section
- rent part
- and many more stay updated
