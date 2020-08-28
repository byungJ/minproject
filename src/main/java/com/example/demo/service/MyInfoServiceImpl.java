package com.example.demo.service;

import com.example.demo.entity.MyInfo;
import com.example.demo.entity.VueBoard;
import com.example.demo.repository.MyInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyInfoServiceImpl implements  MyInfoService {
    static final Logger log = LoggerFactory.getLogger(MyInfoServiceImpl.class);

    @Autowired
    private MyInfoRepository repository;

    @Override
    public void register(MyInfo myInfo) throws Exception {
        repository.create(myInfo);
    }
//    @Override
//    public VueBoard read(Long sno) throws Exception {
//        log.info("MyInfoService read(sno): " + sno);
//        return repository.read(sno);
//    }
//    @Override
//    public void modify(MyInfo myInfo) throws Exception {
//        log.info("MyInfoService modify(board): " + myInfo);
//        repository.update(myInfo);
//    }
//    @Override
//    public void remove(Long sno) throws Exception {
//        repository.delete(sno);
//    }
    @Override
    public List<VueBoard> list(Long userNo) throws Exception {
        return repository.list(userNo);
    }
}
