package com.InatandevClari.farmacia.entities.funcionarios;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;
import java.util.Objects;

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
    @Column(length = 11)
    @CPF
    private String cpf;
    @Column(length = 11)
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
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaRegistro;
    private String tel;
    private String cel;
    private String email;
    private char sexo;
    private char admin;

    @PrePersist
    public void prePersist(){
        dtaRegistro = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Funcionarios that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(cpf, that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cpf);
    }
}
