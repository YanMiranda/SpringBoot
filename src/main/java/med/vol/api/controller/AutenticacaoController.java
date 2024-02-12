package med.vol.api.controller;

import jakarta.validation.Valid;
import med.vol.api.domain.user.Usuario;
import med.vol.api.domain.user.records.DadosAutenticacao;
import med.vol.api.infra.security.DadosTokenJwt;
import med.vol.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid DadosAutenticacao data) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.login(), data.senha());
        Authentication auth = manager.authenticate(token);

        String authToken = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJwt(authToken));

    }
}
