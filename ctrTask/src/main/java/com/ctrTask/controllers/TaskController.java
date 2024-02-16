package com.ctrTask.controllers;

import com.ctrTask.models.task.ResponseTask;
import com.ctrTask.models.task.Task;
import com.ctrTask.repositories.TaskRepositoty;
import com.ctrTask.dtos.RequestTask;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepositoty repositoty;

    @Autowired
    private ResponseTask responseTask;

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity getAllTasks(){
        var allTasks = repositoty.findAll();
        return ResponseEntity.ok(allTasks);
    }

    @PostMapping("/registerTask")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<?> registerTask(@RequestBody @Valid RequestTask dadosRequest){

        Task newTask = new Task(dadosRequest);

        if (dadosRequest.name().equals("")) {
            responseTask.setMensagem("Informar o nome da tarefa!");
            return new ResponseEntity<ResponseTask>(responseTask, HttpStatus.BAD_REQUEST);
        }else if (dadosRequest.prioridade().equals("")) {
            responseTask.setMensagem("Informar a prioridade!");
            return new ResponseEntity<ResponseTask>(responseTask, HttpStatus.BAD_REQUEST);
        }else{
            repositoty.save(newTask);
            return ResponseEntity.ok(newTask);
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
