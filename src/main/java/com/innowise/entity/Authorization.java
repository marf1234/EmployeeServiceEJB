package com.innowise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents an authorization entity.
 **/
@Data
@Table(name = "authority")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Authorization {
    /**
     * The unique identifier for the authorization.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * The name of the authorization.
     */
    @Column(name = "authority")
    private String name;
    /**
     * The users associated with the authorization.
     */
    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<User> users;
}