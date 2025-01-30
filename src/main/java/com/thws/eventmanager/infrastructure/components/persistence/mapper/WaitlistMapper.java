package com.thws.eventmanager.infrastructure.components.persistence.mapper;

import com.thws.eventmanager.domain.models.Event;
import com.thws.eventmanager.domain.models.User;
import com.thws.eventmanager.domain.models.Waitlist;
import com.thws.eventmanager.infrastructure.components.persistence.entities.EventEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.UserEntity;
import com.thws.eventmanager.infrastructure.components.persistence.entities.WaitlistEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WaitlistMapper extends Mapper<Waitlist, WaitlistEntity>{
//    private final EventRetrievalOutputPort eventRepo;
    private final EventMapper eventMapper = new EventMapper();
    private final UserMapper userMapper = new UserMapper();


    @Override
    public Waitlist toModel(WaitlistEntity entity) {
        Waitlist waitlist = new Waitlist();

        //maps eventEntity to Event model
        waitlist.setEvent(eventMapper.toModel(entity.getEvent()));

        //maps the list of usersEntity to a model in domain
        List<User> users = entity.getUsers().stream()
                .map(userMapper::toModel).toList();
        waitlist.setUsers(users);

        return waitlist;
    }

    @Override
    public WaitlistEntity toEntity(Waitlist model) {
        WaitlistEntity entity = new WaitlistEntity();
        entity.setEvent(eventMapper.toEntity(model.getEvent()));
        List<UserEntity> userEntities = model.getUsers().stream()
                        .map(userMapper::toEntity).toList();
        entity.setUserEntities(userEntities);
        return entity;
    }
}
