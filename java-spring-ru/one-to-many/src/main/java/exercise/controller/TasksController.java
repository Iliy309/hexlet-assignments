package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.model.Task;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "tasks")
public class TasksController {
    // BEGIN

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TaskMapper taskMapper;

    @GetMapping
    public List<TaskDTO> getAll(){
        var tasks = taskRepository.findAll();
        return tasks.stream()
                .map(taskMapper::map)
                .toList();
    }

    @GetMapping(path = "{id}")
    public TaskDTO getById(@PathVariable Long id){
       var tasks =  taskRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Tasks with id " + id + " not found"));
       return taskMapper.map(tasks);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO create(@Valid @RequestBody TaskCreateDTO taskCreateDTO){
        var task = taskMapper.map(taskCreateDTO);
        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @PutMapping(path = "{id}")
    public TaskDTO update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO taskUpdateDTO){
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tasks with id " + id + " not found"));
        var user = userRepository.findById(taskUpdateDTO.getAssigneeId())
                .orElseThrow(() -> new RuntimeException("Users with id " + id + " not found"));

        task.setAssignee(user);
        task.setTitle(taskUpdateDTO.getTitle());
        task.setDescription(taskUpdateDTO.getDescription());

        taskRepository.save(task);
        return taskMapper.map(task);
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        taskRepository.deleteById(id);
    }






    // END
}
