/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/9/15 15:11:56                           */
/*==============================================================*/



/*==============================================================*/
/* Table: biz_order_finance_collection��modify��                  */
/*==============================================================*/
alter table biz_order_finance_collection
add   collection_status              varchar(10)     comment '�տ�״̬����ӣ� -- \'',
add   collection_operator_employee_id int(11)         comment '�տ������Ա��id����ӣ� -- \'',
add   collection_remarks             varchar(1000)   comment '�տ�˵������ӣ� -- \'',
add   collection_operate_datetime    datetime        comment '�տ��������ʱ�� -- \'';

/*==============================================================*/
/* Table: biz_pre_pm_settle_finance_receive_moeny��modify��       */
/*==============================================================*/
alter table biz_pre_pm_settle_finance_receive_moeny
add   collection_status              varchar(10)     comment '�տ�״̬����ӣ� -- \'';

