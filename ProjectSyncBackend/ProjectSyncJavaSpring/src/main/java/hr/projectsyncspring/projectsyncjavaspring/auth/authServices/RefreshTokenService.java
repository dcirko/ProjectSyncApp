package hr.projectsyncspring.projectsyncjavaspring.auth.authServices;

import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.MyUser;
import hr.projectsyncspring.projectsyncjavaspring.auth.authDomain.RefreshToken;
import hr.projectsyncspring.projectsyncjavaspring.auth.authRepositories.RefreshTokenRepository;
import hr.projectsyncspring.projectsyncjavaspring.auth.authRepositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private UserRepository userRepository;

    @Value("${jwt.refresh-token-validity-ms}")
    private Long refreshTokenValidityMs;

    public RefreshToken createRefreshToken(String email){
       Optional<MyUser> userOpt = userRepository.findByEmail(email);

       if(userOpt.isEmpty()){
           throw new RuntimeException("User not found");
       }
       MyUser user = userOpt.get();
       refreshTokenRepository.deleteByUser_Id(user.getId());

       RefreshToken refreshToken = RefreshToken.builder()
                .user(user)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(refreshTokenValidityMs))
                .build();
       return refreshTokenRepository.save(refreshToken);
    }

    public void deleteRefreshToken(RefreshToken token){
        refreshTokenRepository.delete(token);
    }

    public Optional<RefreshToken> findByToken(String token){return refreshTokenRepository.findByToken(token);}

    public RefreshToken verifyExpiration(RefreshToken token){
        if(token.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token is expired. Please make a new login..!");
        }
        return token;
    }
}
