package com.aplication.helloevents_app.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String location;

    private String category;

    private LocalDate eventDate;

    private LocalDate createdAt = LocalDate.now();
}
