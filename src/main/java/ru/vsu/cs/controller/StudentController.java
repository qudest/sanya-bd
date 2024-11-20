package ru.vsu.cs.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.dto.StudentCreationDto;
import ru.vsu.cs.dto.StudentDto;
import ru.vsu.cs.service.FacultyService;
import ru.vsu.cs.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final FacultyService facultyService;

    public StudentController(StudentService studentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.facultyService = facultyService;
    }

    @GetMapping({"", "/"})
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        model.addAttribute("student", new StudentCreationDto());
        model.addAttribute("faculties", facultyService.findAll());
        return "students/creationPage";
    }

    @PostMapping("/create")
    public String createStudent(@Valid @ModelAttribute("student") StudentCreationDto student, BindingResult result, Model model) {
        if (studentService.existsByRecordBookNumber(student.getRecordBookNumber())) {
            result.rejectValue("recordBookNumber", "recordBookNumber", "Record book number already exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("faculties", facultyService.findAll());
            return "students/creationPage";
        }

        studentService.save(student);

        return "redirect:/students";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam Long id) {
        StudentDto original = studentService.findById(id);
        model.addAttribute("original", original);
        StudentCreationDto edited = studentService.toCreationDto(original);
        model.addAttribute("edited", edited);
        model.addAttribute("faculties", facultyService.findAll());
        return "students/editPage";
    }

    @PostMapping("/edit")
    public String editStudent(Model model, @RequestParam Long id, @Valid @ModelAttribute("edited") StudentCreationDto student, BindingResult result) {
        try {
            StudentDto original = studentService.findById(id);
            model.addAttribute("original", original);
            model.addAttribute("faculties", facultyService.findAll());

            if (!student.getRecordBookNumber().equals(original.getRecordBookNumber()) && studentService.existsByRecordBookNumber(student.getRecordBookNumber())) {
                result.rejectValue("recordBookNumber", "recordBookNumber", "Record book number already exists");
            }

            if (result.hasErrors()) {
                return "students/editPage";
            }

        } catch (Exception e) {
            return "redirect:/students";
        }

        studentService.update(id, student);

        return "redirect:/students";

    }

    @GetMapping("/delete")
    public String deleteStudent(@RequestParam Long id) {
        studentService.delete(id);
        return "redirect:/students";
    }

}
