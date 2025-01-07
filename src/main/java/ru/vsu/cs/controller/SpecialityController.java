package ru.vsu.cs.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.dto.SpecialityCreationDto;
import ru.vsu.cs.dto.SpecialityDto;
import ru.vsu.cs.dto.StudentDto;
import ru.vsu.cs.service.FacultyService;
import ru.vsu.cs.service.SpecialityService;

@Controller
@RequestMapping("/specialities")
public class SpecialityController {

    private final SpecialityService specialityService;
    private final FacultyService facultyService;

    public SpecialityController(SpecialityService specialityService, FacultyService facultyService) {
        this.specialityService = specialityService;
        this.facultyService = facultyService;
    }

    @GetMapping({"", "/"})
    public String getAllSpecialities(Model model,
                                     @RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "20") int size) {
        Page<SpecialityDto> specialityPage = specialityService.findAll(page, size);
        model.addAttribute("specialities", specialityPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", specialityPage.getTotalPages());
        model.addAttribute("totalItems", specialityPage.getTotalElements());
        return "specialities/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("speciality", new SpecialityCreationDto());
        model.addAttribute("faculties", facultyService.findAll());
        return "specialities/creationPage";
    }

    @PostMapping("/create")
    public String createSpeciality(@Valid @ModelAttribute("speciality") SpecialityCreationDto speciality, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("faculties", facultyService.findAll());
            return "specialities/creationPage";
        }

        specialityService.save(speciality);

        return "redirect:/specialities";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        SpecialityDto original = specialityService.findById(id);
        model.addAttribute("original", original);
        SpecialityCreationDto edited = specialityService.toCreationDto(original);
        model.addAttribute("edited", edited);
        model.addAttribute("faculties", facultyService.findAll());
        return "specialities/editPage";
    }

    @PostMapping("/edit")
    public String editSpeciality(Model model, @RequestParam Long id, @Valid @ModelAttribute("edited") SpecialityCreationDto faculty, BindingResult result) {
        try {
            SpecialityDto original = specialityService.findById(id);
            model.addAttribute("original", original);

            if (result.hasErrors()) {
                model.addAttribute("faculties", facultyService.findAll());
                return "specialities/editPage";
            }

        } catch (Exception e) {
            return "redirect:/specialities";
        }

        specialityService.update(id, faculty);

        return "redirect:/specialities";
    }

    @GetMapping("/delete")
    public String deleteSpeciality(@RequestParam Long id) {
        specialityService.delete(id);
        return "redirect:/specialities";
    }

}
