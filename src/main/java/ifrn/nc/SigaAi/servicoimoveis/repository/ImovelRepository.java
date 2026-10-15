package ifrn.nc.SigaAi.servicoimoveis.repository;

import ifrn.nc.SigaAi.servicoimoveis.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;


public interface ImovelRepository extends JpaRepository<Imovel, Long> {

    List<Imovel> findByEndereco(String endereco);

    List<Imovel> findByValorAluguelLessThan(Double valor);

    List<Imovel> findByEnderecoContainingIgnoreCase(String trecho);

    List<Imovel> findByEnderecoContainingIgnoreCaseAndValorAluguelLessThanEqual(String endereco, Double valor);

}
