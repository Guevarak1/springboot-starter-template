package com.kevguev.starter_template.data.repositories;

import com.kevguev.starter_template.data.models.UserModel;
import com.kevguev.starter_template.data.repositories.interfaces.UserRepositoryCustom;
import com.kevguev.starter_template.exceptionHandler.UserNotFoundException;
import com.kevguev.starter_template.services.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

//https://www.baeldung.com/spring-data-mongodb-tutorial
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User updateUser(String id, User user) {
        Query findUserByIdQuery = Query.query(Criteria.where("_id").is(id));

        UserModel userModel = mongoTemplate.findOne(findUserByIdQuery, UserModel.class);
        if (userModel == null)
            throw new UserNotFoundException("User was not found with Id: " + id);

        userModel.setFirstName(user.getFirstName());
        userModel.setLastName(user.getLastName());

        return new User(mongoTemplate.save(userModel));
    }
}
