package com.br.projeto1.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.projeto1.Model.Usuario;
import com.br.projeto1.Model.Papel;
import com.br.projeto1.Repository.UsuarioRepository;
import com.br.projeto1.Repository.PapelRepository;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    PapelRepository pRepository;

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView mv = new ModelAndView("home");
        ArrayList<Papel> papeis = new ArrayList<>();
        papeis = (ArrayList<Papel>) pRepository.findAll();
        mv.addObject("papeis", papeis);
        return mv;
    }

    @PostMapping("/home")
    public String salvar(Usuario usuario, @RequestParam("pps") List<Integer> papelId) {
        ArrayList<Papel> papeis = new ArrayList<>();
        for (Integer id : papelId) {
            papeis.add(pRepository.findById(id).get());
        }
        usuario.setPapeis(papeis);
        repository.save(usuario);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public ModelAndView lista() {
        ModelAndView mv = new ModelAndView("list");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = (ArrayList<Usuario>) repository.findAll();
        mv.addObject("usuarios", usuarios);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") int id) {
        repository.deleteById(id);
        return "redirect:/list";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("home");
        Usuario usuario = new Usuario();
        usuario = repository.findById(id).get();
        mv.addObject("usuario", usuario);

        return mv;

    }

}