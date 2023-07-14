package com.boot.study.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

public class CompanyProvider {
    public String findAll() {

        return new SQL() {
            {
                SELECT("*").FROM("company");
            }
        }.toString();

    }
}
