package com.myfirstproject.springapplication.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name="daily_wages")
    public class DailyWage {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private DailyWageWorker worker;

        @Column(name = "wage_amount", nullable = false)
        private Double wageAmount;

        @Column(name = "date", nullable = false)
        private LocalDate date;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public DailyWageWorker getWorker() {
            return worker;
        }

        public void setWorker(DailyWageWorker worker) {
            this.worker = worker;
        }

        public Double getWageAmount() {
            return wageAmount;
        }

        public void setWageAmount(Double wageAmount) {
            this.wageAmount = wageAmount;
        }

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }
}
