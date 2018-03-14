/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/8/25 11:03:40                           */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_materials_choice_bill��modify��                     */
/*==============================================================*/
alter table biz_materials_choice_bill
add   is_dealed_main_materials       char(1)         comment '�Ƿ��Ѵ������ģ���ӣ� -- \'0.��1.��',
add   is_dealed_wall_floor_tile      char(1)         comment '�Ƿ��Ѵ���ǽ��ש����ӣ� -- \'0.��1.��';

/*==============================================================*/
/* Table: biz_order_wall_floor_tile��modify2��                    */
/*==============================================================*/
alter table biz_order_wall_floor_tile
add   attribute                      varchar(100)    comment '���ԣ���ӣ� -- \'',
add   supplier_name                  varchar(100)    comment '��Ӧ�����ƣ���ӣ� -- \'',
add   loss_ratio                     decimal(10,2)   comment '���ϵ������ӣ� -- \'',
add   purchase_count                 decimal(10,2)   comment '��������������ӣ� -- \'';

/*==============================================================*/
/* Table: biz_prepare_order��modify6��                            */
/*==============================================================*/
alter table biz_prepare_order
add   refuse_reason_type             varchar(10)     comment '�ܾ����ɷ��ࣨ��ӣ� -- \'',
add   refuse_reason                  varchar(1000)   comment '�ܾ����ɣ���ӣ� -- \'';

