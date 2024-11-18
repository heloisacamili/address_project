package br.com.senai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoViaCepDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
    private String estado;

}
