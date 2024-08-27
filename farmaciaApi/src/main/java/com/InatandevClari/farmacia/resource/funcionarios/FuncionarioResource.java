package com.InatandevClari.farmacia.resource.funcionarios;

import com.InatandevClari.farmacia.dto.funcionario.FuncionarioDTO;
import com.InatandevClari.farmacia.services.funcionarios.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioResource {

    @Autowired
    private FuncionariosService service;

    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> findAllPaged(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
                                                             @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                             @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
    ){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<FuncionarioDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id){
        FuncionarioDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> insert(@RequestBody FuncionarioDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping
    public ResponseEntity<FuncionarioDTO> update(@PathVariable Long id ,@RequestBody FuncionarioDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }
}
