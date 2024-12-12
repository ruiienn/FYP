package fyp.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> findByMember(Member member);
}
