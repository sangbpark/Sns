package com.sns.user.Enitity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@Entity
public class User {
	private int id;
	private String lognId;
	private String password;
	private String name;
	private String nickName;
	private String email;
	private String imagePath;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
