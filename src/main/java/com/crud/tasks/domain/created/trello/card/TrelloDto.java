package com.crud.tasks.domain.created.trello.card;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class TrelloDto {
    private int board;
    private int card;
}
