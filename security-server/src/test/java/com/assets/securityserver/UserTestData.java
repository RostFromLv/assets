package com.assets.securityserver;

import com.assets.commondtos.models.UserDto;
import java.util.Collection;
import java.util.HashSet;

@SuppressWarnings(value = "argument") // For testing cases
public class UserTestData  {
    protected final Long id = 1L;
    protected final Long secondId = 2L;
    protected final Long thirdId = 2L;
    protected final String email = "test@email.com";
    protected final String secondEmail = "Test2@email.com";
    protected final String name = "Dima";
    protected final String secondName = "Oleg";
    protected final String lastName = "Petravchuk";
    protected final String secondLastName = "Koval";
    protected final String password = "firstPass";
    protected final String secondPassword = "secPass";

    protected final UserDto userDto = UserDto.builder()
        .id(id)
        .email(email)
        .password(password)
        .name(name)
        .lastName(lastName)
        .roles(new HashSet<>()).build();


    protected final UserDto secondUserDto = UserDto.builder()
        .id(secondId)
        .email(secondEmail)
        .password(secondPassword)
        .name(secondName)
        .lastName(secondLastName)
        .roles(new HashSet<>()).build();;

    protected Collection<UserDto> getCollectionOfUsersDto() {
      Collection<UserDto> expected = new HashSet<>();
      expected.add(userDto);
      expected.add(secondUserDto);
      return expected;
    }
}
