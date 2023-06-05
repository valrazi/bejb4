package org.bejb4.finalproject.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageModel {
    private String message;
    private boolean status;
    private Object data;
    private String code;

}