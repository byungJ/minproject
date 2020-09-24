package com.example.demo.service;

import com.example.demo.entity.VueBoard;
import com.example.demo.repository.VueBoardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueBoardServiceImpl implements  VueBoardService {
    static final Logger log = LoggerFactory.getLogger(VueBoardServiceImpl.class);

    @Autowired
    private VueBoardRepository repository;

    @Override
    public void register(VueBoard sno) throws Exception {
        repository.create(sno);
    }
    @Override
    public VueBoard read(Long sno) throws Exception {
        log.info("VueBoardService read(sno): " + sno);
        return repository.read(sno);
    }
    @Override
    public void modify(VueBoard board) throws Exception {
        log.info("VueBoardService modify(board): " + board);
        repository.update(board);
    }
    @Override
    public void remove(Long sno) throws Exception {
        repository.delete(sno);
    }
    @Override
    public List<VueBoard> list() throws Exception {
        return repository.list();
    }

    @Override
    public List<VueBoard> search(String search) throws Exception {
        return repository.search(search);
    }
}
