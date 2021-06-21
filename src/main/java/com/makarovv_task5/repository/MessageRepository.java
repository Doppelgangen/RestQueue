package com.makarovv_task5.repository;

import com.makarovv_task5.model.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, Long> {
    Optional<Message> findFirstByIdNotNullOrderByIdAsc();

    Optional<Message> findFirstByIdNotNullOrderByIdDesc();
}
