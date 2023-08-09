package com.narvasoft.agenda.controller;

import com.narvasoft.agenda.model.Salas;
import com.narvasoft.agenda.repo.SalasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/salas")
@Controller
public class SalasController {
    @Autowired
    private SalasRepository salasRepository;

    @GetMapping
    public String index(Model model) {
        List<Salas> salas = salasRepository.findAll();
        model.addAttribute("salas", salas);
        return "salas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("sala", new Salas());
        return "nuevoSala";
    }

    @PostMapping("/nuevo")
    public String crear(@Validated Salas sala,
                        BindingResult bindingResult,
                        Model model,
                        RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sala", sala);
            return "nuevoSala";
        }
        salasRepository.save(sala);
        attr.addFlashAttribute("msgExito", "Registro creado exitosamente");
        return "redirect:/salas";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Salas sala = salasRepository.getById(id);
        model.addAttribute("sala", sala);
        return "nuevoSala";
    }

    @PostMapping("/{id}/editar")
    public String editar(
            @PathVariable Long id,
            @Validated Salas sala,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attr) {
        Salas salaFromBD = salasRepository.getById(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("sala", sala);
            return "nuevoSala";
        }
        salaFromBD.setFecha(sala.getFecha());
        salaFromBD.setGrado(sala.getGrado());
        salaFromBD.setSeccion(sala.getSeccion());
        salaFromBD.setAsignatura(sala.getAsignatura());
        salaFromBD.setPc1(sala.isPc1());
        salaFromBD.setPc2(sala.isPc2());
        salaFromBD.setPc3(sala.isPc3());
        salaFromBD.setPc4(sala.isPc4());
        salaFromBD.setPc5(sala.isPc5());
        salaFromBD.setPc6(sala.isPc6());
        salaFromBD.setPc7(sala.isPc7());
        salaFromBD.setPc8(sala.isPc8());
        salaFromBD.setPc9(sala.isPc9());
        salaFromBD.setPc10(sala.isPc10());
        salaFromBD.setNovedad(sala.getNovedad());

        salasRepository.save(salaFromBD);

        attr.addFlashAttribute("msgExito", "Registro actualizado exitosamente");
        return "redirect:/salas";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes attr) {
        Salas sala = salasRepository.getById(id);
        salasRepository.delete(sala);
        attr.addFlashAttribute("msgExito", "Registro eliminado exitosamente");
        return "redirect:/salas";
    }
}