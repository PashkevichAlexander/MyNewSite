package com.mysite.MyNewSite.repository;

import com.mysite.MyNewSite.domian.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);
}
