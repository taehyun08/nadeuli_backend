package kr.nadeuli.service.orikkirimanage;

import java.util.List;
import kr.nadeuli.entity.AnsQuestion;
import kr.nadeuli.entity.Member;
import kr.nadeuli.entity.Orikkiri;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnsQuestionRepository extends JpaRepository<AnsQuestion, Long> {

    Page<AnsQuestion> findByOrikkiriAndOriScheMemChatFavNotNull(Orikkiri orikkiri, Pageable pageable);

    Page<AnsQuestion> findByOrikkiriAndOriScheMemChatFavIsNull(Orikkiri orikkiri, Pageable pageable);

    @Query("SELECT DISTINCT m " +
        "FROM Member m " +
        "JOIN OriScheMemChatFav mcf ON m.tag = mcf.member.tag " +
        "JOIN AnsQuestion aq ON mcf.oriScheMemChatFavId = aq.oriScheMemChatFav.oriScheMemChatFavId " +
        "WHERE mcf.orikkiri.orikkiriId = :orikkiriId")
    List<Member> findSignUpByOrikkiriId(@Param("orikkiriId") Long orikkiriId);
}
