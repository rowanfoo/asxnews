package com.dharma.news.parser;

import com.dharma.news.data.entity.News;

import java.util.ArrayList;

public interface AsxNewsParser {
    public ArrayList<News> parse(String content);

    public boolean isEmptyPage(String content);
}
