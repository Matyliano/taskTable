package matyliano.service;

import java.util.List;
import matyliano.entity.Comment;
import matyliano.repository.CommentRepository;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    public Comment getOneComment(Long id){
        return commentRepository.getOne(id);
    }

    public void deleteById(Long id){
        commentRepository.deleteById(id);
    }

    public List<Comment> getCommentsByTask(){
        return commentRepository.getAllByTask();
    }
}
