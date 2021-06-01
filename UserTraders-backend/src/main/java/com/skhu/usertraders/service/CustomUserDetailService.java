package com.skhu.usertraders.service;

import com.skhu.usertraders.config.JwtTokenProvider;
import com.skhu.usertraders.domain.entity.UserEntity;
import com.skhu.usertraders.domain.repository.UserRepository;
import com.skhu.usertraders.dto.user.UserDto;
import com.skhu.usertraders.exception.board.ApiIllegalArgumentException;
import com.skhu.usertraders.exception.board.ApiUserNullPointerException;
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
    // 여기서 @AutoWired 를쓰면 파라미터 값이 1개가 아니라서 안되고,
    // @RequiredArgsConstructor 써서 final 객체 부르면 스프링 빈 종속성 문제 에서 순환 참조 문제가 발생 된다.
    // 따라서 생성자 주입 해줄 때 @LAZY로 지연 로딩 시켜준다. 허나 이건 임시 방편이다.

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailService(@Lazy UserRepository userRepository, @Lazy JwtTokenProvider jwtTokenProvider, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

//     Spring Security 필수 메소드 구현
//     @param userid 이메일 아이디
//     @return UserDetails
//     @throws UsernameNotFoundException 유저가 없을 때 예외 발생

    @Transactional // 회원 로그인
    public String login(Map<String, String> user) {
        UserEntity userEntity = userRepository.findByUserid(user.get("userid"))
                .orElseThrow(() -> new ApiIllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        if (!passwordEncoder.matches(user.get("password"), userEntity.getPassword())) {
            throw new ApiIllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(userEntity.getUsername(), userEntity.getRoles());
    }

    @Transactional // Spring security 필수 메소드 구현
    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        return userRepository.findByUserid(userid)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Transactional // 유저 회원 가입 정보 저장
    public Integer save(UserDto userDto) {
        return userRepository.save(UserEntity.builder()
                .userid(userDto.getUserid())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .tel(userDto.getTel())
                .name(userDto.getName())
                .roles(Collections.singletonList("ROLE_USER")) // 최초 가입시 USER 로 설정
                .department(userDto.getDepartment())
                .build()).getId();
    }

    @Transactional
    public UserDto findByUserId(String userId){
        if (userId == null){
            throw new ApiUserNullPointerException("유저정보가 없습니다.");
        }
        Optional<UserEntity> userEntityWrapper = userRepository.findByUserid(userId);
        UserEntity userEntity = userEntityWrapper.get();
        return UserDto.builder().build().UserEntityToDto(userEntity);
    }

    @Transactional // 회원 한명 정보 조회
    public UserDto findById(UserEntity user) {
        if (user == null){
            throw new ApiUserNullPointerException("유저정보가 없습니다.");
        }
        Optional<UserEntity> userEntityWrapper = userRepository.findById(user.getId());
        UserEntity userEntity = userEntityWrapper.get();
        return UserDto.builder().build().UserEntityToDto(userEntity);
    }
    @Transactional // 회원 한명 정보 조회
    public UserDto findById(Integer id) {
        if (id == null){
            throw new ApiUserNullPointerException("유저정보가 없습니다.");
        }
        Optional<UserEntity> userEntityWrapper = userRepository.findById(id);
        UserEntity userEntity = userEntityWrapper.get();
        return UserDto.builder().build().UserEntityToDto(userEntity);
    }

    @Transactional // 회원 한명 정보 수정
    public Integer updateById(UserDto userDto,Integer id) {
        UserDto user = this.findById(id);
        Optional<UserEntity> userEntityWrapper = userRepository.findById(userDto.getId());
        userEntityWrapper.ifPresent(userEntity -> {
            userEntity = UserEntity.builder()
                    .id(userDto.getId())
                    .userid(userDto.getUserid())
                    .password(passwordEncoder.encode(userDto.getPassword()))
                    .tel(userDto.getTel())
                    .department(user.getDepartment())  //학과도 일단 변경 안되게
                    .name(userDto.getName())
                    .roles(user.getRoles()) // 역할은 유저로 변화하면 안되기때문에
                    .build();
            userRepository.save(userEntity);
        });
        return userEntityWrapper.get().getId();
    }

    @Transactional  //회원 한명 정보 삭제
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }
}
