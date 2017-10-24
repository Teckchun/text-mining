package com.kshrd.controller.rest;

import com.kshrd.Utility.*;
import com.kshrd.entity.Board;
import com.kshrd.service.DataMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Teckchun on 10/24/17.
 */

@RestController
@RequestMapping("/rest/data-monitoring")
public class DataMonitorRestController {
    @Autowired
    DataMonitorService dataMonitorService;

    @RequestMapping(value = "/getBaords/query", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<ResponseList<Board>> getBaords(
            @RequestParam Map<String, String> queryParameters) {
        System.out.println("param "+queryParameters.get("board_title"));

        ResponseList<Board> list = new ResponseList<>();
        HttpStatus status = HttpStatus.OK;

        try {
            ArrayList<Board> boards = dataMonitorService.getBoards(
                    queryParameters.get("board_title"),
                    queryParameters.get("start_date"),
                    queryParameters.get("end_date"));
            if (!boards.isEmpty()) {
                list = new ResponseList.ResponseListSuccess<>(
                        HttpMessage.success(Table.BOARD + "(s)", Transaction.Success.RETRIEVE), true, boards,
                        null);
            } else {
                list = new ResponseList.ResponseListFailure<>(
                        HttpMessage.fail(Table.BOARD + "(s)", Transaction.Fail.RETRIEVE), false,
                        ResponseHttpStatus.RECORD_NOT_FOUNT);
            }
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            list = new ResponseList.ResponseListFailure<>(HttpMessage.INTERNAL_SERVER_ERROR, false,
                    ResponseHttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        System.out.println("DATA SIZE "+list.getData().size());
        return new ResponseEntity<ResponseList<Board>>(list, status);
    }




}
