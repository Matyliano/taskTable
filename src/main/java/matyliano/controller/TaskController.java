package matyliano.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import matyliano.dto.TaskDTO;
import matyliano.entity.Task;
import matyliano.entity.User;
import matyliano.service.CsvWriterService;
import matyliano.service.TaskService;
import matyliano.util.CsvUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/task")
@AllArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;
    private final CsvWriterService csvWriterService;

    @GetMapping("/getAll")
    public ResponseEntity<Page<Task>> getAllTasks(@PageableDefault(size = 15) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        log.info("Request to get task : {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getOneTask(id));
    }

    @PostMapping("/new")
    public ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        log.info("Request to create task: {}", task);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.save(task));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTaskDetails(@RequestBody TaskDTO task, @PathVariable Long id) {
        log.info("Request to update task: {}", task);
        return ResponseEntity.status(HttpStatus.OK).body(taskService.updateTask(id, task));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTaskById(@PathVariable Long id) {
        log.info("Request to delete task with id : {}", id);
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/addComment")
    public void addCommentToTask(@RequestParam("id") Long id, @RequestBody String note, User authorId) {
        Task task = taskService.getOneTask(id);
        taskService.commentToTask(task, note, authorId);
    }

    @PostMapping("/assignTask/{taskId}/assignUser/{userId}")
    public User assignUser(@PathVariable Long taskId, @PathVariable Long userId) {
        return taskService.assignTaskToUser(taskId, userId);
    }

    @GetMapping("/csv")
    public void getTaskListCsv(@PageableDefault(size = 15) Pageable pageable, HttpServletResponse response) throws IOException {
        Page<Task> tasks = taskService.getAllTasks(pageable);
        csvWriterService.configureCsvWriterAndPrint(response, tasks, CsvUtil.taskListHeader, "tasks.csv");
    }
}
