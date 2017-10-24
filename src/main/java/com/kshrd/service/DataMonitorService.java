package com.kshrd.service;

import com.kshrd.entity.Board;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Teckchun on 10/24/17.
 */

public interface DataMonitorService {

    // TODO: 10/24/17 get All board by title
    public ArrayList<Board> getBoards(String boardTitle, String startDate, String endDate);

    // TODO: 10/24/17 get content advanced search
    public List<Map<String,Object>> getBoardContent(
                                                    String productName,
                                                    String contentLike,
                                                    String excludeOne,
                                                    String excludeTwo,
                                                    String excludeThree);

}
