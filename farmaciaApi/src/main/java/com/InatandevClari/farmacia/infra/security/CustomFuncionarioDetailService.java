package com.InatandevClari.farmacia.infra.security;

import com.InatandevClari.farmacia.entities.funcionarios.Funcionarios;
import com.InatandevClari.farmacia.repository.funcionario.FuncionarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomFuncionarioDetailService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionarios funcionarios = this.repository.findByUsuario(username).orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
        return new org.springframework.security.core.userdetails.User(funcionarios.getUsuario(), funcionarios.getSenha(), new ArrayList<>());
    }
}
