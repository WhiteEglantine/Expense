package com.example.expense.service;

import com.example.expense.dto.NotificationDto;
import com.example.expense.entity.Notification;
import com.example.expense.mapper.NotificationMapper;
import com.example.expense.repository.NotificationRepository;
import com.example.expense.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public List<NotificationDto> getAllUnreadNotifications() {
        long userId = SecurityContextUtil.getCurrentUserId();
        List<Notification> unreadNotifications = notificationRepository.findAllUnreadByUserId(userId);
        List<NotificationDto> notificationDtos = notificationMapper.toDtoList(unreadNotifications);
        unreadNotifications.forEach(notification -> {
            notification.setRead(true);
            notificationRepository.save(notification);
        });
        return notificationDtos;
    }

    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

}
