package com.dandy.core;

import java.util.Comparator;

public class ResultComparator implements Comparator<ResultModel>{

    @Override
    public int compare(ResultModel person1, ResultModel person2) {
	return Float.compare(person1.getGwa(), person2.getGwa());
    }
}
