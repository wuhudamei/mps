/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/22 19:24:22                           */
/*==============================================================*/


drop table if exists biz_cus_service_important_degree;

drop table if exists biz_cus_service_liable_type;

drop table if exists biz_cus_service_problem;

/*==============================================================*/
/* Table: biz_cus_service_important_degree                      */
/*==============================================================*/
create table biz_cus_service_important_degree
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   important_degree_code          varchar(20)     comment '��Ҫ�̶ȱ��� -- \'',
   father_important_degree_code   varchar(20)     comment '����Ҫ�̶ȱ��� -- \'',
   important_degree_level         int             comment '��Ҫ�̶ȼ��� -- \'',
   important_degree_name          varchar(100)    comment '��Ҫ�̶����� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_cus_service_liable_type                           */
/*==============================================================*/
create table biz_cus_service_liable_type
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   liable_type_code               varchar(20)     comment '���������� -- \'',
   father_liable_type_code        varchar(20)     comment '������������ -- \'',
   liable_type_level              int             comment '������𼶱� -- \'',
   liable_type_name               varchar(100)    comment '����������� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_cus_service_problem                               */
/*==============================================================*/
create table biz_cus_service_problem
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   store_code                     varchar(50)     comment '�ŵ���� -- \'',
   order_id                       int(11)         comment '����id -- \'',
   order_code                     varchar(100)    comment '�������� -- \'',
   work_order_code                varchar(100)    comment '������� -- \'',
   customer_address               varchar(255)    comment '�ͻ���ַ -- \'',
   customer_name                  varchar(20)     comment '�ͻ����� -- \'',
   customer_mobile                varchar(11)     comment '�ͻ��ֻ��� -- \'',
   contractor_name                varchar(20)     comment '��Ŀ�������� -- \'',
   contractor_mobile              varchar(11)     comment '��Ŀ�����ֻ��� -- \'',
   supervisor_name                varchar(20)     comment '�ʼ�Ա���� -- \'',
   supervisor_mobile              varchar(11)     comment '�ʼ�Ա�ֻ��� -- \'',
   problem_create_datetime        datetime        comment '���ⴴ��ʱ�� -- \'',
   problem_describe               varchar(1000)   comment '�������� -- \'',
   liable_department_code         varchar(20)     comment '���β��ű��� -- \'',
   liable_type_code_1             varchar(20)     comment '���α���1 -- \'',
   liable_type_code_2             varchar(20)     comment '���α���2 -- \'',
   important_degree_code_1        varchar(20)     comment '��Ҫ�̶ȱ���1 -- \'',
   important_degree_code_2        varchar(20)     comment '��Ҫ�̶ȱ���2 -- \'',
   photo_url                      varchar(1000)   comment '��Ƭurl -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

