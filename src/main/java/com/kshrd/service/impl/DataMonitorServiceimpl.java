package com.kshrd.service.impl;

import com.kshrd.entity.Board;
import com.kshrd.repository.DataMonitorRepository;
import com.kshrd.service.DataMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by Teckchun on 10/24/17.
 */
@Service
public class DataMonitorServiceimpl implements DataMonitorService {

    @Autowired
    DataMonitorRepository dataMonitorRepository;

    @Override
    public ArrayList<Board> getBoards(String boardTitle, String startDate, String endDate) {

        return dataMonitorRepository.getBoards(boardTitle,startDate,endDate);
    }
}
