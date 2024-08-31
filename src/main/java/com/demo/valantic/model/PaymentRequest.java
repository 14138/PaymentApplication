package com.demo.valantic.model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaymentRequest {
    @NotNull
    @Min(4)
    private Long amount;

    @Email
    private String email;

   @NotBlank
   @Size(min = 5, max = 200)
   private String productName;
   
    @NotBlank
    private String currency;
}