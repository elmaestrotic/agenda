package com.narvasoft.agenda.controller;

import com.narvasoft.agenda.model.Usuarios;
import com.narvasoft.agenda.repo.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UsuariosController {
    @Autowired
    private UsuariosRepository usuariosRepository;

    @GetMapping("")
    public String index(Model model) {
        List<Usuarios> usuarios = usuariosRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {//<--se crea un objeto modelo de tipo usuario
        model.addAttribute("usuario", new Usuarios());//<--se agrega el objeto usuario al modelo");
        return "nuevo";
    }

    @PostMapping("/nuevo")
    public String crear(@Validated Usuarios usuario,
                        BindingResult bindingResult,
                        Model model,
                        RedirectAttributes attr) {
       if (bindingResult.hasErrors()) {
           model.addAttribute("usuario", usuario);
            return "nuevo";
        }
        usuariosRepository.save(usuario);
        attr.addFlashAttribute("msgExito", "Usuario creado exitosamente");
        return "redirect:/";
    }
}