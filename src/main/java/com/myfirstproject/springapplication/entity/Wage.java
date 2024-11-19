package com.myfirstproject.springapplication.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wage")
public class Wage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker; // Reference to the Worker entity

    // Default constructor
    public Wage() {}

    // Parameterized constructor
    public Wage(LocalDate date, Double amount, Worker worker) {
        this.date = date;
        this.amount = amount;
        this.worker = worker;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}
