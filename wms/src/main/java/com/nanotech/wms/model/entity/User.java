package com.nanotech.wms.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cs_users")
public class User extends BaseEntity implements UserDetails {

    private String username;

    private String password;

    private String mainPhoto;

    private String email;

    @ManyToOne
    @JoinColumn(name = "org_id")
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }
}
