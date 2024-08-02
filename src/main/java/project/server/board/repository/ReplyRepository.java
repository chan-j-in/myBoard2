package project.server.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.server.board.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
