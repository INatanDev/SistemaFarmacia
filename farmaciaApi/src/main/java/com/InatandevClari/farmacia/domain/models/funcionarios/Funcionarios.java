package com.InatandevClari.farmacia.domain.models.funcionarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "funcionarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Funcionarios {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String cpf;

    private String rg;

    private String usuario;

    private String senha;

    private String endereco;

    private String numCasa;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    private String cep;

    private Date dtaRegistro;

    private String tel;

    private String cel;

    private String email;

    private char sexo;

    private char admin;

}
