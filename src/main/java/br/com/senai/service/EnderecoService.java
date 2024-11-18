package br.com.senai.service;


import br.com.senai.dto.EnderecoViaCepDTO;
import br.com.senai.entity.EnderecoEntity;
import br.com.senai.exception.EntidadeException;
import br.com.senai.repository.EnderecoRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public EnderecoEntity cadastrarEndereco(EnderecoEntity endereco){
        return enderecoRepository.save(endereco);
    }

    public List<EnderecoEntity> listarEnderecos(){
        return enderecoRepository.findAll();
    }

    public EnderecoEntity buscarEnderecoPorId(Long idEndereco){
        return enderecoRepository.findById(idEndereco).orElseThrow(() -> new EntidadeException("Cadastro de Endereco não encontrado!!"));
    }

    public EnderecoEntity buscarEnderecoPorCep(String cep){
        return enderecoRepository.buscarEnderecoPorCep(cep).orElseThrow(() -> new EntidadeException("Endereço não encontrado"));

    }

    public List<EnderecoEntity> buscarEnderecoPorCidade(String cidade){
        return enderecoRepository.buscarEnderecoPorCidade(cidade);
    }

    public List<EnderecoEntity> buscarEnderecoPorUF(String uf){
        return enderecoRepository.buscarEnderecoPorUF(uf);
    }

    public void excluirEndereco(Long idEndereco){
        enderecoRepository.deleteById(idEndereco);
    }

    public EnderecoEntity editarEndereco(Long idEndereco, EnderecoEntity endereco){
        EnderecoEntity enderecoOld = enderecoRepository.findById(idEndereco).orElseThrow(() -> new EntidadeException("Endereço não encontrado."));
        enderecoOld.setUf(endereco.getUf());
        enderecoOld.setCep(endereco.getCep());
        enderecoOld.setBairro(endereco.getBairro());
        enderecoOld.setEstado(endereco.getEstado());
        enderecoOld.setRua(endereco.getRua());
        endereco.setCidade(endereco.getCidade());

        return enderecoRepository.save(enderecoOld);
    }

    public EnderecoViaCepDTO buscaEnderecoViaCep(String cep) {
        final String apiViaCep = "http://viacep.com.br/ws/";
        final int statusCode = 200;

        try {
            URL url = new URL(apiViaCep + cep + "/json");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(connection.getResponseCode() != statusCode) {
                throw new RuntimeException("HTTP error code: " + connection.getResponseCode());
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String resposta, jsonViaCep = "";
            while((resposta = bufferedReader.readLine()) != null) {
                jsonViaCep += resposta;
            }

            Gson gson = new Gson();
            EnderecoViaCepDTO endereco = gson.fromJson(jsonViaCep, EnderecoViaCepDTO.class);

            return endereco;
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

}
