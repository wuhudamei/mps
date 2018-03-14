
package cn.damei.common.persistence.dialect.db;

import cn.damei.common.persistence.dialect.Dialect;


public class DerbyDialect implements Dialect {
    @Override
    public boolean supportsLimit() {
        return false;
	}

    @Override
    public String getLimitString(String sql, int offset, int limit) {

        throw new UnsupportedOperationException("paged queries not supported");
    }


	public String getLimitString(String sql, int offset,String offsetPlaceholder, int limit, String limitPlaceholder) {
		throw new UnsupportedOperationException( "paged queries not supported" );
	}

}
