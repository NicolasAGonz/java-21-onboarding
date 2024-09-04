package com.dev.java.MSCuentas.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class NewAccountWithCardsDTO {
    int numcue;
    String cardType;
    String cardCurrency;
}
