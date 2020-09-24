package com.example.demo.repository;

import com.example.demo.entity.MyInfo;
import com.example.demo.entity.VueBoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class MyInfoRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(MyInfo info) throws Exception {
        String query = "insert into myinfo (sno, user_no) values (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection con)
                            throws SQLException {
                        PreparedStatement ps = con.prepareStatement(query, new String[]{"info_id"});
                        ps.setLong(1, info.getSno());
                        ps.setLong(2, info.getUser_no());
                        return ps;
                    }
                }, keyHolder);

        info.setInfo_id(keyHolder.getKey().longValue());
    }

    public List<VueBoard> list(Long userNo) throws Exception {
        List<VueBoard> results = jdbcTemplate.query(
                "select sb.sno,sb.studyname,sb.start,sb.end" +
                        " from study_board sb" +
                        " JOIN myinfo as my " +
                        " ON sb.sno = my.sno" +
                        " where my.user_no = ?",
                new RowMapper<VueBoard>() {
                    @Override
                    public VueBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                        VueBoard vueBoard = new VueBoard();
                        vueBoard.setSno(rs.getLong("sno"));
                        vueBoard.setStudyname(rs.getString("studyname"));
                        vueBoard.setStart(rs.getString("start"));
                        vueBoard.setEnd(rs.getString("end"));
                        return vueBoard;
                    }
                },userNo
        );

        return results;
    }

    public void delete(VueBoard vueBoard,Long userNo) throws Exception {
        String query  = "delete from myinfo where sno = ? and user_no = ?";
        jdbcTemplate.update(query, vueBoard.getSno(),userNo);
    }

    public MyInfo findByBoardId(MyInfo  myInfo) throws Exception {
        List<MyInfo> results = jdbcTemplate.query(
                "select info_id" +
                        " from myinfo where sno = ? and user_no = ?",
                new RowMapper<MyInfo>() {
                    @Override
                    public MyInfo mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        MyInfo myInfo = new MyInfo();

                        myInfo.setInfo_id(rs.getInt("info_id"));

                        return myInfo;
                    }
                }, myInfo.getSno(),myInfo.getUser_no());
        return results.isEmpty() ? null : results.get(0);
    }

}

