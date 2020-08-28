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
public class VueBoardRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void create(VueBoard board) throws Exception {
        String query = "insert into study_board (studyname, area, start, end, subject, email) values (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
            new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    PreparedStatement ps = con.prepareStatement(query, new String[] {"sno"});
                    ps.setString(1, board.getStudyname());
                    ps.setString(2, board.getArea());
                    ps.setString(3, board.getStart());
                    ps.setString(4, board.getEnd());
                    ps.setString(5, board.getSubject());
                    ps.setString(6, board.getEmail());
                    return ps;
                }
            }, keyHolder);

        board.setSno(keyHolder.getKey().longValue());
    }

    public VueBoard read(Long boardNo) throws Exception {
        List<VueBoard> results = jdbcTemplate.query(
            "select studyname, area, start, end, subject " +
                    "from study_board where sno = ?",
            new RowMapper<VueBoard>() {
                @Override
                public VueBoard mapRow(ResultSet rs, int rowNum)
                        throws SQLException {
                    VueBoard board = new VueBoard();

                    board.setSno(rs.getInt("sno"));
                    board.setStudyname(rs.getString("studyname"));
                    board.setArea(rs.getString("area"));
                    board.setStart(rs.getString("start"));
                    board.setEnd(rs.getString("end"));
                    board.setSubject(rs.getString("subject"));

                    System.out.println("VueBoardRepository: " + board);

                    return board;
                }
            }, boardNo
        );

        return results.isEmpty() ? null : results.get(0);
    }

    public void update(VueBoard board) throws Exception {
        String query = "update cafe_board set title = ?, content = ? " +
                "where board_no = ?";
        jdbcTemplate.update(query, board.getStudyname(),
                board.getStudyname(), board.getStudyname());
    }

    public void delete(Long boardNo) throws Exception {
        String query = "delete from cafe_board where board_no = ?";
        jdbcTemplate.update(query, boardNo);
    }

    public List<VueBoard> list() throws Exception {
        List<VueBoard> results = jdbcTemplate.query(
                "select sno,studyname, area, start, end, subject from study_board order by sno desc",
                new RowMapper<VueBoard>() {
                    @Override
                    public VueBoard mapRow(ResultSet rs, int rowNum) throws SQLException {
                        VueBoard board = new VueBoard();
                        board.setSno(rs.getLong("sno"));
                        board.setStudyname(rs.getString("studyname"));
                        board.setArea(rs.getString("area"));
                        board.setStart(rs.getString("start"));
                        board.setEnd(rs.getString("end"));
                        board.setSubject(rs.getString("subject"));

                        return board;
                    }
                }
        );

        return results;
    }

}
