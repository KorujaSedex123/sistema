package br.com.napoleao.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.napoleao.projeto.entity.ReuniaoEntity;

public interface ReuniaoRepository extends JpaRepository<ReuniaoEntity, Long> {

}
