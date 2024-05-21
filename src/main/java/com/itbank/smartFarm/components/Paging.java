package com.itbank.smartFarm.components;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class Paging {
    private int reqPage, offset;
    private int boardCount = 10;
    private int pageCount = 10;
    private int totalBoard, totalPage;
    private int section, begin, end;
    private boolean prev, next;

    public Paging(int reqPage, int totalBoard) {
        this.reqPage = reqPage;
        this.totalBoard = totalBoard;

        offset = (reqPage - 1) * boardCount;

        totalPage = totalBoard / boardCount;
        totalPage += (totalBoard % boardCount == 0 ? 0 : 1);

        section = (reqPage - 1) / pageCount;
        begin = section * pageCount + 1;
        end = (section + 1) * pageCount;

        end = (end < totalPage) ? end : totalPage;

        prev = section != 0;
        next = end != totalPage;
    }
}
