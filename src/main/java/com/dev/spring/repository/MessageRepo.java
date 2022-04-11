package com.dev.spring.repository;

import com.dev.spring.entity.Message;
import com.dev.spring.entity.User;
import com.dev.spring.entity.dto.MessageDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends CrudRepository<Message, Long> {

    @Query(value = "select new MessageDto( " +
            "m, " +
            "count(ml), " +
            "sum(case when ml = :user then 1 else 0 end) > 0 ) " +
            "from Message m left join m.likes ml " +
            "group by m", nativeQuery = true)
    Page<MessageDto> findAll(Pageable pageable, @Param("user") User user);

    @Query(value = "select new MessageDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "where m.tag = :tag " +
            "group by m", nativeQuery = true)
    Page<MessageDto> findByTag(@Param("tag") String tag, Pageable pageable, @Param("user") User user);

    @Query(value = "select new MessageDto(" +
            "   m, " +
            "   count(ml), " +
            "   sum(case when ml = :user then 1 else 0 end) > 0" +
            ") " +
            "from Message m left join m.likes ml " +
            "where m.author = :author " +
            "group by m", nativeQuery = true)
    Page<MessageDto> findByUser(Pageable pageable, @Param("author") User author, @Param("user") User user);
}
