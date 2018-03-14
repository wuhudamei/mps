
package cn.damei.common.persistence.interceptor;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.plugin.Interceptor;

import cn.damei.common.config.Global;
import cn.damei.common.persistence.Page;
import cn.damei.common.persistence.dialect.Dialect;
import cn.damei.common.persistence.dialect.db.DB2Dialect;
import cn.damei.common.persistence.dialect.db.DerbyDialect;
import cn.damei.common.persistence.dialect.db.H2Dialect;
import cn.damei.common.persistence.dialect.db.HSQLDialect;
import cn.damei.common.persistence.dialect.db.MySQLDialect;
import cn.damei.common.persistence.dialect.db.OracleDialect;
import cn.damei.common.persistence.dialect.db.PostgreSQLDialect;
import cn.damei.common.persistence.dialect.db.SQLServer2005Dialect;
import cn.damei.common.persistence.dialect.db.SybaseDialect;
import cn.damei.common.utils.Reflections;

import java.io.Serializable;
import java.util.Properties;


public abstract class BaseInterceptor implements Interceptor, Serializable {
	
	private static final long serialVersionUID = 1L;

    protected static final String PAGE = "page";
    
    protected static final String DELEGATE = "delegate";

    protected static final String MAPPED_STATEMENT = "mappedStatement";

    protected Log log = LogFactory.getLog(this.getClass());

    protected Dialect DIALECT;







    @SuppressWarnings("unchecked")
	protected static Page<Object> convertParameter(Object parameterObject, Page<Object> page) {
    	try{
            if (parameterObject instanceof Page) {
                return (Page<Object>) parameterObject;
            } else {
                return (Page<Object>)Reflections.getFieldValue(parameterObject, PAGE);
            }
    	}catch (Exception e) {
			return null;
		}
    }


    protected void initProperties(Properties p) {
    	Dialect dialect = null;
        String dbType = Global.getConfig("jdbc.type");
        if ("db2".equals(dbType)){
        	dialect = new DB2Dialect();
        }else if("derby".equals(dbType)){
        	dialect = new DerbyDialect();
        }else if("h2".equals(dbType)){
        	dialect = new H2Dialect();
        }else if("hsql".equals(dbType)){
        	dialect = new HSQLDialect();
        }else if("mysql".equals(dbType)){
        	dialect = new MySQLDialect();
        }else if("oracle".equals(dbType)){
        	dialect = new OracleDialect();
        }else if("postgre".equals(dbType)){
        	dialect = new PostgreSQLDialect();
        }else if("mssql".equals(dbType) || "sqlserver".equals(dbType)){
        	dialect = new SQLServer2005Dialect();
        }else if("sybase".equals(dbType)){
        	dialect = new SybaseDialect();
        }
        if (dialect == null) {
            throw new RuntimeException("mybatis dialect error.");
        }
        DIALECT = dialect;





    }
}
