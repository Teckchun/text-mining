package com.kshrd.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.Timestamp;
import java.sql.Date;

/**
 * Created by Teckchun on 10/24/17.
 */
public class Board {



    @JsonProperty("board_view")
    private int boardView;

    @JsonProperty("board_title")
    private String boardTitle;

    @JsonProperty("board_recommand")
    private String boardRecommand;

    @JsonProperty("insert_date")
    private Date insertDate;

    public int getBoardView() {
        return boardView;
    }

    public void setBoardView(int boardView) {
        this.boardView = boardView;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardRecommand() {
        return boardRecommand;
    }

    public void setBoardRecommand(String boardRecommand) {
        this.boardRecommand = boardRecommand;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    @Override
    public String toString() {
        return "Board{" +
                "boardView=" + boardView +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardRecommand='" + boardRecommand + '\'' +
                ", insertDate=" + insertDate +
                '}';
    }
}
