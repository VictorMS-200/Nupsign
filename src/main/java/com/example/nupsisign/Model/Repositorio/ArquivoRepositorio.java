package com.example.nupsisign.Model.Repositorio;

import com.example.nupsisign.Model.Dbset.Arquivos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepositorio extends JpaRepository<Arquivos, String> {
    Arquivos findByIdArquivo(String idArquivo);
}
