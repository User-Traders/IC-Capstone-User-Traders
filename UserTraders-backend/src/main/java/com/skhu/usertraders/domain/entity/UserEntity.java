//package com.skhu.usertraders.domain.entity;
//
//
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//@Entity
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Getter
//@Table(name = "user")
//public class UserEntity extends TimeEntity implements UserDetails {
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Column(name = "userid")
//    private String userid;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "tel")
//    private String tel;
//
//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private DepartmentEntity department;
//
//
//    @Column(name = "name")
//    private String name;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();
//
//
//    @Builder
//    public UserEntity(Integer id , String userid , String password , String tel ,
//                      DepartmentEntity department , String name){
//
//        this.id = id;
//        this.userid = userid;
//        this.password = password;
//        this.tel = tel;
//        this.department = department;
//        this.name = name;
//    }
//
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return userid;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
