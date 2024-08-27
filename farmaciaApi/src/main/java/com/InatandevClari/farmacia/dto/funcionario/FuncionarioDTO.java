package com.InatandevClari.farmacia.dto.funcionario;

import com.InatandevClari.farmacia.entities.funcionarios.Funcionarios;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioDTO {

   private Long id;
   private String nome;
   private String cpf;
   private String rg;
   private String endereco;
   private String numCasa;
   private String complemento;
   private String bairro;
   private String cidade;
   private String uf;
   private String cep;
   private String tel;
   private String cel;
   private String email;
   private char sexo;

    public FuncionarioDTO(Funcionarios entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.cpf = entity.getCpf();
        this.rg = entity.getRg();
        this.endereco = entity.getEndereco();
        this.numCasa = entity.getNumCasa();
        this.complemento = entity.getComplemento();
        this.bairro = entity.getBairro();
        this.cidade = entity.getCidade();
        this.uf = entity.getUf();
        this.cep = entity.getCep();
        this.tel = entity.getTel();
        this.cel = entity.getCel();
        this.email = entity.getEmail();
        this.sexo = entity.getSexo();
    }
}
