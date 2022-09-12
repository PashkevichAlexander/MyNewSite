package com.mysite.MyNewSite.repository;

import com.mysite.MyNewSite.domian.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Integer> {

}
