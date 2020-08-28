package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class VueBoard {
    private long sno;
    private String studyname;
    private String area;
    private String start;
    private String end;
    private String subject;
    private String email;
}
