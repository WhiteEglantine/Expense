package com.example.expense.repository;

import com.example.expense.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    @Query(value = "SELECT * FROM notification JOIN expense_config " +
            "ON notification.expense_config_id = expense_config.id " +
            "WHERE expense_config.user_id = ?1 AND notification.read = FALSE", nativeQuery = true)
    List<Notification> findAllUnreadByUserId(long userId);
}
