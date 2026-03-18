package com.pedro.fincontrol.config;

import com.pedro.fincontrol.entities.Category;
import com.pedro.fincontrol.entities.Transaction;
import com.pedro.fincontrol.entities.User;
import com.pedro.fincontrol.entities.enums.TransactionStatus;
import com.pedro.fincontrol.entities.enums.TransactionType;
import com.pedro.fincontrol.repositories.CategoryRepository;
import com.pedro.fincontrol.repositories.TransactionRepository;
import com.pedro.fincontrol.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {


        // Cria o utilizador principal
        User u1 = new User(null, "Pedro", "pedro@email.com", "senha123");

        // Cria as categorias
        Category cat1 = new Category(null, "Desenvolvimento de Software");
        Category cat2 = new Category(null, "Ganhos Motorista");
        Category cat3 = new Category(null, "Manutenção Gol");
        Category cat4 = new Category(null, "Combustível");

        // Salva primeiro os objetos independentes
        userRepository.saveAll(Arrays.asList(u1));
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));

        // Cria as transações
        Transaction t1 = new Transaction(null, "Sistema Barbearia", 1500.0, Instant.parse("2026-03-10T10:00:00Z"), Instant.parse("2026-03-15T10:00:00Z"), u1, cat1);
        t1.setType(TransactionType.INCOME);
        t1.setStatus(TransactionStatus.PAID);

        Transaction t2 = new Transaction(null, "Corridas da semana", 450.0, Instant.parse("2026-03-15T22:00:00Z"), Instant.parse("2026-03-15T22:00:00Z"), u1, cat2);
        t2.setType(TransactionType.INCOME);
        t2.setStatus(TransactionStatus.PAID);

        Transaction t3 = new Transaction(null, "Abastecimento posto", 150.0, Instant.parse("2026-03-16T08:00:00Z"), Instant.parse("2026-03-16T08:00:00Z"), u1, cat4);
        t3.setType(TransactionType.EXPENSE);
        t3.setStatus(TransactionStatus.PAID);

        Transaction t4 = new Transaction(null, "Troca de óleo", 200.0, Instant.parse("2026-03-20T14:00:00Z"), Instant.parse("2026-03-25T14:00:00Z"), u1, cat3);
        t4.setType(TransactionType.EXPENSE);
        t4.setStatus(TransactionStatus.PENDING); // Pendente para testar as contas projetadas depois

        // Salva as transações
        transactionRepository.saveAll(Arrays.asList(t1, t2, t3, t4));
    }
}
