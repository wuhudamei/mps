
package cn.damei.common.persistence.dialect.db;

import org.apache.commons.lang3.StringUtils;

import cn.damei.common.persistence.dialect.Dialect;


public class SQLServer2005Dialect implements Dialect {

    @Override
    public boolean supportsLimit() {
        return true;
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset,
                limit, Integer.toString(limit));
    }


    private String getLimitString(String querySqlString, int offset, int limit, String limitPlaceholder) {
        StringBuilder pagingBuilder = new StringBuilder();
        String orderby = getOrderByPart(querySqlString);
        String distinctStr = "";

        String loweredString = querySqlString.toLowerCase();
        String sqlPartString = querySqlString;
        if (loweredString.trim().startsWith("select")) {
            int index = 6;
            if (loweredString.startsWith("select distinct")) {
                distinctStr = "DISTINCT ";
                index = 15;
            }
            sqlPartString = sqlPartString.substring(index);
        }
        pagingBuilder.append(sqlPartString);


        if (StringUtils.isEmpty(orderby)) {
            orderby = "ORDER BY CURRENT_TIMESTAMP";
        }

        StringBuilder result = new StringBuilder();
        result.append("WITH query AS (SELECT ")
                .append(distinctStr)
                .append("TOP 100 PERCENT ")
                .append(" ROW_NUMBER() OVER (")
                .append(orderby)
                .append(") as __row_number__, ")
                .append(pagingBuilder)
                .append(") SELECT * FROM query WHERE __row_number__ BETWEEN ")
                .append(offset).append(" AND ").append(offset + limit)
                .append(" ORDER BY __row_number__");

        return result.toString();
    }

    static String getOrderByPart(String sql) {
        String loweredString = sql.toLowerCase();
        int orderByIndex = loweredString.indexOf("order by");
        if (orderByIndex != -1) {


            return sql.substring(orderByIndex);
        } else {
            return "";
        }
    }
}
