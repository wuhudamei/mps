
package cn.damei.common.persistence.dialect.db;

import cn.damei.common.persistence.dialect.Dialect;


public class H2Dialect implements Dialect {

    public boolean supportsLimit() {
        return true;
    }


    private String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder) {
        return sql + ((offset > 0) ? " limit " + limitPlaceholder + " offset "
                + offsetPlaceholder : " limit " + limitPlaceholder);
    }

    @Override
    public String getLimitString(String sql, int offset, int limit) {
        return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
    }
}