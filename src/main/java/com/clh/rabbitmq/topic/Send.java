package com.clh.rabbitmq.topic;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    public static void main(String[] args) throws IOException, TimeoutException {
        int x = 2 ;
        System.out.println(( x++ )/3);
    }
}
