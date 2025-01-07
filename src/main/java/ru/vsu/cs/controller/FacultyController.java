package ru.vsu.cs.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.dto.FacultyCreationDto;
import ru.vsu.cs.dto.FacultyDto;
import ru.vsu.cs.dto.SpecialityDto;
import ru.vsu.cs.service.FacultyService;
import ru.vsu.cs.service.SpecialityService;

import java.util.List;

@Controller
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;
    private final SpecialityService specialityService;

    public FacultyController(FacultyService facultyService, SpecialityService specialityService) {
        this.facultyService = facultyService;
        this.specialityService = specialityService;
    }

    @GetMapping({"", "/"})
    public String getAllFaculties(Model model,
                                  @RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "20") int size,
                                  @RequestParam(required = false) String searchValue,
                                  @RequestParam(required = false) String searchColumn
    ) {
        Page<FacultyDto> facultyPage = facultyService.findAll(page, size, searchValue, searchColumn);
        model.addAttribute("faculties", facultyPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", facultyPage.getTotalPages());
        model.addAttribute("totalItems", facultyPage.getTotalElements());
        model.addAttribute("searchValue", searchValue);
        model.addAttribute("searchColumn", searchColumn);
        return "faculties/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("faculty", new FacultyCreationDto());
        return "faculties/creationPage";
    }

    @PostMapping("/create")
    public String createFaculty(@Valid @ModelAttribute("faculty") FacultyCreationDto faculty, BindingResult result) {
        if (result.hasErrors()) {
            return "faculties/creationPage";
        }

        facultyService.save(faculty);

        return "redirect:/faculties";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        FacultyDto original = facultyService.findById(id);
        model.addAttribute("original", original);
        FacultyCreationDto edited = facultyService.toCreationDto(original);
        model.addAttribute("edited", edited);
        return "faculties/editPage";
    }

    @PostMapping("/edit")
    public String editFaculty(Model model, @RequestParam Long id, @Valid @ModelAttribute("edited") FacultyCreationDto faculty, BindingResult result) {
        try {
            FacultyDto original = facultyService.findById(id);
            model.addAttribute("original", original);

            if (result.hasErrors()) {
                return "faculties/editPage";
            }
        } catch (Exception e) {
            return "redirect:/faculties";
        }

        facultyService.update(id, faculty);

        return "redirect:/faculties";
    }

    @GetMapping("/delete")
    public String deleteFaculty(@RequestParam Long id) {
        facultyService.delete(id);
        return "redirect:/faculties";
    }

    @GetMapping("/{id}/specialities")
    @ResponseBody
    public List<SpecialityDto> getSpecialitiesByFacultyId(@PathVariable Long id) {
        return specialityService.findAllByFacultyId(id);
    }
}
