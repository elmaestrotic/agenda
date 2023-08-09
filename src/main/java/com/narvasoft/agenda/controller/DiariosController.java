package com.narvasoft.agenda.controller;

import com.narvasoft.agenda.model.Diarios;

import com.narvasoft.agenda.repo.DiariosRepository;

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

@RequestMapping("/diarios")
@Controller
public class DiariosController {
    @Autowired
    private DiariosRepository diariosRepository;

    @GetMapping
    public String index(Model model) {
        List<Diarios> diarios = diariosRepository.findAll();
        model.addAttribute("diarios", diarios);
        return "diarios";
    }

    @GetMapping("/nuevo")//<--api para crear un nuevo diario
    public String nuevo(Model model) {//<--se crea un objeto modelo de tipo diario
        model.addAttribute("diario", new Diarios());//<--se agrega el objeto diario al modelo");
        return "nuevoDiario";//<--se redirecciona a la vista nuevoDiario.html
    }

    @PostMapping("/nuevo")//<--api para guardar un nuevo diario
    public String crear(@Validated Diarios diario,
                        BindingResult bindingResult,
                        Model model,
                        RedirectAttributes attr) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("diario", diario);
            return "nuevoDiario";
        }
        diariosRepository.save(diario);
        attr.addFlashAttribute("msgExito", "diario creado exitosamente");
        return "redirect:/diarios";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        Diarios diario = diariosRepository.getById(id);
        model.addAttribute("diario", diario);
        return "nuevoDiario";//al momento de editar se redirecciona a la vista nuevo.html
    }

    @PostMapping("/{id}/editar")
    public String editar(
            @PathVariable Long id,
            @Validated Diarios diario,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes attr) {
        Diarios diarioFromBD = diariosRepository.getById(id);

        if (bindingResult.hasErrors()) {
            model.addAttribute("diario", diario);
            return "nuevoDiario";
        }
        diarioFromBD.setFecha(diario.getFecha());
        diarioFromBD.setGrado(diario.getGrado());
        diarioFromBD.setSeccion(diario.getSeccion());
        diarioFromBD.setAsignatura(diario.getAsignatura());
        diarioFromBD.setDescripcion(diario.getDescripcion());
        diarioFromBD.setReflexion(diario.getReflexion());
        diariosRepository.save(diarioFromBD);


        attr.addFlashAttribute("msgExito", "diario actualizado exitosamente");
        return "redirect:/diarios";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes attr) {
        Diarios diario = diariosRepository.getById(id);
        diariosRepository.delete(diario);
        attr.addFlashAttribute("msgExito", "diario eliminado exitosamente");
        return "redirect:/diarios";
    }

}