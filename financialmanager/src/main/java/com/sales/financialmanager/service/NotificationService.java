package com.sales.financialmanager.service;

import com.sales.financialmanager.dto.ExpenseDTO;
import com.sales.financialmanager.entity.ProfileEntity;
import com.sales.financialmanager.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final ProfileRepository profileRepository;
    private final EmailService emailService;
    private final ExpenseService expenseService;

    @Value("${financial.manager.frontend.url}")
    private String frontendUrl;


    //rodar diariamente às 9:00 no horário de São Paulo.
    @Scheduled(cron = "0 0 9 * * *", zone = "America/Sao_Paulo")
    public void sendDailyIncomeExpenseReminder() {
        log.info("Job started: sendDailyIncomeExpenseReminder()");
        List<ProfileEntity> profiles = profileRepository.findAll();
        for(ProfileEntity profile : profiles) {
            String body = "Olá " + profile.getFullName() + ",<br><br>"
                    + "Este é um lembrete para adicionar suas receitas e despesas de hoje no Financial Manager.<br><br>"
                    + "<a href="+frontendUrl+" style='display:inline-block;padding:10px 20px;background-color:#4CAF50;color:#fff;text-decoration:none;border-radius:5px;font-weight:bold;'>Financial Manager</a>"
                    + "<br><br>Atenciosamente,<br>Equipe Financial Manager";
            emailService.sendEmail(profile.getEmail(), "Lembrete diário: Adicione suas receitas e despesas", body);
        }
        log.info("Job completed: sendDailyIncomeExpenseReminder()");
    }

    //rodar diariamente às 23:00 no horário de São Paulo.
    @Scheduled(cron = "0 0 23 * * *", zone = "America/Sao_Paulo")
    public void sendDailyExpenseSummary() {
        log.info("Job started: sendDailyExpenseSummary()");
        List<ProfileEntity> profiles = profileRepository.findAll();
        // Garante que a data usada na busca corresponde ao fuso horário do agendamento.
        LocalDate todayInBrazil = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        for (ProfileEntity profile : profiles) {
            List<ExpenseDTO> todaysExpenses = expenseService.getExpensesForUserOnDate(profile.getId(), todayInBrazil);
            if (!todaysExpenses.isEmpty()) {
                StringBuilder table = new StringBuilder();
                table.append("<table style='border-collapse:collapse;width:100%;font-family: Arial, sans-serif;'>");
                table.append("<tr style='background-color:#f2f2f2;'><th style='border:1px solid #ddd;padding:8px;'>Nº</th><th style='border:1px solid #ddd;padding:8px;'>Nome</th><th style='border:1px solid #ddd;padding:8px;'>Valor</th><th style='border:1px solid #ddd;padding:8px;'>Categoria</th></tr>");
                int i = 1;
                for(ExpenseDTO expense : todaysExpenses) {
                    table.append("<tr>");
                    table.append("<td style='border:1px solid #ddd;padding:8px;'>").append(i++).append("</td>");
                    table.append("<td style='border:1px solid #ddd;padding:8px;'>").append(expense.getName()).append("</td>");
                    table.append("<td style='border:1px solid #ddd;padding:8px;'>").append(expense.getAmount()).append("</td>");
                    table.append("<td style='border:1px solid #ddd;padding:8px;'>").append(expense.getCategoryName() != null ? expense.getCategoryName() : "N/A").append("</td>");
                    table.append("</tr>");
                }
                table.append("</table>");
                String body = "Olá "+profile.getFullName()+",<br/><br/>Aqui está um resumo de suas despesas de hoje:<br/><br/>"+table+"<br/><br/>Atenciosamente,<br/>Equipe Financial Manager";
                emailService.sendEmail(profile.getEmail(), "Seu resumo diário de despesas", body);
            }
        }
        log.info("Job completed: sendDailyExpenseSummary()");
    }
}