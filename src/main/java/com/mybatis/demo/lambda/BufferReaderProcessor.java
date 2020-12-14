package com.mybatis.demo.lambda;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferReaderProcessor{

    String process(BufferedReader b)throws IOException;
}