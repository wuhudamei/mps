
package cn.damei.common.persistence.dialect.db;

import cn.damei.common.persistence.dialect.Dialect;


public class SQLServerDialect implements Dialect {

    public boolean supportsLimit() {
        return true;
    }

    static int getAfterSelectInsertPoint(String sql) {
        int selectIndex = sql.toLowerCase().indexOf("select");
        final int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
        return selectIndex + (selectDistinctIndex == selectIndex ? 15 : 6);
    }

    public String getLimitString(String sql, int offset, int limit) {
        return getLimit(sql, offset, limit);
    }


    public String getLimit(String sql, int offset, int limit) {
        if (offset > 0) {
            throw new UnsupportedOperationException("sql server has no offset");
        }
        return new StringBuffer(sql.length() + 8)
                .append(sql)
                .insert(getAfterSelectInsertPoint(sql), " top " + limit)
                .toString();
    }


}
