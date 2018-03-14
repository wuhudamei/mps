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
   order_id                       int(11)         comment '订单id -- \'',
   order_number                   varchar(100)    comment '订单编号 -- \'',
   materials_choice_total_amount  decimal(10,2)   comment '选材总价 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_bill_item                        */
/*==============================================================*/
create table biz_materials_choice_bill_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   materials_choice_bill_id       int(11)         comment '选材单id -- \'',
   materials_choice_type          varchar(10)     comment '选材类型 -- \'',
   materials_choice_category_code varchar(100)    comment '选材类目编码 -- \'',
   supplier_name                  varchar(100)    comment '供应商名称 -- \'',
   supplier_no                    varchar(100)    comment '供应商编号 -- \'',
   brand                          varchar(100)    comment '品牌 -- \'',
   model                          varchar(100)    comment '型号 -- \'',
   attribute                      varchar(100)    comment '属性 -- \'',
   unit                           varchar(100)    comment '单位 -- \'',
   spec                           varchar(100)    comment '规格 -- \'',
   position                       varchar(100)    comment '位置 -- \'',
   budget_number_1                varchar(50)     comment '预算用量1 -- \'',
   budget_number_2                varchar(50)     comment '预算用量2 -- \'',
   loss_ratio                     decimal(10,2)   comment '损耗系数 -- \'',
   include_loss_number            decimal(10,2)   comment '含损耗用量 -- \'',
   unit_price                     decimal(10,2)   comment '单价 -- \'',
   total_amount                   decimal(10,2)   comment '合计 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_category                         */
/*==============================================================*/
create table biz_materials_choice_category
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   parent_id                      int(11)         comment '所属父级id -- \'',
   category_level                 int             comment '类目级别 -- \'',
   category_code                  varchar(100)    comment '类目编码 -- \'',
   category_name                  varchar(100)    comment '类目名称 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_change_bill                      */
/*==============================================================*/
create table biz_materials_choice_change_bill
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   order_number                   varchar(100)    comment '订单编号 -- \'',
   change_bill_code               varchar(100)    comment '变更单编号 -- \'',
   change_reason                  varchar(255)    comment '变更原因 -- \'',
   change_apply_date              date            comment '设计师申请变更日期 -- \'',
   designer_name                  varchar(20)     comment '设计师姓名 -- \'',
   change_checked_date            date            comment '变更单审核通过日期 -- \'',
   checker_name                   varchar(20)     comment '审计员姓名 -- \'',
   add_item_total_amount          decimal(10,2)   comment '增项合计金额 -- \'',
   reduce_item_total_amount       decimal(10,2)   comment '减项合计金额 -- \'',
   change_bill_total_amount       decimal(10,2)   comment '变更单总金额 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_change_bill_item                 */
/*==============================================================*/
create table biz_materials_choice_change_bill_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   materials_choice_change_bill_id int(11)         comment '变更单id -- \'',
   change_type                    varchar(10)     comment '变更类型 -- \'1.增项；2.减项',
   materials_choice_type          varchar(10)     comment '选材类型 -- \'',
   materials_choice_category_code varchar(100)    comment '材料类目编码 -- \'',
   brand                          varchar(100)    comment '品牌 -- \'',
   model                          varchar(100)    comment '型号 -- \'',
   attribute                      varchar(100)    comment '属性 -- \'',
   unit                           varchar(100)    comment '单位 -- \'',
   spec                           varchar(100)    comment '规格 -- \'',
   change_number                  varchar(50)     comment '变更用量 -- \'',
   unit_price                     decimal(10,2)   comment '单价 -- \'',
   total_amount                   decimal(10,2)   comment '变更合计金额 -- \'增项为正，减项为负',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_materials_choice_category_install_item            */
/*==============================================================*/
create table biz_materials_choice_category_install_item
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   order_install_item_id          int(11)         comment '安装项id -- \'',
   materials_choice_category_id   int(11)         comment '选材类目id -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);