package com.example.application.controls;

import com.example.application.entities.User;
import com.example.application.repositories.StellenanzeigeRepository;
import com.example.application.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class UserControl {

    @Autowired
    private UserRepository userRep;

    @Autowired
    private StellenanzeigeRepository stellenanzeigeRep;

    public void deleteUser(int userid){
        User user = userRep.findUserByUserid(userid);
        stellenanzeigeRep.deleteStellenanzeigesByUserid(user);
        userRep.deleteUserByUserid(userid);

    }
}
