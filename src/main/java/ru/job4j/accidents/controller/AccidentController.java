package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.exception.InvalidParamsException;
import ru.job4j.accidents.exception.ParamErrorResponse;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.jpa.AccidentServiceJpa;
import ru.job4j.accidents.service.jpa.AccidentTypeServiceJpa;
import ru.job4j.accidents.service.jpa.RuleServiceJpa;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {

    private final AccidentServiceJpa accidentService;
    private final AccidentTypeServiceJpa accidentTypeService;
    private final RuleServiceJpa ruleService;

    @GetMapping("/formCreate")
    public String viewCreateAccident(Model model) {
        model.addAttribute("accident", new Accident());
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "/accidents/formCreate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Valid Accident accident,  Errors errors, @RequestParam("type.id") int typeId,
                       @RequestParam("rIds") List<Integer> rIds) {
        if (errors.hasErrors()) {
            return "/accidents/formCreate";
        }
        if (accidentTypeService.findById(typeId).isEmpty()) {
            return "redirect:/accidents/fail";
        }
        accident.setType(accidentTypeService.findById(typeId).get());
        accident.setRules(ruleService.findByIds(rIds));
        accidentService.add(accident);
        return "redirect:/";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", new Accident());
        Optional<Accident> accident = accidentService.findById(id);
        if (accident.isEmpty()) {
            return "redirect:/accidents/fail";
        }
        model.addAttribute("accident", accident.get());
        model.addAttribute("types", accidentTypeService.findAll());
        model.addAttribute("rules", ruleService.findAll());
        return "accidents/formUpdate";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute Accident accident, Errors errors, @RequestParam("type.id") int typeId,
                         @RequestParam("rIds") List<Integer> rIds) {
        if (accidentTypeService.findById(typeId).isEmpty()) {
            return "redirect:/accidents/fail";
        }
        if (errors.hasErrors()) {
            return "/accidents/formUpdate";
        }
        accident.setType(accidentTypeService.findById(typeId).get());
        accident.setRules(ruleService.findByIds(rIds));
        accidentService.update(accident);
        return "redirect:/";
    }

    @GetMapping("/fail")
    public String failPage() {
        return "shared/fail";
    }

    @ExceptionHandler
    private ResponseEntity<ParamErrorResponse> handlerException(InvalidParamsException e) {
        ParamErrorResponse response = new ParamErrorResponse("Переданы неверные парметры", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}

