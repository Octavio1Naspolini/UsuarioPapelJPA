package com.br.projeto1.Componentes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.br.projeto1.Model.Papel;
import com.br.projeto1.Repository.PapelRepository;

@Component
public class CarregaDados implements CommandLineRunner {

    @Autowired
    PapelRepository pRepository;

    @Override
    public void run(String... args) throws Exception {
        String[] papeis = { "ADMIN", "NORMAL", "CONSULTA" };

        for (String papelString : papeis) {
            Papel papel = pRepository.findByNome(papelString);
            if (papel == null) {
                papel = new Papel(papelString);
                pRepository.save(papel);
            }
        }
    }

}
