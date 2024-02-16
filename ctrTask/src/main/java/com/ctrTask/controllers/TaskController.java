package com.ctrTask.controllers;

import com.ctrTask.domain.task.Task;
import com.ctrTask.domain.task.TaskRepositoty;
import com.ctrTask.domain.task.RequestTask;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepositoty repositoty;

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity getAllTasks(){
        var allTasks = repositoty.findAll();
        return ResponseEntity.ok(allTasks);
    }

    @PostMapping("/registerTask")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity registerTask(@RequestBody @Valid RequestTask dadosRequest){
        Task newTask = new Task(dadosRequest);

        if (dadosRequest.name().equals("")) {
            return ResponseEntity.status(500).body("Falta nome");
        }else if (dadosRequest.prioridade().equals("")) {
            return ResponseEntity.status(500).body("Falta prioridade");
        }else{
            repositoty.save(newTask);
            return ResponseEntity.ok("Cadastro ok");
        }
    }

    @PutMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity updateTask(@RequestBody @Valid RequestTask dadosRequest){
        Task task = repositoty.getReferenceById(dadosRequest.id());
        task.setName(dadosRequest.name());
        task.setPrioridade(dadosRequest.prioridade());
        return ResponseEntity.ok(task);
    }
}
