# Mysql cluster安装文件：MySQL-Cluster-server-gpl-7.4.11-1.el7.x86_64.rpm
[安装文件下载](http://dev.mysql.com/downloads/cluster/)

# 各节点安装的软件包情况
节点类型    | 安装rpm软件包
:----------|:------------------------------------------------------------------------------------------------------
 ndb_mgmd  | MySQL-Cluster-server-gpl-7.4.11-1.el7.x86_64.rpm
 sql node  | MySQL-Cluster-server-gpl-7.4.11-1.el7.x86_64.rpm 和 MySQL-Cluster-client-gpl-7.4.11-1.el7.x86_64.rpm
 data node | MySQL-Cluster-server-gpl-7.4.11-1.el7.x86_64.rpm

# 安装步骤
1. [节点安装](http://dev.mysql.com/doc/refman/5.7/en/mysql-cluster-install-linux-rpm.html)
2. [初始化配置](http://dev.mysql.com/doc/refman/5.7/en/mysql-cluster-install-configuration.html)
3. [启动](http://dev.mysql.com/doc/refman/5.7/en/mysql-cluster-install-first-start.html)

## 各节点启动方法和启动顺序
 启动顺序 | 节点类型   | 启动方法
:------- |:----------|:-----------------------------------------------------------------------------------------------------------------------------
 1)      | ndb_mgmd  | sudo ndb_mgmd -f /var/lib/mysql-cluster/config.ini
 2)      | data node | sudo ndbd
 3)      | sql node  | sudo /bin/sh /usr/bin/mysqld_safe --datadir=/var/lib/mysql --user=mysql --ndbcluster --ndb-connectstring=192.168.101.202:1186&

 
## 各节点关闭
 节点类型   | 关闭方法
:----------|:------------------------------------------------------------------------------------------------------
 ndb_mgmd  | sudo ndb_mgm –e shutdown
 
## 注意:如果sql node通过init脚本启动不成功的话,可以执行如下命令启动:
sudo /bin/sh /usr/bin/mysqld_safe --datadir=/var/lib/mysql --user=mysql --ndbcluster --ndb-connectstring=192.168.101.202:1186&
根据[mysql官方推荐配置](https://dev.mysql.com/doc/refman/5.6/en/mysql-cluster-config-starting.html)
如下参数最好也加入到 mysqld启动参数中:
1. --ndb-use-exact-count=0
2. --ndb-index-stat-enable=0
3. --ndb-force-send=1
4. --engine-condition-pushdown=1

 
## 各节点启动顺序
 1. ndb_mgmd
 2. data node
 3. sql node
 注意:启动各节点之前,需要将该节点上的mysql服务停掉,/etc/init.d/mysql stop
  
  
启动完成之后,可以在ndb_mgmd节点上,通过ndb_mgm进入到cluster的管理客户端,输入 show 命令查看集群当前的状态,如果各个节点之前没有出现not connected,则表示集群正常启动了。

# Cluster Configuration
ndbd(NDB) |              2 node(s) 
----------|:-----------------------------------------------------------
id=2      |@192.168.101.204  (mysql-5.6.29 ndb-7.4.11, Nodegroup: 0, *)
id=3	  |@192.168.101.205  (mysql-5.6.29 ndb-7.4.11, Nodegroup: 0)

ndb_mgmd(MGM) |              1 node(s) 
--------------|:------------------------------------------
id=1          |@192.168.101.202  (mysql-5.6.29 ndb-7.4.11)

mysqld(API)   |              1 node(s) 
--------------|:------------------------------------------
id=4          |@192.168.101.203  (mysql-5.6.29 ndb-7.4.11)


# mysql root账户密码: mysql

# 需要注意:
1.  除了data node可以支持在线添加新节点,其他节点,无论是ndb_mgmd节点,还是sql节点,都不能在不重启集群的情况下添加新节点
2.  Mysql中文字符集支持,需要将/etc/my.conf文件全部替换成vagrant中共享目录中的文件

# [如何重置mysql root密码?](http://dev.mysql.com/doc/refman/5.7/en/resetting-permissions.html)
1.  mysqladmin -uroot -p$初始密码 password mysql
    初始密码位置在sql节点上的/root/.mysql_secret文件中

# centos7 关闭防火墙
sudo systemctl stop firewalld.service
sudo systemctl disable firewalld.service

sudo systemctl enable iptables
sudo systemctl disable iptables


# 关于spring boot的example
参见:https://github.com/spring-projects/spring-boot/tree/v1.4.0.RELEASE/spring-boot-samples






