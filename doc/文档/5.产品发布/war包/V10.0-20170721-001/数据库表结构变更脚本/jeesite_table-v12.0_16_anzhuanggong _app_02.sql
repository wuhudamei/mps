/*==============================================================*/
/* Table: biz_order_install_item��modify2��                       */
/*==============================================================*/
alter table biz_order_install_item
add   install_mode                   varchar(10)     comment '��װģʽ����ӣ� -- \'1.��ҵ��2.��ͳ',
add   status                         varchar(10)     comment '״̬����ӣ� -- \'',
add   status_datetime                datetime        comment '״̬���ڣ���ӣ� -- \'';

/*==============================================================*/
/* Table: biz_order_install_plan��modify��                        */
/*==============================================================*/
alter table biz_order_install_plan
add   install_mode                   varchar(10)     comment '��װģʽ����ӣ� -- \'1.��ҵ��2.��ͳ',
add   send_supplier_id               int(11)         comment 'ת����Ӧ��id����ӣ� -- \'',
add   plan_complete_date             datetime        comment '�ƻ��깤�������ڣ���ӣ� -- \'',
add   supplier_confirm_complete_date datetime        comment '��Ӧ��ȷ��������ڣ���ӣ� -- \'';

/*==============================================================*/
/* Table: biz_project_install_item��modify4��                     */
/*==============================================================*/
alter table biz_project_install_item
add   install_mode                   varchar(10)     comment '��װģʽ����ӣ� -- \'1.��ҵ��2.��ͳ';

