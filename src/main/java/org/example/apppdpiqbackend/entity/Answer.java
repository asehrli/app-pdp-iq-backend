package org.example.apppdpiqbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(nullable = false, unique = true)
    String text;

    boolean correct;
    @ManyToOne(optional = false)
    Question question;
}