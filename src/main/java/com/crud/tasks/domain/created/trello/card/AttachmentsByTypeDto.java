package com.crud.tasks.domain.created.trello.card;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AttachmentsByTypeDto {

    @JsonProperty("trello")
    private TrelloDto trelloDto;
}
