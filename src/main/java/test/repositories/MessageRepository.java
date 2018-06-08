package test.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import test.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {

	Optional<Message> findById(Long id);

}
