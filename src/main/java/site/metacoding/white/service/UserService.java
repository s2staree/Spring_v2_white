package site.metacoding.white.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.User;
import site.metacoding.white.domain.UserRepository;

// 서비스의 역할: 트랜잭션 관리
// DTO 변환해서 컨트롤러에게 돌려줘야함

@RequiredArgsConstructor // 이거 없으면 디폴트생성자
@Service // 이거 안붙이면 IoC(메모리)에 안뜸
public class UserService {

    private final UserRepository userRepository;

    @Transactional // 트랜잭션을 붙이지 않으면 영속화 되어 있는 객체가 flush가 안됨 -> PC에만 들어가고 DB에는 안들어감
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User login(User user) {
        User userPS = userRepository.findByUsername(user.getUsername());
        if (userPS.getPassword().equals(user.getPassword())) {
            return userPS;
        } else {
            throw new RuntimeException("아이디 혹은 패스워드가 잘못 입력되었습니다.");
        }
    } // 트랜잭션 종료 (자동 flush 발생)

}
