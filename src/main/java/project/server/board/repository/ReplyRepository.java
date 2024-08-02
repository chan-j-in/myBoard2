package project.server.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.server.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
}
