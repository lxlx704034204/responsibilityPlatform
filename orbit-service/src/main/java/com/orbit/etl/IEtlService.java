package com.orbit.etl;

/**
 * 单表逐批次导入,增加调度
 */
public interface IEtlService {

  /**
   * 默认每次100条之后commit一次
   */
  int DEFAULT_BATCH_SIZE = 100;

  /**
   * 获取导入的数据源jdbc连接串  //TODO 需要抽象出对象,目前仅仅用string代替,很可能是jdbc链接
   *
   * @return 数据源的描述
   */
  String getSourceJdbc();

  /**
   * 获取导出到哪里jdbc连接串
   */
  String getDestinationJdbc();

  /**
   * 获取要导出的源表名字
   */
  String getSourceTableName();

  /**
   * 获取要导入到的目标库表的名字
   */
  String getDestTableName();


  /**
   * 源表字段到目标表字段的映射
   */
  String getFieldMappings();


  /**
   * 获取批处理的量,每次多少一次commit
   */
  String getBachSize();


  /**
   * 执行etl任务
   */
  void execute();
}
