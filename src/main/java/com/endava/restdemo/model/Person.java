package com.endava.restdemo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

  @NotNull
  @Size(min = 3, max = 30)
  private String username;

  @NotNull
  @Size(min = 3, max = 30)
  private String firstName;

  @NotNull
  @Size(min = 3, max = 30)
  private String lastName;

  @NotNull
  @Size(min = 4, max = 50)
  private String password;

  @NotNull
  @Size(min = 4, max = 50)
  private String newPassword;

  @NotNull
  private LocalDateTime hiringDate;

  protected Long id;

  @NotNull
  protected LocalDateTime createdAt;

  @NotNull
  protected LocalDateTime modifiedAt;


  public Person() {
    createdAt = LocalDateTime.now();
    modifiedAt = LocalDateTime.now();
  }


}
