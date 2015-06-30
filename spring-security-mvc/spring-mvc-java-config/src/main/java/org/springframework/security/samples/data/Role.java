package org.springframework.security.samples.data;

/**
 * @author wvergara, created on 6/9/15.
 */

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Siva
 *
 */
@Entity
@Table(name = "ROLES")
public class Role implements Serializable
{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;
    @Column(name="role_name",nullable=false)
    private String roleName;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }
    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

}