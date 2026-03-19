package com.pedro.fincontrol.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_recurring_bill")
public class RecurringBill implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer totalInstallments;
    private Integer currentInstallment;
    private Double installmentValue;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant startDate;

    @ManyToOne
    @JoinColumn(name =  "user_id")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "recurringBill")
    private List<Transaction> transactions = new ArrayList<>();

    public RecurringBill() {
    }

    public RecurringBill(Long id, String title, Integer totalInstallments, Integer currentInstallment, Double installmentValue, Instant startDate, User user) {
        this.id = id;
        this.title = title;
        this.totalInstallments = totalInstallments;
        this.installmentValue = installmentValue;
        this.currentInstallment = currentInstallment;
        this.startDate = startDate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTotalInstallments() {
        return totalInstallments;
    }

    public void setTotalInstallments(Integer totalInstallments) {
        this.totalInstallments = totalInstallments;
    }

    public Integer getCurrentInstallment() {
        return currentInstallment;
    }

    public void setCurrentInstallment(Integer currentInstallment) {
        this.currentInstallment = currentInstallment;
    }

    public Double getInstallmentValue() {
        return installmentValue;
    }

    public void setInstallmentValue(Double installmentValue) {
        this.installmentValue = installmentValue;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RecurringBill that = (RecurringBill) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
