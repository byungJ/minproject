package com.example.demo.controller;

import com.example.demo.entity.MyInfo;
import com.example.demo.entity.VueBoard;
import com.example.demo.security.AuthUtil;
import com.example.demo.service.MyInfoService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Log
@RestController
@RequestMapping("/myinfo")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class MyInfoController {
    @Autowired
    private MyInfoService service;

    @PostMapping("")
    public ResponseEntity<String> register(
            @Validated @RequestBody MyInfo info,
            UriComponentsBuilder uriBuilder,@RequestHeader (name="Authorization") String header ) throws Exception {
        log.info("POST register()");
        Long userNo = AuthUtil.getUserNo(header);
        info.setUser_no(userNo);
        service.register(info);
        log.info(info.toString());
        log.info("register info.getInfo_id() = " + info.getInfo_id());


        return new ResponseEntity<String>("수강신청완료", HttpStatus.OK);
    }



    @GetMapping
    public ResponseEntity<List<VueBoard>> list(@RequestHeader (name="Authorization") String header ) throws Exception {
        log.info("list()");
        Long userNo = AuthUtil.getUserNo(header);
        return new ResponseEntity<List<VueBoard>>(service.list(userNo),HttpStatus.OK);

    }
}
