/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/9 17:58:13                            */
/*==============================================================*/


drop table if exists biz_material_selfbuy;

drop table if exists biz_material_selfbuy_reimburse;

/*==============================================================*/
/* Table: biz_material_selfbuy                                  */
/*==============================================================*/
create table biz_material_selfbuy
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   store_id                       int(11)         comment '门店id -- \'',
   project_mode                   varchar(10)     comment '工程模式 -- \'',
   material_name                  varchar(100)    comment '材料名称 -- \'',
   material_code                  varchar(100)    comment '材料编码 -- \'',
   settle_rate                    decimal(5,2)    comment '项目经理结算比例 -- \'',
   settle_stage                   varchar(10)     comment '所属结算阶段 -- \'1.中期结算；2.竣工结算',
   is_enabled                     char(1)         comment '是否启用 -- \'0.否；1.是；',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_material_selfbuy_reimburse                        */
/*==============================================================*/
create table biz_material_selfbuy_reimburse
(
   id                             int(11)         not null auto_increment comment 'id -- \'',
   reimburse_type                 varchar(10)     comment '报销类型 -- \'1.初次申报；2.重新申报',
   related_reimburse_id           int(11)         comment '关联报销id -- \'',
   order_id                       int(11)         comment '订单id -- \'',
   material_selfbuy_id            int(11)         comment '材料自采id -- \'',
   customer_pay_amount            decimal(8,2)    comment '客户交费金额 -- \'',
   settle_rate                    decimal(5,2)    comment '结算比例 -- \'',
   settle_stage                   varchar(10)     comment '所属结算阶段 -- \'1.中期结算；2.竣工结算',
   settle_amount                  decimal(8,2)    comment '实际结算金额 -- \'',
   reimburse_remarks              varchar(255)    comment '报销说明 -- \'',
   reimburse_status               varchar(10)     comment '报销状态 -- \'',
   reimburse_status_datetime      datetime        comment '报销状态日期时间 -- \'',
   reimburse_status_remarks       varchar(255)    comment '报销状态说明 -- \'',
   remarks                        varchar(255)    comment '备注 -- \'',
   create_by                      varchar(64)     comment '创建人员工id -- \'',
   create_date                    datetime        comment '创建日期时间 -- \'',
   update_by                      varchar(64)     comment '更新人员工id -- \'',
   update_date                    datetime        comment '更新日期时间 -- \'',
   del_flag                       char(1)         comment '是否删除 -- \'',
   primary key (id)
);

/*==============================================================*/
/* Table: biz_pm_settle_bill（modify3）                           */
/*==============================================================*/
alter table biz_pm_settle_bill
add   material_selfbuy_reimburse_amount decimal(10,2)   comment '自采材料报销金额（添加） -- \'';


/*==============================================================*/
/* Table: biz_pm_settle_summary_bill（modify3）                   */
/*==============================================================*/
alter table biz_pm_settle_summary_bill
add   material_selfbuy_reimburse_amount decimal(10,2)   comment '自采材料报销金额（添加） -- \'';

