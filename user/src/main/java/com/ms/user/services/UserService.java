package com.ms.user.services;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProducer;
import com.ms.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProducer userProducer;

    @Transactional
    public UserModel save(UserModel userModel){

        userModel = userRepository.save(userModel); // Vai salvar no BD
        userProducer.publishMessageEmail(userModel); // acionar o producer

        return userModel; // retornar pro cliente
    }
}
