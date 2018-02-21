package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloList> trelloLists1 = Arrays.asList(
                new TrelloList("1", "name1", true),
                new TrelloList("2", "name2", true));
        List<TrelloList> trelloLists2 = Arrays.asList(
                new TrelloList("3", "name3", true),
                new TrelloList("4", "name4", true));
        List<TrelloListDto> trelloListsDto1 = Arrays.asList(
                new TrelloListDto("name1", "1", true),
                new TrelloListDto("name2", "2", true));
        List<TrelloListDto> trelloListsDto2 = Arrays.asList(
                new TrelloListDto("name3", "3", true),
                new TrelloListDto("name4", "4", true));
        List<TrelloBoardDto> trelloBoardsDto = Arrays.asList(
                new TrelloBoardDto("name1", "1", trelloListsDto1),
                new TrelloBoardDto("name2", "2", trelloListsDto2));
        List<TrelloBoard> expectedTrelloBoards = Arrays.asList(
                new TrelloBoard("1", "name1", trelloLists1),
                new TrelloBoard("2", "name2", trelloLists2));

        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardsDto);
        //Then
        assertEquals(expectedTrelloBoards, trelloBoards);
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloLists1 = Arrays.asList(
                new TrelloList("1", "name1", true),
                new TrelloList("2", "name2", true));
        List<TrelloList> trelloLists2 = Arrays.asList(
                new TrelloList("3", "name3", true),
                new TrelloList("4", "name4", true));
        List<TrelloListDto> trelloListsDto1 = Arrays.asList(
                new TrelloListDto("name1", "1", true),
                new TrelloListDto("name2", "2", true));
        List<TrelloListDto> trelloListsDto2 = Arrays.asList(
                new TrelloListDto("name3", "3", true),
                new TrelloListDto("name4", "4", true));
        List<TrelloBoard> trelloBoards = Arrays.asList(
                new TrelloBoard("1", "name1", trelloLists1),
                new TrelloBoard("2", "name2", trelloLists2));
        List<TrelloBoardDto> expectedTrelloBoardsDto = Arrays.asList(
                new TrelloBoardDto("name1", "1", trelloListsDto1),
                new TrelloBoardDto("name2", "2", trelloListsDto2));
        //When
        List<TrelloBoardDto> trelloBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(expectedTrelloBoardsDto, trelloBoardsDto);
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> trelloListsDto = Arrays.asList(
                new TrelloListDto("name1", "1", true),
                new TrelloListDto("name2", "2", true),
                new TrelloListDto("name3", "3", true));
        List<TrelloList> expectedTrelloLists = Arrays.asList(
                new TrelloList("1", "name1", true),
                new TrelloList("2", "name2", true),
                new TrelloList("3", "name3", true));
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListsDto);
        //Then
        assertEquals(expectedTrelloLists, trelloLists);
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> trelloLists = Arrays.asList(
                new TrelloList("1", "name1", true),
                new TrelloList("2", "name2", true),
                new TrelloList("3", "name3", true));
        List<TrelloListDto> expectedTrelloListsDto = Arrays.asList(
                new TrelloListDto("name1", "1", true),
                new TrelloListDto("name2", "2", true),
                new TrelloListDto("name3", "3", true));
        //When
        List<TrelloListDto> trelloListsDto = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(expectedTrelloListsDto, trelloListsDto);
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("name1", "description1", "pos1", "1");
        TrelloCardDto expectedTrelloCardDto =
                new TrelloCardDto("name1", "description1", "pos1", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals(expectedTrelloCardDto, trelloCardDto);
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name1", "description1", "pos1", "1");
        TrelloCard expectedTrelloCard =
                new TrelloCard("name1", "description1", "pos1", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals(expectedTrelloCard, trelloCard);
    }
}