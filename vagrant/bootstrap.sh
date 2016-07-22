#!/bin/bash

mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.backup
cp /home/vagrant/sync/CentOS7-Base-163.repo  /etc/yum.repos.d/
yum clean all
yum makecache
yum remove -y  mariadb-libs 
yum install -y perl perl-devel net-tools vim perl-Data-Dumper

groupadd mysql
useradd -g mysql -s /bin/false mysql

sync_dir=/home/vagrant/sync
rpm -ivh ${sync_dir}/MySQL-Cluster-server-gpl-7.4.11-1.el7.x86_64.rpm

host=`hostname`
if [ "$host" == "sql" ]
    then
        rpm -ivh ${sync_dir}/MySQL-Cluster-client-gpl-7.4.11-1.el7.x86_64.rpm
        cp ${sync_dir}/my.conf /etc/my.cnf
elif [[ "$host" =~ "data*" ]]
    then
        cp ${sync_dir}/my.conf /etc/my.cnf
elif [ "$host" == "manager" ]
    then
        mkdir /var/lib/mysql-cluster
        cp ${sync_dir}/config.ini /var/lib/mysql-cluster/
else
    echo "should not be here"
fi
