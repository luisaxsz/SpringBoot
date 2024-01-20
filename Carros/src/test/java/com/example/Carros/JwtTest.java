package com.example.Carros;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.Carros.CarrosApplication;
import com.Carros.api.security.jwt.JwtUtil;
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CarrosApplication.class)
public class JwtTest {
	@Autowired
    @Qualifier("userDetailService")
    private UserDetailsService userDetailService;

    @Test
    public void testToken() {

        // Le usuário
        UserDetails user = userDetailService.loadUserByUsername("admin");
        assertNotNull(user);

        // Gera token
        String jwtToken = JwtUtil.createToken(user);
        System.out.println(jwtToken);
        assertNotNull(jwtToken);

        // Valida Token
        boolean ok = JwtUtil.isTokenValid(jwtToken);
        assertTrue(ok);

        // Valida login
        String login = JwtUtil.getLogin(jwtToken);
        assertEquals("admin",login);

        // Valida roles - > consegue recuperar role
        List<GrantedAuthority> roles = JwtUtil.getRoles(jwtToken);
        assertNotNull(roles);
        System.out.println(roles);
        String role = roles.get(0).getAuthority();
        assertEquals(role,"ROLE_ADMIN");
    }

}
