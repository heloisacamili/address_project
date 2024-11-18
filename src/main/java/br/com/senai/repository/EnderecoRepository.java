package br.com.senai.repository;

import br.com.senai.entity.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Long> {

    @Query("select e from EnderecoEntity e where e.cep = ?1")
    Optional<EnderecoEntity> buscarEnderecoPorCep(String cep);

    @Query("select e from EnderecoEntity e where e.cidade = ?1")
    List<EnderecoEntity> buscarEnderecoPorCidade(String cidade);

    @Query("select e from EnderecoEntity e where e.uf = ?1")
    List<EnderecoEntity> buscarEnderecoPorUF(String uf);
}
