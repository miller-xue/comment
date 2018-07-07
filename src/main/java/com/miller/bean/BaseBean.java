package com.miller.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by miller on 2018/7/7
 */
@Getter
@Setter
public class BaseBean {
    private Page page;

    public BaseBean() {
        this.page = new Page();
    }
}
