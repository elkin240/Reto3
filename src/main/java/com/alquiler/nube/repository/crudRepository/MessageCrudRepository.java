package com.alquiler.nube.repository.crudRepository;

import com.alquiler.nube.entities.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageCrudRepository extends CrudRepository<Message,Integer> {
}
