package com.eric.board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @AllArgsConstructor @NoArgsConstructor
public class Board {
  private Long id;
  private String title;
  private String contents;
}
