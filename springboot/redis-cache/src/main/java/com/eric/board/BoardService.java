package com.eric.board;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
  
  @Autowired
  BoardRepository repository;
  
  @Cacheable(key = "#size", value = "getBoards")
  public List<Board> getBoards(String size) {
    return repository.createBySize(size);
  }
  
  public static int getDbCount() {
    return BoardRepository.getDbCount();
  }
}
