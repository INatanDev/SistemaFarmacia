package com.InatandevClari.farmacia.repository.funcionario;

import com.InatandevClari.farmacia.entities.funcionarios.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionarios, Long> {
    Optional<Funcionarios> findByUsuario(String usuario);
}
