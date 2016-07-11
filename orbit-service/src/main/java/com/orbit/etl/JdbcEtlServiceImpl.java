package com.orbit.etl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class JdbcEtlServiceImpl implements IEtlService {
  private static Logger logger = LoggerFactory.getLogger(JdbcEtlServiceImpl.class);

  private String sourceJdbc;
  private String destJdbc;
  private String sourceTableName;
  private String destTableName;
  private int batchSize;

  /**
   * 获取不同的RDBMS的jdbc驱动程序class name
   */
  abstract String getDriverClass();


  @Override
  public String getSourceJdbc() {
    return null;
  }


  protected Connection getConnection(String url) throws Exception {
    try {
      Class.forName(getDriverClass());
      return DriverManager.getConnection(url);
    } catch (ClassNotFoundException e) {
      logger.error("获取connection失败", e);
      throw e;
    } catch (SQLException e) {
      logger.error("获取connection失败", e);
      throw e;
    }
  }

  @Override
  public String getDestinationJdbc() {
    return null;
  }

  @Override
  public String getSourceTableName() {
    return null;
  }

  @Override
  public String getDestTableName() {
    return null;
  }

  @Override
  public String getFieldMappings() {
    return null;
  }

  @Override
  public String getBachSize() {
    return null;
  }

  @Override
  public void execute() {
    //获取源表链接
    //获取目标表连接
    //目标表是否存在,是否自动创建
    //读取源表,分batch读取之后,插入目标表
  }
}
