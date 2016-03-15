package com.etaofinance.api.service.inter;

import java.util.List;
import java.util.Map;

public interface ICustomerSqlService {
    int delete(String sql);
    int insert(String sql);
    List<Map<String, String>> select(String sql);
    int update(String sql);
}
