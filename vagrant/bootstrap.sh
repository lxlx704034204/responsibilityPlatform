#!/bin/bash

mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
cp /home/vagrant/sync/CentOS7-Base-163.repo  /etc/yum.repos.d/
yum clean all
yum makecache
yum remove -y  mariadb-libs 
yum install -y perl perl-devel net-tools vim perl-Data-Dumper

groupadd mysql
useradd -g mysql -s /bin/false mysql
systemctl stop firewalld
systemctl disable firewalld

sync_dir=/home/vagrant/sync
rpm -ivh ${sync_dir}/MySQL-Cluster-server-gpl-7.4.11-1.el7.x86_64.rpm

host=`hostname`

systemctl disable mysql
systemctl stop mysql

if [ "$host" == "sql" ]
    then
        cp ${sync_dir}/ifcfg-eth1-sql /etc/sysconfig/network-scripts/ifcfg-eth1
        service network restart
        rpm -ivh ${sync_dir}/MySQL-Cluster-client-gpl-7.4.11-1.el7.x86_64.rpm
        cp ${sync_dir}/my.cnf /etc/my.cnf

        #启动sql节点
        /usr/bin/mysqld_safe --datadir=/var/lib/mysql --user=mysql --ndbcluster --ndb-connectstring=192.168.101.202:1186&

        set -x
        
        ps -ef|grep mysql|grep -v grep
        while [ $? -ne 0 ]
        do
            echo "waiting for mysql to start..."
            sleep 1s
            ps -ef|grep mysql|grep -v grep
        done


        #重置root密码，并创建orbit库
        # 获取mysql安装过程中随机生成的root账户密码
        pass=`awk -F"):" '{gsub(/^[ \t]+/, "", $2); gsub(/[ \t]+$/, "", $2); print $2}'  /root/.mysql_secret`
        mysqladmin -uroot -p$pass password mysql

        set +x
elif [[ $host =~ ^data ]]
    then
        cp ${sync_dir}/ifcfg-eth1-$host /etc/sysconfig/network-scripts/ifcfg-eth1
        service network restart
        cp ${sync_dir}/my.cnf /etc/my.cnf
        mkdir -p /usr/local/mysql/data
        chown -R mysql:mysql /var/lib/mysql
        #启动ndbd节点
        ndbd
elif [ "$host" == "manager" ]
    then
        cp ${sync_dir}/ifcfg-eth1-manager /etc/sysconfig/network-scripts/ifcfg-eth1
        service network restart
        mkdir -p /var/lib/mysql-cluster
        cp ${sync_dir}/config.ini /var/lib/mysql-cluster/
        # 启动ndb_mgmd进程
        ndb_mgmd -f /var/lib/mysql-cluster/config.ini
else
    echo "error: should not be here"
fi


