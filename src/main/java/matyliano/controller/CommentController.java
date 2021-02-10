package matyliano.controller;

import java.util.Collection;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import matyliano.entity.Comment;
import matyliano.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/comment")
@Slf4j
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Comment>> getAllComments() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        log.info("Request to get comment : {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getOneComment(id));
    }

    @GetMapping("/task")
    public ResponseEntity<List<Comment>> getCommentByTask() {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByTask());
    }
}
