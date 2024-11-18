package br.com.senai.controller;


import br.com.senai.dto.EnderecoViaCepDTO;
import br.com.senai.entity.EnderecoEntity;
import br.com.senai.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping ("/listar")
    public List<EnderecoEntity> listarEnderecos(){
        return enderecoService.listarEnderecos();
    }

    @PostMapping
    public EnderecoEntity cadastrarEndereco(@RequestBody EnderecoEntity endereco){
        return enderecoService.cadastrarEndereco(endereco);
    }

    @GetMapping("/{idEndereco}")
    public EnderecoEntity buscarEnderecoPorId(@PathVariable Long idEndereco){
        return enderecoService.buscarEnderecoPorId(idEndereco);
    }

    @GetMapping("/CEP/{cep}")
    public EnderecoEntity buscarEnderecoPorCEP(@PathVariable String cep){
        String Cep = cep.toUpperCase();
        return enderecoService.buscarEnderecoPorCep(cep);
    }

    @GetMapping("/cidade/{cidade}")
    public List<EnderecoEntity> buscarEnderecoPorCidade(@PathVariable String cidade){
        return enderecoService.buscarEnderecoPorCidade(cidade);
    }

    @GetMapping("/uf/{uf}")
    public List<EnderecoEntity> buscarEnderecoPorUF(@PathVariable String uf){
        String Uf = uf.toUpperCase();
        return enderecoService.buscarEnderecoPorUF(uf);
    }

    @DeleteMapping("/{idEndereco}")
    public void excluirEndereco(@PathVariable Long idEndereco){
        enderecoService.excluirEndereco(idEndereco);
    }

    @PutMapping("/editar/{idEndereco}")
    public EnderecoEntity editarEndereco(@PathVariable Long idEndereco, @RequestBody EnderecoEntity endereco){
        return enderecoService.editarEndereco(idEndereco, endereco);
    }

    @GetMapping("/viacep/{cep}")
    public EnderecoViaCepDTO buscaEnderecoViaCep(@PathVariable String cep) {
        return enderecoService.buscaEnderecoViaCep(cep);
    }

}
