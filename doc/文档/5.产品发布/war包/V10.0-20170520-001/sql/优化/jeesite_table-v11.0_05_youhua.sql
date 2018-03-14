/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/5/12 12:15:16                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_order��modify7��                                    */
/*==============================================================*/
alter table biz_order
add   contract_amount                decimal(8,2)    comment '��ͬ��� -- \'';

/*==============================================================*/
/* Table: biz_purchase��modify4��                                 */
/*==============================================================*/
alter table biz_purchase
add   status_describe                varchar(10)     comment '״̬���� -- \'',
add   purchase_count_total           decimal(8,2)    comment '�ɹ���Ʒ���� -- \'';


/*==============================================================*/
/* Table: biz_prepare_order��modify3��                            */
/*==============================================================*/
alter table biz_prepare_order
add   contract_amount                decimal(8,2)    comment '��ͬ��� -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage_settlement_detail��modify��       */
/*==============================================================*/
alter table biz_order_taskpackage_settlement_detail
add   ensure_amount_datetime         datetime        comment 'ȷ��н������ʱ�� -- \'';

/*==============================================================*/
/* Table: biz_order_taskpackage_settlement��modify7��             */
/*==============================================================*/
alter table biz_order_taskpackage_settlement
add   ensure_amount_datetime         datetime        comment 'ȷ��н������ʱ�� -- \'';

