package com.kshrd.controller.rest;

import com.kshrd.Utility.*;
import com.kshrd.entity.Board;
import com.kshrd.repository.DataMonitorRepository;
import com.kshrd.service.DataMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Teckchun on 10/24/17.
 */

@RestController
@RequestMapping("/rest/data-monitoring")
public class DataMonitorRestController {
    @Autowired
    DataMonitorService dataMonitorService;

    @RequestMapping(value = "/getBoards", method = RequestMethod.GET, headers = "Accept=application/json")
    public ResponseEntity<ResponseList<Board>> getBaords(
            @RequestParam Map<String, String> queryParameters) {
        System.out.println("param "+queryParameters.get("board_title"));
        System.out.println("param "+queryParameters.get("start_date"));
        System.out.println("param "+queryParameters.get("end_date"));

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


    @GetMapping("/getBoardContents")
    public ResponseEntity<ResponseList<Map<String,Object>>> getBoardContent(
                                                    @RequestParam(value="product_name" ,required = false) String productName,
                                                    @RequestParam(value="content_like" ,required = false) String contentLike,
                                                    @RequestParam(value="exclude_one" ,required = false) String exclude_one,
                                                                            @RequestParam(value="exclude_two" ,required = false) String exclude_two,
                                                    @RequestParam(value="exclude_three" ,required = false) String exclude_three){

        System.out.println(productName+"||"+contentLike+"||"+exclude_one+"||"+exclude_two+"||"+exclude_three);
        ResponseList<Map<String,Object>> list = new ResponseList<>();
        HttpStatus status = HttpStatus.OK;
        try {

            List<Map<String, Object>> map = dataMonitorService.getBoardContent(productName, contentLike, exclude_one,exclude_two,exclude_three);

            if (!map.isEmpty()) {
                list = new ResponseList.ResponseListSuccess<>(
                        HttpMessage.success(Table.BOARD + "(s)", Transaction.Success.RETRIEVE), true, map,
                        null);
            } else {
                list = new ResponseList.ResponseListFailure<>(
                        HttpMessage.fail(Table.BOARD + "(s)", Transaction.Fail.RETRIEVE), false,
                        ResponseHttpStatus.RECORD_NOT_FOUNT);
            }

        }catch(Exception e){
            e.printStackTrace();
        }


        System.out.println("COUNT => "+list.getData().size());
        return new ResponseEntity<ResponseList<Map<String,Object>>>(list, status);
    }

}
