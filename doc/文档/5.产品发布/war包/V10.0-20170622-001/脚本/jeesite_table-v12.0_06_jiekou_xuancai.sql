/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/13 17:35:58                           */
/*==============================================================*/


drop table if exists biz_materials_choice_bill;

drop table if exists biz_materials_choice_bill_item;

drop table if exists biz_materials_choice_category;

drop table if exists biz_materials_choice_change_bill;

drop table if exists biz_materials_choice_change_bill_item;

drop table if exists biz_materials_choice_category_install_item;


/*==============================================================*/
/* Table: biz_materials_choice_bill                             */
/*==============================================================*/
create table biz_materials_choice_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   order_number                   varchar(100)    comment '������� -- \'',
   materials_choice_total_amount  decimal(10,2)   comment 'ѡ���ܼ� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_bill_item                        */
/*==============================================================*/
create table biz_materials_choice_bill_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   materials_choice_bill_id       int(11)         comment 'ѡ�ĵ�id -- \'',
   materials_choice_type          varchar(10)     comment 'ѡ������ -- \'',
   materials_choice_category_code varchar(100)    comment 'ѡ����Ŀ���� -- \'',
   supplier_name                  varchar(100)    comment '��Ӧ������ -- \'',
   supplier_no                    varchar(100)    comment '��Ӧ�̱�� -- \'',
   brand                          varchar(100)    comment 'Ʒ�� -- \'',
   model                          varchar(100)    comment '�ͺ� -- \'',
   attribute                      varchar(100)    comment '���� -- \'',
   unit                           varchar(100)    comment '��λ -- \'',
   spec                           varchar(100)    comment '��� -- \'',
   position                       varchar(100)    comment 'λ�� -- \'',
   budget_number_1                varchar(50)     comment 'Ԥ������1 -- \'',
   budget_number_2                varchar(50)     comment 'Ԥ������2 -- \'',
   loss_ratio                     decimal(10,2)   comment '���ϵ�� -- \'',
   include_loss_number            decimal(10,2)   comment '��������� -- \'',
   unit_price                     decimal(10,2)   comment '���� -- \'',
   total_amount                   decimal(10,2)   comment '�ϼ� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_category                         */
/*==============================================================*/
create table biz_materials_choice_category
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '�ŵ�id -- \'',
   parent_id                      int(11)         comment '��������id -- \'',
   category_level                 int             comment '��Ŀ���� -- \'',
   category_code                  varchar(100)    comment '��Ŀ���� -- \'',
   category_name                  varchar(100)    comment '��Ŀ���� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_change_bill                      */
/*==============================================================*/
create table biz_materials_choice_change_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '����id -- \'',
   order_number                   varchar(100)    comment '������� -- \'',
   change_bill_code               varchar(100)    comment '�������� -- \'',
   change_reason                  varchar(255)    comment '���ԭ�� -- \'',
   change_apply_date              date            comment '���ʦ���������� -- \'',
   designer_name                  varchar(20)     comment '���ʦ���� -- \'',
   change_checked_date            date            comment '��������ͨ������ -- \'',
   checker_name                   varchar(20)     comment '���Ա���� -- \'',
   add_item_total_amount          decimal(10,2)   comment '����ϼƽ�� -- \'',
   reduce_item_total_amount       decimal(10,2)   comment '����ϼƽ�� -- \'',
   change_bill_total_amount       decimal(10,2)   comment '������ܽ�� -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_change_bill_item                 */
/*==============================================================*/
create table biz_materials_choice_change_bill_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   materials_choice_change_bill_id int(11)         comment '�����id -- \'',
   change_type                    varchar(10)     comment '������� -- \'1.���2.����',
   materials_choice_type          varchar(10)     comment 'ѡ������ -- \'',
   materials_choice_category_code varchar(100)    comment '������Ŀ���� -- \'',
   brand                          varchar(100)    comment 'Ʒ�� -- \'',
   model                          varchar(100)    comment '�ͺ� -- \'',
   attribute                      varchar(100)    comment '���� -- \'',
   unit                           varchar(100)    comment '��λ -- \'',
   spec                           varchar(100)    comment '��� -- \'',
   change_number                  varchar(50)     comment '������� -- \'',
   unit_price                     decimal(10,2)   comment '���� -- \'',
   total_amount                   decimal(10,2)   comment '����ϼƽ�� -- \'����Ϊ��������Ϊ��',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_category_install_item            */
/*==============================================================*/
create table biz_materials_choice_category_install_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_install_item_id          int(11)         comment '��װ��id -- \'',
   materials_choice_category_id   int(11)         comment 'ѡ����Ŀid -- \'',
   remarks                        varchar(255)    comment '��ע -- \'',
   create_by                      varchar(64)     comment '������Ա��id -- \'',
   create_date                    datetime        comment '��������ʱ�� -- \'',
   update_by                      varchar(64)     comment '������Ա��id -- \'',
   update_date                    datetime        comment '��������ʱ�� -- \'',
   del_flag                       char(1)         comment '�Ƿ�ɾ�� -- \'',
   primary key (id)
);