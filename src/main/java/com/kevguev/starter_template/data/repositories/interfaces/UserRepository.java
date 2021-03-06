package com.kevguev.starter_template.data.repositories.interfaces;

import com.kevguev.starter_template.data.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/*
    commands on 2 different terminals
    /usr/local/opt/mongodb-community@3.6/bin/mongod
    mongo
 */
public interface UserRepository extends MongoRepository<UserModel, String>, UserRepositoryCustom {
    List<UserModel> findByLastName(String lastName);
}
