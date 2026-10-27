package com.underwearstore.orderservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "refresh_tokens")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RefreshToken {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // ID генерируется до Insert в БД. Можно использовать пакетную вставку (batching). Можно прикрутить контроль над генерацией ID
	Long id;

	@NotNull
	String refreshToken; // refreshToken

	@NotNull
	@ManyToOne
	@JoinColumn(name = "user_id")
	User user;

	@NotNull
	Instant expiryDate; // Дата истечения срока действия
}
