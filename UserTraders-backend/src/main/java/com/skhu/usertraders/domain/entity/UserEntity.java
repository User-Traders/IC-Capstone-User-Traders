package com.skhu.usertraders.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "user")
public class UserEntity extends TimeEntity implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userid")
    private String userid;

    @Column(name = "password")
    private String password;

    @Column(name = "tel")
    private String tel;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;


    @Column(name = "name")
    private String name;


    @ElementCollection(fetch = FetchType.EAGER)

    @CollectionTable(
            name = "role",
            joinColumns = @JoinColumn(name = "id")
    )

    private List<String> roles = new ArrayList<>();


    @Builder
    public UserEntity(Integer id, String userid, String password, String tel,
                      DepartmentEntity department, String name, List<String> roles) {

        this.id = id;
        this.userid = userid;
        this.password = password;
        this.tel = tel;
        this.department = department;
        this.name = name;
        this.roles = roles;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    // 사용자의 id를 반환 (unique한 값)
    @Override
    public String getUsername() {
        return userid;
    }

    // 사용자의 password를 반환
    @Override
    public String getPassword() {
        return password;
    }

    // 계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        // 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        // 계정 잠금되었는지 확인하는 로직
        return true; // true -> 잠금되지 않았음
    }

    // 패스워드의 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        // 패스워드가 만료되었는지 확인하는 로직
        return true; // true -> 만료되지 않았음
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직
        return true; // true -> 사용 가능
    }
}

