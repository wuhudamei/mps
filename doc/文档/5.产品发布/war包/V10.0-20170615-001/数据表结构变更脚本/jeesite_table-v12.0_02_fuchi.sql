/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/5 15:54:46                            */
/*==============================================================*/


/*==============================================================*/
/* Table: biz_order_checksize��modify2��                          */
/*==============================================================*/
alter table biz_order_checksize
add order_install_item_id          int(11)         comment '������װ��id����ӣ� -- \'';

/*==============================================================*/
/* Table: biz_order_install_item��modify��                        */
/*==============================================================*/
alter table biz_order_install_item
add   days_plan_checksize            int             comment '�����ڼ��츴�ߣ���ӣ� -- \'',
add   is_to_checksize                char(1)         comment '�Ƿ񸴳ߣ���ӣ� -- \'0.��1.�ǣ�';

/*==============================================================*/
/* Table: biz_project_install_item��modify2��                     */
/*==============================================================*/
alter table biz_project_install_item
add   days_plan_checksize            int             comment '�����ڼ��츴�ߣ���ӣ� -- \'',
add   is_to_checksize                char(1)         comment '�Ƿ񸴳ߣ���ӣ� -- \'0.��1.�ǣ�';

