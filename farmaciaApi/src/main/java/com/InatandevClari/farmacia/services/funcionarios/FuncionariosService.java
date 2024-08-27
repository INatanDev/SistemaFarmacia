package com.InatandevClari.farmacia.services.funcionarios;

import com.InatandevClari.farmacia.entities.funcionarios.Funcionarios;
import com.InatandevClari.farmacia.dto.funcionario.FuncionarioDTO;
import com.InatandevClari.farmacia.repository.funcionario.FuncionarioRepository;
import com.InatandevClari.farmacia.services.exception.DatabaseException;
import com.InatandevClari.farmacia.services.exception.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionariosService {

    @Autowired
    private FuncionarioRepository repository;

    @Transactional(readOnly = true)
    public Page<FuncionarioDTO> findAllPaged(PageRequest pageRequest) {
        Page<Funcionarios> list = repository.findAll(pageRequest);
        return list.map(x -> new FuncionarioDTO(x));
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO findById(Long id) {
        Optional<Funcionarios> obj = repository.findById(id);
        Funcionarios entity = obj.orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado!!! Por favor informar ao time de suporte para análise. "));
        return new FuncionarioDTO(entity);
    }

    @Transactional
    public FuncionarioDTO insert(FuncionarioDTO dto) {
        Funcionarios entity =  new Funcionarios();
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setRg(dto.getRg());
        entity.setEndereco(dto.getEndereco());
        entity.setNumCasa(dto.getNumCasa());
        entity.setComplemento(dto.getComplemento());
        entity.setBairro(dto.getBairro());
        entity.setCidade(dto.getCidade());
        entity.setUf(dto.getUf());
        entity.setCep(dto.getCep());
        entity.setTel(dto.getTel());
        entity.setCel(dto.getCel());
        entity.setEmail(dto.getEmail());
        entity.setSexo(dto.getSexo());
        entity = repository.save(entity);
        return new FuncionarioDTO(entity);
    }

    @Transactional
    public FuncionarioDTO update(Long id, FuncionarioDTO dto) {
        try{
            Funcionarios entity = repository.getReferenceById(id);
            entity.setNome(dto.getNome());
            entity.setCpf(dto.getCpf());
            entity.setRg(dto.getRg());
            entity.setEndereco(dto.getEndereco());
            entity.setNumCasa(dto.getNumCasa());
            entity.setComplemento(dto.getComplemento());
            entity.setBairro(dto.getBairro());
            entity.setCidade(dto.getCidade());
            entity.setUf(dto.getUf());
            entity.setCep(dto.getCep());
            entity.setTel(dto.getTel());
            entity.setCel(dto.getCel());
            entity.setEmail(dto.getEmail());
            entity.setSexo(dto.getSexo());
            entity = repository.save(entity);
            return new FuncionarioDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado!!");
        }

        try{
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial!");
        }
    }


}
