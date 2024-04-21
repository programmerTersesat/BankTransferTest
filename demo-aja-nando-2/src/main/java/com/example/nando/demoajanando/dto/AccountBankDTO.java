package com.example.nando.demoajanando.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBankDTO {
    private Long idSource;
    private Long idDestination;
    private Double transfer;
}
