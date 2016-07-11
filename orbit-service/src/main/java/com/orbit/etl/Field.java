package com.orbit.etl;

/**
 * 表字段描述
 */
public abstract class Field {

  String name;

  int length;

  FieldData data;


  /**
   * 将一个Filed转换成其他类型的Field
   */
  Field convertTo<Class<F extends  Field>>();

}
