package com.skhu.usertraders.service;

import com.skhu.usertraders.config.JwtTokenProvider;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.UserRepository;
import com.skhu.usertraders.dto.DepartmentDto;
import com.skhu.usertraders.dto.UserDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService {
// 아니여기서 @AutoWired쓰면 파라미터값이 1개가아니라서 안되고
    //@RequiredArgsConstructor 써서 final 객체 부르면 스프링 빈 종속성 문제에서 순환 참조 문제가 발생된다.
    // 따라서 생성자 주입해줄때 @LAZY로 지연로딩 시켜준다. 허나 이건 임시방편

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailService(@Lazy UserRepository userRepository, @Lazy JwtTokenProvider jwtTokenProvider, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }


    private UserDto UserEntityToDto(UserEntity userEntity) {//나중에 쓸 일 있을것 같아서 유저정보들 객체에 담아놨음
        return UserDto.builder()
                .id(userEntity.getId())
                .userid(userEntity.getUserid())
                .password(userEntity.getPassword())
                .tel(userEntity.getTel())
                .department(userEntity.getDepartment())
                .createdDate(userEntity.getCreatedDate())
                .modifiedDate(userEntity.getModifiedDate())
                .name(userEntity.getName())
                .roles(userEntity.getRoles())
                .build();
    }

    @Transactional //회원 로그인
    public String login(Map<String, String> user) {
        UserEntity userEntity = userRepository.findByUserid(user.get("userid"))
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), userEntity.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(userEntity.getUsername(), userEntity.getRoles());
    }


    /**
     * Spring Security 필수 메소드 구현
     *
     * @param userid 이메일 아이디
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        return userRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Transactional // 회원가입 정보 저장
    public Integer save(UserDto userDto) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        userDto.setPassword(encoder.encode(userDto.getPassword()));
//
//        return userRepository.save(userDto.convertDtoToEntity()).getId();


        return userRepository.save(UserEntity.builder()
                .userid(userDto.getUserid())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .tel(userDto.getTel())
                .name(userDto.getName())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .department(userDto.getDepartment())
                .build()).getId();

    }

    @Transactional // 회원 한명 정보 조회
    public UserDto find(int id) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(id);
        UserEntity userEntity = userEntityWrapper.get();

        return this.UserEntityToDto(userEntity);
    }

    @Transactional // 회원 한명 정보 수정
    public Integer updateById(UserDto userDto) {
        Optional<UserEntity> userEntityWrapper = userRepository.findById(userDto.getId());

        userEntityWrapper.ifPresent(userEntity -> {
            userEntity = userDto.convertDtoToEntity();
            userRepository.save(userEntity);
        });

        return userEntityWrapper.get().getId();
    }

    @Transactional
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
