package com.car.rental.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean enabled;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true)
    private String nrc;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "customer")
    private Set<Booking> customerBookings = new HashSet<>();

    @OneToMany(mappedBy = "approvedBy")
    private Set<Booking> approvedBookings = new HashSet<>();

    public User() {
    }

    public User(Long id, String username, String email, String password, boolean enabled, String address, String nrc, Role role, Set<Booking> customerBookings, Set<Booking> approvedBookings) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.address = address;
        this.nrc = nrc;
        this.role = role;
        this.customerBookings = customerBookings;
        this.approvedBookings = approvedBookings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Booking> getCustomerBookings() {
        return customerBookings;
    }

    public void setCustomerBookings(Set<Booking> customerBookings) {
        this.customerBookings = customerBookings;
    }

    public Set<Booking> getApprovedBookings() {
        return approvedBookings;
    }

    public void setApprovedBookings(Set<Booking> approvedBookings) {
        this.approvedBookings = approvedBookings;
    }
}
