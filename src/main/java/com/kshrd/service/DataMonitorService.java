package com.kshrd.service;

import com.kshrd.entity.Board;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Teckchun on 10/24/17.
 */

public interface DataMonitorService {

    // TODO: 10/24/17 get All board by title
    public ArrayList<Board> getBoards(String boardTitle, String startDate, String endDate);
    // TODO: 10/24/17 get All board by title and type
    public ArrayList<Board> getBoardsByType(String boardTitle, String startDate, String endDate, String type);


}
