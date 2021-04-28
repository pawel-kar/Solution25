package com.example.solution_25.controller;


import com.example.solution_25.model.Task;
import com.example.solution_25.model.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String redirectToHomeSite() {

        return "index";
    }

    @GetMapping("/tasks")
    public String showTasks(Model model, @RequestParam(name = "isDone") boolean isDone) {
        List<Task> tasks;
        if (isDone) {
            tasks = taskRepository.findByIsDoneTrueOrderByDeadline();
        } else {
            tasks = taskRepository.findByIsDoneFalseOrderByDeadline();
        }
        model.addAttribute("taskList", tasks);
        return "tasks";
    }

    @GetMapping("/task/{id}")
    public String showTask(@PathVariable Long id) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setDone(true);
            taskRepository.save(task);
            return "redirect:/tasks?isDone=false";
        } else {
            return "index";
        }
    }

    @GetMapping("/add")
    public String redirectToAddForm(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);
        return "addTask";
    }

    @PostMapping("/addSingleTask")
    public String add(Task task) {
        taskRepository.save(task);
        return "index";
    }
}