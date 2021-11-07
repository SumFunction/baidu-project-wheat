package com.lrr.demo.generator.upload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MsgTo {
    private Integer code;
    private  String msg;

}
