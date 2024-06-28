package com.example.expense.scheduler;

import com.example.expense.dto.ExpenseConfigDto;
import com.example.expense.entity.ExpenseConfig;
import com.example.expense.entity.Notification;
import com.example.expense.service.ExpenseConfigService;
import com.example.expense.service.ExpenseService;
import com.example.expense.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class NotificationScheduler {

    private final ExpenseConfigService expenseConfigService;
    private final ExpenseService expenseService;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 0 9 * * *") // every day at 09:00 AM
    public void dailyNotificationGenerator() {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();
        List<ExpenseConfigDto> activeConfigs = expenseConfigService.getAllActiveExpenseConfigs();
        activeConfigs.forEach(expenseConfigDto -> {
            float totalExpense = expenseService.calculateTotalExpense(
                    expenseConfigDto.getUserId(), expenseConfigDto.getCategory(), currentMonth, currentYear);
            if (totalExpense > expenseConfigDto.getLimit()) {
                ExpenseConfig expenseConfig = expenseConfigService.getExpenseConfigEntity(expenseConfigDto.getId());
                Notification notification = new Notification(LocalDate.now(), expenseConfig, false);
                notificationService.save(notification);
            }
        });
    }
}
