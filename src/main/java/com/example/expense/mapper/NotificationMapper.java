package com.example.expense.mapper;

import com.example.expense.dto.NotificationDto;
import com.example.expense.entity.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "category", expression = "java(notification.getExpenseConfig().getCategory().name())")
    NotificationDto toDto(Notification notification);

    List<NotificationDto> toDtoList(List<Notification> notificationEntities);

}
