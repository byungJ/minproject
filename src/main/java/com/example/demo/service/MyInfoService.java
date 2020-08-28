package com.example.demo.service;

import com.example.demo.entity.MyInfo;
import com.example.demo.entity.VueBoard;

import java.util.List;

public interface MyInfoService {
    public void register(MyInfo myInfo) throws Exception;
//    public VueBoard read(Long info_id) throws Exception;
//    public void modify(MyInfo myInfo) throws Exception;
//    public void remove(Long info_id) throws Exception;
    public List<VueBoard> list(Long userNo) throws Exception;
}
