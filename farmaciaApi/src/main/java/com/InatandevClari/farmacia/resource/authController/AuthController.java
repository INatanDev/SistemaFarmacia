package com.InatandevClari.farmacia.resource.authController;

import com.InatandevClari.farmacia.dto.LoginRequestDTO;
import com.InatandevClari.farmacia.dto.RegisterRequestDTO;
import com.InatandevClari.farmacia.dto.ResponseDTO;
import com.InatandevClari.farmacia.entities.funcionarios.Funcionarios;
import com.InatandevClari.farmacia.infra.security.TokenService;
import com.InatandevClari.farmacia.repository.funcionario.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final FuncionarioRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body) {
        Funcionarios funcionarios = this.repository.findByUsuario(body.usuario()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(body.senha(), funcionarios.getSenha() )){
            String token = this.tokenService.generatedToken(funcionarios);
            return ResponseEntity.ok(new ResponseDTO(funcionarios.getNome(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<Funcionarios> funcionarios = this.repository.findByUsuario(body.usuario());

        if(funcionarios.isEmpty()){
            Funcionarios newFuncionarios = new Funcionarios();
            newFuncionarios.setSenha(passwordEncoder.encode(body.senha()));
            newFuncionarios.setUsuario(body.usuario());
            newFuncionarios.setNome(body.nome());
            this.repository.save(newFuncionarios);

            String token = this.tokenService.generatedToken(newFuncionarios);
            return ResponseEntity.ok(new ResponseDTO(newFuncionarios.getNome(), token));

        }

        return ResponseEntity.badRequest().build();
    }
}
