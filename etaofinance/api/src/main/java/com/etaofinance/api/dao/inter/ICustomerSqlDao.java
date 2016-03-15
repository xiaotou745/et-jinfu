package com.etaofinance.api.dao.inter;

import java.util.List;
import java.util.Map;

public interface ICustomerSqlDao {
    int delete(String sql);
    int insert(String sql);
    List<Map<String, String>> select(String sql);
    int update(String sql);
}
