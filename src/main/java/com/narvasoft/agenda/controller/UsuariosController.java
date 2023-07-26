package com.narvasoft.agenda.controller;

import com.narvasoft.agenda.model.Usuarios;
import com.narvasoft.agenda.repo.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuariosController {
    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {//<--se crea un objeto modelo de tipo usuario
        model.addAttribute("Usuario", new Usuarios());//<--se agrega el objeto usuario al modelo");
        return "nuevo";
    }

    @PostMapping("/nuevo")
    public String crear(Usuarios usuario, RedirectAttributes attr) {
        usuariosRepository.save(usuario);
        attr.addFlashAttribute("msgExito", "Usuario creado exitosamente");
        return "redirect:/";
    }
}