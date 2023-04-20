package com.innowise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

/**

 The User class is an entity class that represents a user in the system. It maps to the "users" table in the database.

 This class uses the @Entity annotation to indicate that it is an entity class and the @Table annotation to specify

 the name of the table it maps to. It also uses the @AllArgsConstructor, @NoArgsConstructor, @Builder and @Data annotations

 from the Lombok library to generate constructors, builder methods and getter/setter methods for its fields.

 The fields of this class map to columns in the "users" table using the @Id, @GeneratedValue, @Column, @ManyToOne and @JoinColumn

 annotations. The @Id and @GeneratedValue annotations are used to specify that the "id" column is the primary key of the table

 and that it is generated automatically by the database. The @Column annotation is used to specify the name of the columns

 that map to the "username" and "password" fields. The @ManyToOne and @JoinColumn annotations are used to specify the

 many-to-one relationship between users and authorities, with the "authority_id" column in the "users" table acting as the

 foreign key to the "id" column in the "authorities" table.

 This class also provides a getter method for the name of the user's authority, which is obtained by calling the getName

 method of the associated Authorization instance.
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {

    /**

     The primary key of the "users" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**

     The username of the user.
     */
    @Column(name = "username")
    private String username;
    /**

     The password of the user.
     */
    @Column(name = "password")
    private String password;
    /**

     The authority of the user.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "authority_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private Authorization authority;
    /**

     Returns the name of the user's authority.
     @return the name of the user's authority
     */
    public String getAuthorityName() {
        return this.authority.getName();
    }
}