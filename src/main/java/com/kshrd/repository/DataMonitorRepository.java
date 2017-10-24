package com.kshrd.repository;

import com.kshrd.entity.Board;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Teckchun on 10/24/17.
 */

@Repository
public interface DataMonitorRepository {

    final String GET_BOARDS = "" +
            "select board_title,board_view,board_recommand,insert_date,'ppomppu' as type from ppomppu_list a" +
            " where a.board_title " +
            " like concat('%',#{board_title},'%')  " +
            " and (a.insert_date >=#{start_date} and a.insert_date <=#{end_date}) " +
            " union " +
            " select board_title,board_view,board_recommand,insert_date,'dcinside' as type from dcinside_list b" +
            " where b.board_title " +
            " like concat('%',#{board_title},'%') and  (b.insert_date >= #{start_date} and b.insert_date <=#{end_date})" +
            " union " +
            " select board_title,board_view,board_recommand,insert_date,'momcafe' as type from momcafe_list c " +
            " where c.board_title " +
            " like concat('%',#{board_title},'%') and  (c.insert_date >= #{start_date} and c.insert_date <=#{end_date})" +
            " ";
    @Select(GET_BOARDS)
    @Results(value={
            @Result(property="boardTitle",column="board_title"),
            @Result(property="boardView",column="board_view"),
            @Result(property="boardRecommand",column="board_recommand"),
            @Result(property="insertDate",column="insert_date"),
            @Result(property="type",column="type")
    })
    public ArrayList<Board> getBoards(
            @Param("board_title") String boardTitle,
            @Param("start_date") String startDate,
            @Param("end_date") String endDate);




    @Select("select * " +
            "from ppomppu_contents a join product_synonym_dic b on a.content like concat('%',b.product_synonym,'%') " +
            "where b.product_name=#{product_name} " +
            "and a.content like concat('%',#{content_like},'%') " +
            "and a.content not like concat('%',#{exclude_one},'%')" +
            " and a.content not like concat('%',#{exclude_two},'%') " +
            "and a.content not like concat('%',#{exclude_three},'%')")
    public  List<Map<String,Object>> getBoardContent(
            @Param("product_name") String productName,
            @Param("content_like") String contentLike,
            @Param("exclude_one") String excludeOne,
            @Param("exclude_two") String excludeTwo,
            @Param("exclude_three") String excludeThree
            );














}
