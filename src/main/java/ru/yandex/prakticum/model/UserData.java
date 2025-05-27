package ru.yandex.prakticum.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Модель данных пользователя
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private String name;
    private String email;
    private String password;
}