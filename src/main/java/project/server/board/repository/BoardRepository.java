package project.server.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.server.board.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

}
