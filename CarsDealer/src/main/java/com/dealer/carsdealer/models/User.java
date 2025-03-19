// package com.dealer.carsdealer.models;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Email;
// import jakarta.validation.constraints.NotEmpty;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

// import java.util.Collection;
// import java.util.Collections;

// @Entity
// @Table(name = "users")
// public class User implements UserDetails {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @NotEmpty(message = "El nombre no puede estar vacío")
//     private String name;

//     @NotEmpty(message = "El teléfono no puede estar vacío")
//     private String phone;

//     @Email(message = "El email debe ser válido")
//     @NotEmpty(message = "El email no puede estar vacío")
//     @Column(unique = true)
//     private String email;

//     @NotEmpty(message = "La contraseña no puede estar vacía")
//     private String password;

//     @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//     private Cart cart;

//     @NotEmpty
//     private String role;

//     public User() {
//     }

//     public User(String name, String phone, String email, String password, String role) {
//         this.name = name;
//         this.phone = phone;
//         this.email = email;
//         this.password = password;
//         this.role = role;
//     }

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getPhone() {
//         return phone;
//     }

//     public void setPhone(String phone) {
//         this.phone = phone;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public Cart getCart() {
//         return cart;
//     }

//     public void setCart(Cart cart) {
//         this.cart = cart;
//     }

//     public String getRole() {
//         return role;
//     }

//     public void setRole(String role) {
//         this.role = role;
//     }

//     @Override
//     public Collection<? extends GrantedAuthority> getAuthorities() {
//         return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
//     }

//     @Override
//     public String getUsername() {
//         return email;
//     }

//     @Override
//     public boolean isAccountNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isAccountNonLocked() {
//         return true;
//     }

//     @Override
//     public boolean isCredentialsNonExpired() {
//         return true;
//     }

//     @Override
//     public boolean isEnabled() {
//         return true;
//     }
// }